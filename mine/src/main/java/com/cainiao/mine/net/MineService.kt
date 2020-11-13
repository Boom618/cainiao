package com.cainiao.mine.net

import com.cainiao.service.network.BaseCniaoRsp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * @author boomhe on 2020/11/12
 * Mine 模块 网络相关接口
 */
interface MineService {

    /**
     * 用户详情信息的获取
     */
    @GET("/member/userinfo")
    fun getUserInfo(@Header("token") token: String?): Call<BaseCniaoRsp>


}