package com.cainiao.login.net

import com.cainiao.service.network.BaseCniaoRsp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 登陆模块的接口
 */
interface LoginService {

    @GET("accounts/phone/is/register")
    suspend fun isRegister(@Query("mobi") mobi: String): Call<BaseCniaoRsp>

    @POST("accounts/course/10301/login")
    suspend fun login(@Body reqBody: LoginReqBody): Call<BaseCniaoRsp>
}