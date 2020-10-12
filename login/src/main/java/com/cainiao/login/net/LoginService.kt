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

    // 在 LoginRepo 函数中保证是 挂起函数，这里可以不需要再挂起了
    @GET("accounts/phone/is/register")
    fun isRegister(@Query("mobi") mobi: String): Call<BaseCniaoRsp>

    @POST("accounts/course/10301/login")
    fun login(@Body reqBody: LoginReqBody): Call<BaseCniaoRsp>
}