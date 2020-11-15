package com.cainiao.mine.repo

import androidx.lifecycle.LiveData
import com.cainiao.mine.net.UserInfoRsp

// Mine 模块的数据接口
interface IMineResource {

    // 用户信息 返回数据类 liveData
    val liveUserInfo: LiveData<UserInfoRsp>

    // 获取userInfo的api函数
    suspend fun getUserInfo(token: String?)
}