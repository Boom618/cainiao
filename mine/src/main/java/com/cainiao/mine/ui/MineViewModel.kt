package com.cainiao.mine.ui

import androidx.lifecycle.MutableLiveData
import com.cainiao.common.base.BaseViewModel
import com.cainiao.mine.net.UserInfoRsp
import com.cainiao.service.repo.CniaoUserInfo

class MineViewModel : BaseViewModel() {

    // 用在MineFragment中
    val liveUser = MutableLiveData<CniaoUserInfo>()

    // 用在userInfoFragment中
    val liveInfo = MutableLiveData<UserInfoRsp>()
}