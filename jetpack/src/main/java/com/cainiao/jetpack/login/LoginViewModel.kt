package com.cainiao.jetpack.login

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.blankj.utilcode.util.ToastUtils

/**
 * @author boomhe on 2020/9/18.
 */
class LoginViewModel(application: Application) : AndroidViewModel(application) {
    val uName = ObservableField<String>()
    val uPass = ObservableField<String>()

    fun login() {
        ToastUtils.showShort("登录")
    }
}