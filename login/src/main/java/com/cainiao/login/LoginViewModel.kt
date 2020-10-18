package com.cainiao.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cainiao.common.base.BaseViewModel
import com.cainiao.login.net.LoginReqBody
import com.cainiao.login.repo.ILoginResource
import kotlinx.coroutines.launch

class LoginViewModel(private val resource: ILoginResource) : BaseViewModel() {

    // 账户、密码
    val obMobile = ObservableField<String>()
    val obPassWord = ObservableField<String>()

    val liveRegisterRsp = resource.registerRsp
    val liveLoginRsp = resource.loginRsp

    // 检查是否登录的账号
    private fun checkRegister(mobi: String) = serverAwait { resource.checkRegister(mobi) }

    fun repoLogin() {
        val account = obMobile.get() ?: return
        val password = obPassWord.get() ?: return
        serverAwait { resource.requestLogin(LoginReqBody(account, password)) }
    }


    fun goLogin() {
        val account = obMobile.get() ?: return
        checkRegister(account)

    }
}