package com.cainiao.mine

import androidx.lifecycle.MutableLiveData
import com.cainiao.common.base.BaseViewModel
import com.cainiao.service.repo.CniaoUserInfo

class MineViewModel : BaseViewModel() {

    val liveUser = MutableLiveData<CniaoUserInfo>()
}