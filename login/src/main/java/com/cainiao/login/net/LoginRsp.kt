package com.cainiao.login.net

import androidx.annotation.Keep

/**
 * 查询手机号码是否注册的响应
 */
@Keep
data class RegisterRsp(val is_register: Int = FLAG_UN_REGISTERED) {
    companion object {
        // 1 ： 注册  0:未注册
        const val FLAG_IS_REGISTERED = 1
        const val FLAG_UN_REGISTERED = 0
    }
}

/**
 * 登陆接口响应
 */
@Keep
data class LoginRsp(val course_permission: Boolean, val token: String, val user: User) {

    @Keep
    data class User(val id: Int, val logo_url: String?, val reg_time: String?, val username: String?)
}