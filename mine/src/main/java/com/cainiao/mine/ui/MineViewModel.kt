package com.cainiao.mine.ui

import com.cainiao.common.base.BaseViewModel
import com.cainiao.mine.repo.IMineResource

class MineViewModel(private val repo: IMineResource) : BaseViewModel() {


    // 用在userInfoFragment中
    val liveInfo = repo.liveUserInfo

    //  获取用户信息
    fun getUserInfo(token: String?) = serverAwait {
        token?.let {
            repo.getUserInfo(it)
        }
    }

}