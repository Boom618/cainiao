package com.cainiao.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    // 账户、密码
    val obMobile = ObservableField<String>()
    val obPassWord = ObservableField<String>()

    fun goLogin() {

    }
}