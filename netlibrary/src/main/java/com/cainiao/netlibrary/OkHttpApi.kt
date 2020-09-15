package com.cainiao.netlibrary

import com.cainiao.netlibrary.config.KtHttpLogInterceptor
import com.cainiao.netlibrary.config.RetryInterceptor
import com.google.gson.Gson
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * @author boomhe on 2020/9/10.
 *
 * OkHttpApi 实现类
 */
class OkHttpApi : HttpApi {

    var maxRetry = 0//最大重试 次数

    companion object {
        private const val TAG = "OkHttpApi"
        private const val baseUrl = "http:api.qingyunke.com"
    }

    private val mClient = OkHttpClient.Builder()
        // 完整请求超时时长、与服务器建立链接时长、读取服务器数据时长、向服务器写数据时长
        .callTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        // 重连、重定向
        .retryOnConnectionFailure(true)
        .followRedirects(true)
        // 自定义拦截器: 公共头、日志、重试、
//        .addNetworkInterceptor(CniaoInterceptor())
        .addNetworkInterceptor(KtHttpLogInterceptor {
            logLevel(KtHttpLogInterceptor.LogLevel.BODY)
        })
        .addNetworkInterceptor(RetryInterceptor(maxRetry))
        .build()

    override fun get(param: Map<String, Any>, path: String, callback: IHttpCallback) {
        val url = "$baseUrl$path"
        // url 转换
        val urlBuilder = url.toHttpUrl().newBuilder()

        param.forEach {
            urlBuilder.addEncodedQueryParameter(it.key, it.value.toString())
        }
        val request = Request.Builder()
            .get()
            .url(urlBuilder.build())
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        mClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                // 在 oKHttp 的回调中 回调自己处理的回调
                callback.onSuccess(response)
            }
        })

    }

    override fun post(body: Any, path: String, callback: IHttpCallback) {
        val url = "$baseUrl$path"

        val request = Request.Builder()
            .post(Gson().toJson(body).toRequestBody())
//            .url(url)
            .url("https://course.api.cniao5.com/accounts/course/10301/login")
            .build()

        mClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(response)
            }
        })
    }
}