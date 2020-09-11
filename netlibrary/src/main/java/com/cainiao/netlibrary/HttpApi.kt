package com.cainiao.netlibrary

/**
 * @author boomhe on 2020/9/10.
 * 网络统一请求接口
 */
interface HttpApi {

    /**
     * Get 异步
     */
    fun get(param: Map<String, Any>, path: String, callback: IHttpCallback)

    /**
     * 同步
     * 默认实现：方式一
     */
    fun getSync(param: Map<String, Any>, path: String): Any? {
        return Any()
    }


    fun post(body: Any, path: String, callback: IHttpCallback)


    /**
     * 同步
     * 默认实现：方式二
     */
    fun postSync(body: Any, path: String): Any? = Any()
}