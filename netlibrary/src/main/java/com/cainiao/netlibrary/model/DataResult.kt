package com.cainiao.netlibrary.model

/**
 * @author boomhe on 2020/9/15.
 * 数据密封类
 * 也 可以使用kotlin标准库中的Result
 * out R:指定泛型 R 返回值
 * 网络数据封装：方式一
 */
sealed class DataResult<out R> {

    // 成功
    data class Success<out T>(val data: T) : DataResult<T>()

    // 失败
    data class Error(val e: Exception) : DataResult<Nothing>()

    // 加载数据中
    object Loading : DataResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success [data = $data]"
            is Error -> "Error [data = $e]"
            is Loading -> "Loading"
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

/**
 * 网络数据封装：方式二
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, "Resource success")
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data:T?):Resource<T>{
            return Resource(Status.LOADING,data,null)
        }
    }

}