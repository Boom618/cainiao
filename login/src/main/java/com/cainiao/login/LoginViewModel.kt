package com.cainiao.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cainiao.common.base.BaseViewModel
import com.cainiao.login.net.LoginReqBody
import com.cainiao.login.repo.ILoginResource
import kotlinx.coroutines.launch

class LoginViewModel(private val resource:ILoginResource) : BaseViewModel() {

    // 账户、密码
    val obMobile = ObservableField<String>()
    val obPassWord = ObservableField<String>()



    fun goLogin() {
        viewModelScope.launch {
            resource.checkRegister("18516597508")
            resource.requestLogin(LoginReqBody("18516597508","123456"))
        }

    }
}