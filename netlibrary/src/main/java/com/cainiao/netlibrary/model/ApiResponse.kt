package com.cainiao.netlibrary.model

import retrofit2.Response

/**
 * @author boomhe on 2020/9/21.
 * 网络数据封装：方式三
 */
sealed class ApiResponse<T> {

    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                ApiErrorResponse(
                    response.code(),
                    response.errorBody()?.string() ?: response.message()
                )
            }
        }

        fun <T> create(errorCode: Int, error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(errorCode, error.message ?: "Unknown Error")
        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorCode: Int, val errorMsg: String) : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

// 未知错误
internal const val UNKNOWN_ERROR_CODE = -1