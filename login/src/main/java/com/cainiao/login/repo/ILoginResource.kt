package com.cainiao.login.repo

import androidx.lifecycle.LiveData
import com.cainiao.login.net.LoginReqBody
import com.cainiao.login.net.LoginRsp
import com.cainiao.login.net.RegisterRsp

/**
 * 登陆模块 抽象数据接口
 */
interface ILoginResource {

    // 校验注册结果
    val registerRsp: LiveData<RegisterRsp>

    // 登录结果
    val loginRsp: LiveData<LoginRsp>

    /**
     * 校验手机好是否注册，合法
     */
    suspend fun checkRegister(mobi: String)

    /**
     * 登陆，获取 token
     */
    suspend fun requestLogin(body: LoginReqBody)
}