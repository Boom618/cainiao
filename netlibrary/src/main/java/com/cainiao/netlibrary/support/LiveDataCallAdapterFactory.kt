package com.cainiao.netlibrary.support

import androidx.lifecycle.LiveData
import com.cainiao.netlibrary.model.ApiResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.IllegalArgumentException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author boomhe on 2020/9/22.
 *
 * 将 Retrofit 返回数据，转化为 LiveData 的 adapter 工厂类
 */
class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }
        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawType = getRawType(observableType)
        if (rawType != ApiResponse::class.java) {
            throw IllegalArgumentException("type must ba a resource")
        }
        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must ba ParameterizedType")
        }
        val bodyType = getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(bodyType)

    }
}