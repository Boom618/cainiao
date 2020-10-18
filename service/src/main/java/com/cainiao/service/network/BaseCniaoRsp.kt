package com.cainiao.service.network

import androidx.annotation.Keep
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.cainiao.common.network.model.DataResult
import com.cainiao.common.network.model.succeeded
import com.cainiao.common.network.support.CniaoUtils
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


/**
 * 响应数据体
 * @Keep：过滤混淆
 */
@Keep
data class BaseCniaoRsp(val code: Int, val data: String, val message: String?) {

    /*
    |1| 成功|
    |0 |失败|
    |101| appid为空或者app不存在|
    |102|签名错误|
    |103|签名失效（已经使用过一次）|
    |104|请求已失效（时间戳过期）|
    |105|缺少必传参数|
    |106|参数格式有误或者未按规则提交|
    |201|缺少token|
    |202|token无效/错误|
    |203|token已过期|
    |401|没有权限调用|
    |501|数据库连接出错|
    |502|读写数据库异常|
     */
    companion object {
        const val SERVER_CODE_FAILED = 0//接口请求响应业务处理 失败
        const val SERVER_CODE_SUCCESS = 1//接口请求响应业务处理 成功
    }


}

/**
 * 将 body.string 转为 entity
 */
inline fun <reified T> BaseCniaoRsp.toEntity(): T? {

    if (data == null) {
        LogUtils.e(" data == null ,$code,$message")
        return null
    }
    // json 不支持 string 转 string
    if (T::class.java.isAssignableFrom(String::class.java)) {
        return CniaoUtils.decodeData(this.data) as T
    }

    return kotlin.runCatching {
        GsonUtils.fromJson(CniaoUtils.decodeData(this.data), T::class.java)
    }.onFailure {
        it.printStackTrace()
    }.getOrNull()
}

// 1、扩展 网络请求的结果 : 成功回调
@OptIn(ExperimentalContracts::class)
inline fun <R> DataResult<R>.onSuccess(action: R.() -> Unit): DataResult<R> {

    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (succeeded) action.invoke((this as DataResult.Success<R>).data)
    return this
}
// crossinline : 加强内联，不允许 return 整个函数，只能 return 当前函数 【见第一行代码 P-268】


// 1.1 扩展 接口成功，code != 1
@OptIn(ExperimentalContracts::class)
inline fun BaseCniaoRsp.onBizError(crossinline block: (code: Int, message: String) -> Unit): BaseCniaoRsp {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    if (code != BaseCniaoRsp.SERVER_CODE_SUCCESS) {
        block.invoke(code, message ?: "Error Message Null")
    }
    return this
}

// 1.2 扩展 接口成功，code == 1
@OptIn(ExperimentalContracts::class)
inline fun <reified T> BaseCniaoRsp.onBizOK(crossinline block: (code: Int, data: T?, message: String?) -> Unit): BaseCniaoRsp {

    contract {
        callsInPlace(block,InvocationKind.AT_MOST_ONCE)
    }
    if (code == BaseCniaoRsp.SERVER_CODE_SUCCESS){
        block.invoke(code,this.toEntity<T>(),message)
    }
    return this
}


// 2、扩展 网络请求的结果 : 失败回调
@OptIn(ExperimentalContracts::class)
inline fun <R> DataResult<R>.onFailure(action: (e: Throwable) -> Unit): DataResult<R> {

    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is DataResult.Error) action.invoke(e)
    return this
}

