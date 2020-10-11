package com.cainiao.login.repo

import com.cainiao.login.net.LoginReqBody
import com.cainiao.login.net.LoginService


/**
 *
 */
class LoginRepo(private val service: LoginService):ILoginResource {


    override suspend fun checkRegister(mobi: String) {
        service.isRegister(mobi)
    }

    override suspend fun requestLogin(body: LoginReqBody) {
        service.login(body)
    }


}