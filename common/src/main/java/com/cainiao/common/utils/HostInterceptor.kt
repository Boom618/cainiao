package com.cainiao.common.utils

import androidx.core.net.toUri
import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Response

// 开发期间 替换 host 拦截器
class HostInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originRequest = chain.request()
        // release.course.api.cniao5.com 不带scheme的
        val oldHost = originRequest.url.host
        val oldUrlStr = originRequest.url.toString()
        // 调试使用
        val newHost = getBaseHost().toUri().host ?: oldHost
        val newUrlStr = if (newHost == oldHost) {
            oldUrlStr
        } else {
            oldUrlStr.replace(oldHost, newHost)
        }

        val newBuilder = originRequest.newBuilder()
        LogUtils.i("Host 拦截器 ${originRequest.url} 拦截后 $newUrlStr")
        newBuilder.url(newUrlStr)

        return chain.proceed(newBuilder.build())
    }
}