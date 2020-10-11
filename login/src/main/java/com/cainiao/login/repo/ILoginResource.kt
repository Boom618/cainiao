package com.cainiao.login.repo

import com.cainiao.login.net.LoginReqBody

/**
 * 登陆模块 抽象数据接口
 */
interface ILoginResource {

    /**
     * 校验手机好是否注册，合法
     */
    suspend fun checkRegister(mobi:String)

    /**
     * 登陆，获取 token
     */
    suspend fun requestLogin(body: LoginReqBody)
}