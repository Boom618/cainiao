package com.cainiao.login.repo

import androidx.lifecycle.LiveData
import com.cainiao.common.model.SingleLiveData
import com.cainiao.login.net.LoginReqBody
import com.cainiao.login.net.LoginRsp
import com.cainiao.login.net.LoginService
import com.cainiao.login.net.RegisterRsp


/**
 * retrofit 的 service
 */
class LoginRepo(private val service: LoginService) : ILoginResource {

    private val _registerRsp = SingleLiveData<RegisterRsp>()
    private val _loginRsp = SingleLiveData<LoginRsp>()

    override val registerRsp: LiveData<RegisterRsp> = _registerRsp
    override val loginRsp: LiveData<LoginRsp> = _loginRsp


    override suspend fun checkRegister(mobi: String) {
        service.isRegister(mobi)
    }

    override suspend fun requestLogin(body: LoginReqBody) {
        service.login(body)
    }


}