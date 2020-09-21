package com.cainiao.jetpack.login

import android.app.Application
import android.os.SystemClock
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.cainiao.jetpack.db.State
import com.cainiao.jetpack.db.UserBean
import com.cainiao.jetpack.db.UserDataBase
import kotlin.concurrent.thread

/**
 * @author boomhe on 2020/9/18.
 */
class LoginViewModel(application: Application) : AndroidViewModel(application) {
    val uName = ObservableField<String>()
    val uPass = ObservableField<String>()

    // 登录感知
    val loginOK = MutableLiveData<Boolean>()

    fun login() {
        ToastUtils.showShort("登录")
        // ViewModel 是在主线程中
        thread {
            val user = UserBean(0, "张三", 1, 28, "SH")
            UserDataBase.getInstance(getApplication()).getUserDao().insert(user)
            // 更新状态
            UserDataBase.getInstance(getApplication()).getStateDao().insert(State(login = true))
        }
        // sleep
        SystemClock.sleep(1000)

        loginOK.value = true
    }

}