package com.cainiao.common.network.support

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cainiao.common.network.model.ApiResponse
import com.cainiao.common.network.model.DataResult
import com.cainiao.common.network.model.UNKNOWN_ERROR_CODE
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Response
import retrofit2.await
import retrofit2.awaitResponse
import java.io.IOException

/**
 * @author boomhe on 2020/9/22.
 * okHttp3 的 Respond 扩展函数
 */

inline fun <reified T> okhttp3.Call.toLiveData(): LiveData<T?> {

    val live = MutableLiveData<T?>()

    enqueue(object : okhttp3.Callback {
        override fun onFailure(call: Call, e: IOException) {
            live.postValue(null)
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                // response 扩展函数
                response.toEntity<T>()
            }
        }
    })
    return live
}


/**
 * 将 Response 对象转化为 entity ，也就是 body.string 转化为 entity
 *
 *
 */
inline fun <reified T> Response.toEntity(): T? {
    if (!isSuccessful) {
        return null
    }
    // Gson 不允许将 string 转化 string
    if (T::class.java.isAssignableFrom(String::class.java)) {
        return kotlin.runCatching {
            body?.string()
        }.getOrNull() as? T
    }
    return kotlin.runCatching {
        Gson().fromJson(body?.string(), T::class.java)
    }.onFailure {
        it.printStackTrace()
    }.getOrNull()
}


//  retrofit 相关扩展. retrofit 源码支持协程

/**
 * retrofit call 转化为 LiveData 可观察的结果
 */
fun <T> retrofit2.Call<T>.toLiveData(): LiveData<T?> {
    val live = MutableLiveData<T?>()
    enqueue(object : retrofit2.Callback<T> {
        override fun onResponse(call: retrofit2.Call<T>, response: retrofit2.Response<T>) {
            val value = when (response.isSuccessful) {
                true -> response.body()
                false -> null
            }
            live.postValue(value)
        }

        override fun onFailure(call: retrofit2.Call<T>, t: Throwable) {
            live.postValue(null)
        }
    })
    return live
}

/**
 * 扩展 retrofit 返回数据，调用 await
 * 返回 DataResult
 */
suspend fun <T : Any> retrofit2.Call<T>.serverData(): DataResult<T> {

    var result: DataResult<T> = DataResult.Loading
    kotlin.runCatching {
        await()
    }.onFailure {
        result = DataResult.Error(RuntimeException(it))
    }.onSuccess {
        result = DataResult.Success(it)
    }
    return result
}

/**
 * 扩展 retrofit 返回数据，调用 await
 * 返回 ApiResponse
 */

suspend fun <T : Any> retrofit2.Call<T>.serverRsp(): ApiResponse<T> {
    var result: ApiResponse<T>
    val response = kotlin.runCatching {
        awaitResponse()
    }.onFailure {
        result = ApiResponse.create(UNKNOWN_ERROR_CODE, it)
        it.printStackTrace()
    }.getOrThrow()
    return ApiResponse.create(response)
}
