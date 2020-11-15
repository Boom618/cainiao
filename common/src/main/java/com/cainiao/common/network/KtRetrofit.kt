package com.cainiao.common.network

import com.cainiao.common.network.config.CniaoInterceptor
import com.cainiao.common.network.config.KtHttpLogInterceptor
import com.cainiao.common.network.support.LiveDataCallAdapterFactory
import com.cainiao.common.utils.HostInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author boomhe on 2020/9/21.
 * Retrofit + 协程封装
 */
object KtRetrofit {

    private val mOkClient = OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        // 重连、重定向
        .retryOnConnectionFailure(true)
        .followRedirects(false)
        .addInterceptor(HostInterceptor())
        // 公共头拦截器、日志拦截器
        .addNetworkInterceptor(CniaoInterceptor())
        .addNetworkInterceptor(KtHttpLogInterceptor {
            logLevel(KtHttpLogInterceptor.LogLevel.BODY)
        }).build()

    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .client(mOkClient)

    private var retrofit: Retrofit? = null

    /**
     * 初始化配置
     * 项目基类 URL ，以 / 结尾
     */
    fun initConfig(baseUrl: String, client: OkHttpClient = mOkClient): KtRetrofit {
        retrofit = retrofitBuilder.baseUrl(baseUrl).client(client).build()
        return this
    }

    /**
     * 获取 retrofit service:接口类
     */
    fun <T> getService(serviceClazz: Class<T>): T {
        if (retrofit == null) {
            throw UninitializedPropertyAccessException("Retrofit must init")
        } else {
            return retrofit!!.create(serviceClazz)
        }
    }
}