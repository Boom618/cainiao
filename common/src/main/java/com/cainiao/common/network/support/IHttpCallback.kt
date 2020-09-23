package com.cainiao.common.network.support

/**
 * @author boomhe on 2020/9/10.
 * Http 接口回调
 */
interface IHttpCallback {

    /**
     * [data] 返回回调数据
     */
    fun onSuccess(data: Any?)

    /**
     * 返回错误信息
     */
    fun onFailed(error: Any?)
}