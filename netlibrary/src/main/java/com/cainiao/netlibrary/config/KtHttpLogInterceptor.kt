package com.cainiao.netlibrary.config

import android.util.Log
import okhttp3.*
import okio.Buffer
import okio.BufferedSink
import java.lang.StringBuilder
import java.net.URLDecoder

/**
 * @author boomhe on 2020/9/11.
 * 日志 拦截器
 */
class KtHttpLogInterceptor(block: KtHttpLogInterceptor.() -> Unit) : Interceptor {

    private var logLevel = LogLevel.NONE
    private var colorLevel = ColorLevel.DEBUG
    private var logTag = TAG

    companion object {
        private const val TAG = "<KtHttp>"
    }

    init {
        block.invoke(this)
    }

    // 设置日志
    fun logLevel(level: LogLevel): KtHttpLogInterceptor {
        logLevel = level
        return this
    }

    fun colorLevel(color: ColorLevel): KtHttpLogInterceptor {
        colorLevel = color
        return this
    }

    fun logTag(tag: String): KtHttpLogInterceptor {
        logTag = tag
        return this
    }


    override fun intercept(chain: Interceptor.Chain): Response {
        // 请求
        val request = chain.request()

        return kotlin.runCatching {
            chain.proceed(request)
        }.onFailure {
            it.printStackTrace()
            logIt(it.message.toString(), ColorLevel.ERROR)
        }.onSuccess {
            if (logLevel == LogLevel.NONE) {
                return it
            }
            // 记录请求日志
            logRequest(request, chain.connection())
            // 记录响应数据
            logResponse(it)
        }.getOrThrow()
    }

    // 记录响应日志
    private fun logResponse(response: Response) {


    }

    // 记录请求日志
    private fun logRequest(request: Request, connection: Connection?) {
        val sb = StringBuilder()
        sb.appendln("\r\n")
        sb.appendln("->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->->")
        when (logLevel) {
            LogLevel.NONE -> {
                // --
            }
            LogLevel.BASIC -> {
                logBasicReq(sb, request, connection)
            }
            LogLevel.HEADERS -> {
                logHeadersReq(sb, request, connection)
            }
            LogLevel.BODY -> {
                logBodyReq(sb, request, connection)
            }
        }
    }

    private fun logBodyReq(sb: StringBuilder, request: Request, connection: Connection?) {
        logHeadersReq(sb, request, connection)
        // 读取 request body 内容
        val build = request.newBuilder().build()
        val sink = Buffer()
        build.body?.writeTo(sink)
        sb.appendln("RequestBody: ${sink.readUtf8()}")
    }

    private fun logHeadersReq(sb: StringBuilder, request: Request, connection: Connection?) {
        logBasicReq(sb, request, connection)
        val headerStr = request.headers.joinToString("") {
            "请求 Header ：{${it.first}=${it.second}}\n"
        }
        sb.appendln(headerStr)

    }


    private fun logBasicReq(sb: StringBuilder, request: Request, connection: Connection?) {
        sb.appendLine(
            "请求 method ：${request.method} url : ${decodeUrlStr(request.url.toString())} " +
                    "tag : ${request.tag()} protocol : ${connection?.protocol() ?: Protocol.HTTP_1_1}"
        )
    }

    // 对编码的 URL 解码
    private fun decodeUrlStr(url: String): String? {
        return kotlin.runCatching {
            URLDecoder.decode(url, "utf-8")
        }.onFailure { it.printStackTrace() }.getOrNull()
    }


    /**
     * 打印日志
     * [any]:需要打印的数据对象
     * [tempLevel]:打印color等级
     */
    private fun logIt(any: Any, tempLevel: ColorLevel = ColorLevel.DEBUG) {
        when (tempLevel) {
            ColorLevel.VERBOSE -> Log.v(logTag, any.toString())
            ColorLevel.DEBUG -> Log.d(logTag, any.toString())
            ColorLevel.INFO -> Log.i(logTag, any.toString())
            ColorLevel.WARN -> Log.w(logTag, any.toString())
            ColorLevel.ERROR -> Log.e(logTag, any.toString())
        }
    }

    enum class LogLevel {
        NONE,   // 不打印
        BASIC,  // 只打印行，首 请求/响应
        HEADERS,// 打印所有 头
        BODY    // 打印所有
    }

    enum class ColorLevel {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }
}