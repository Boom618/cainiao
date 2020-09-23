package com.cainiao.common.network.config

import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author boomhe on 2020/9/11.
 * 重试 拦截器
 */
class RetryInterceptor(var maxRetry: Int = 0) : Interceptor {

    // 已经重试的次数,注意，设置maxRetry重试次数，作用于重试，
    // 所以总的请求次数，可能就是原始的1，+ maxRetry
    private var retriedNum = 0

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var proceed = chain.proceed(request)
        LogUtils.d("重试次数 ： $retriedNum")
        while (!proceed.isSuccessful && retriedNum < maxRetry) {
            retriedNum++
            LogUtils.d("失败后重试次数 ： $retriedNum")
            proceed = chain.proceed(request)
        }
        return proceed
    }
}