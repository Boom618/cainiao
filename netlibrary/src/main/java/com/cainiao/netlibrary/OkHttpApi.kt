package com.cainiao.netlibrary

import androidx.collection.SimpleArrayMap
import com.cainiao.netlibrary.config.CniaoInterceptor
import com.cainiao.netlibrary.config.KtHttpLogInterceptor
import com.cainiao.netlibrary.config.RetryInterceptor
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * @author boomhe on 2020/9/10.
 *
 * OkHttpApi 实现类
 * 构造函数私有化  类似单例类
 */
class OkHttpApi private constructor() : HttpApi {

    var maxRetry = 0//最大重试 次数

    // 存储请求，用于取消
    private val callMap = SimpleArrayMap<Any, Call>()

    companion object {
        @Volatile
        private var api: OkHttpApi? = null

        @Synchronized
        fun getInstance(): OkHttpApi {
            return api ?: OkHttpApi().also { api = it }
        }
    }

    private val defaultClient = OkHttpClient.Builder()
        // 完整请求超时时长、与服务器建立链接时长、读取服务器数据时长、向服务器写数据时长
        .callTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        // 重连、重定向
        .retryOnConnectionFailure(true)
        .followRedirects(true)
        // 自定义拦截器: 公共头、日志、重试、(Header 头拦截器放在最前面，才可能打印需要数据 )
        .addNetworkInterceptor(CniaoInterceptor())
        .addNetworkInterceptor(KtHttpLogInterceptor {
            logLevel(KtHttpLogInterceptor.LogLevel.BODY)
        })
        .addNetworkInterceptor(RetryInterceptor(maxRetry))
        .build()

    private var mClient = defaultClient

    fun getClient() = mClient

    fun initClient(client: OkHttpClient) {
        this.mClient = client
    }

    override fun get(param: Map<String, Any>, path: String, callback: IHttpCallback) {
//        val url = "$baseUrl$path"
//         url 转换
//        val urlBuilder = url.toHttpUrl().newBuilder()
        val urlBuilder = path.toHttpUrl().newBuilder()

        param.forEach {
            urlBuilder.addEncodedQueryParameter(it.key, it.value.toString())
        }
        val request = Request.Builder()
            .get()
            .tag(param)
            .url(urlBuilder.build())
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        val newCall = mClient.newCall(request)
        // 存储请求
        callMap.put(request.tag(), newCall)
        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                // 在 oKHttp 的回调中 回调自己处理的回调
                callback.onSuccess(response)
            }
        })

    }

    /**
     * 封装协程接口
     */
    fun get(param: Map<String, Any>, path: String) = runBlocking {
        val urlBuilder = path.toHttpUrl().newBuilder()

        param.forEach {
            urlBuilder.addEncodedQueryParameter(it.key, it.value.toString())
        }
        val request = Request.Builder()
            .get()
            .tag(param)
            .url(urlBuilder.build())
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        val newCall = mClient.newCall(request)
        // 存储请求
        callMap.put(request.tag(), newCall)
        // 调用自定义 扩展函数 ，使得接口数据在协程中回调
        newCall.call()
    }

    /**
     * 自定义扩展函数 + 协程
     */
    private suspend fun Call.call(async: Boolean = true): Response {
        return suspendCancellableCoroutine { continuation ->
            if (async) {
                enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        if (continuation.isCancelled) {
                            return
                        }
                        continuation.resumeWithException(e)
                    }

                    override fun onResponse(call: Call, response: Response) {
                        continuation.resume(response)
                    }
                })
            } else {
                continuation.resume(execute())
            }
            continuation.invokeOnCancellation {
                try {
                    cancel()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }

    override fun post(body: Any, path: String, callback: IHttpCallback) {

        val request = Request.Builder()
            .post(Gson().toJson(body).toRequestBody())
            .url(path)
            .tag(body)
            .build()
        val newCall = mClient.newCall(request)
        //存储请求，用于取消
        callMap.put(request.tag(), newCall)
        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(response)
            }
        })
    }

    override fun cancelRequest(tag: Any) {
        callMap.get(tag)?.cancel()
    }

    override fun cancelAllRequest() {
        for (i in 0 until callMap.size()) {
            callMap.get(callMap.keyAt(i))?.cancel()
        }
    }
}