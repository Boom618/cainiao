package com.cainiao.common.network.config

import com.blankj.utilcode.util.*
import com.cainiao.common.utils.CniaoSpUtils
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.CacheControl
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer

/**
 * @author boomhe on 2020/9/15.
 * 添加公共头的拦截器
 */
class CniaoInterceptor : Interceptor {

    companion object {
        private val gson = GsonBuilder().enableComplexMapKeySerialization()
            .create()
        private val mapType = object : TypeToken<Map<String, Any>>() {}.type

    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val originRequest = chain.request()
        // 封装clientInfo,deviceInfo等。也可以在post请求中，自定义封装headers的字段体内容
        val attachHeaders = mutableListOf<Pair<String, String>>(
            "appid" to NET_CONFIG_APPID,
            "platform" to "android",
            "timestamp" to System.currentTimeMillis().toString(),

            // 工具类 获取
            "brand" to DeviceUtils.getManufacturer(),
            "model" to DeviceUtils.getModel(),
            "uuid" to DeviceUtils.getUniqueDeviceId(),
            "network" to NetworkUtils.getNetworkType().name,
            "system" to DeviceUtils.getSDKVersionName(),
            "version" to AppUtils.getAppVersionName()
        )
        // token 仅在有值的时候才传递
        var localToken = CniaoSpUtils.getString(SP_KEY_USER_TOKEN, originRequest.header("token")) ?: ""
        if (localToken.isNotEmpty()) {
            attachHeaders.add("token" to localToken)
        }
        val signHeaders = mutableListOf<Pair<String, String>>()
        signHeaders.addAll(attachHeaders)
        // get的请求，参数

        when (originRequest.method) {
            "GET" -> {
                originRequest.url.queryParameterNames.forEach {
                    signHeaders.add(it to (originRequest.url.queryParameter(it) ?: ""))
                }
            }
            "POST" -> {
                val requestBody = originRequest.body
                if (requestBody is FormBody) {
                    for (i in 0 until requestBody.size) {
                        signHeaders.add(requestBody.name(i) to requestBody.value(i))
                    }
                }
                // json 的 body 需要将 requestBody 反序列化为 json 转 map application/json
                if (requestBody?.contentType()?.type == "application"
                    && requestBody.contentType()?.subtype == "json") {
                    kotlin.runCatching {
                        val buffer = Buffer()
                        requestBody.writeTo(buffer)
                        buffer.readByteString().utf8()
                    }.onSuccess {
                        val map = gson.fromJson<Map<String, Any>>(it, mapType)
                        map.forEach { entry ->
                            signHeaders.add(entry.key to entry.value.toString())
                        }
                    }
                }
            }
        }
        // sign = MD5（ascii排序后的 headers 及 params 的key=value拼接&后，
        // 最后拼接appkey和value）//32位的大写
        val signValue = signHeaders
            .sortedBy {
                it.first
            }.joinToString("&") {
                "${it.first}=${it.second}"
            }.plus("&appkey=$NET_CONFIG_APPKEY")

        // 重新构建新的请求，添加 header
        val newBuilder = originRequest.newBuilder()
            .cacheControl(CacheControl.FORCE_NETWORK)
        attachHeaders.forEach { newBuilder.header(it.first, it.second) }
        // 没有添加签名 SB 了
        newBuilder.header("sign", EncryptUtils.encryptMD5ToString(signValue))
        if (originRequest.method == "POST" && originRequest.body != null) {
            newBuilder.post(originRequest.body!!)
        } else if (originRequest.method == "GET") {
            newBuilder.get()
        }
        return chain.proceed(newBuilder.build())
    }
}