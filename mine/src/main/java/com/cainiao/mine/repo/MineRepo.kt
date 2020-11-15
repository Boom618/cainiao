package com.cainiao.mine.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.cainiao.common.network.support.serverData
import com.cainiao.common.network.support.serverRsp
import com.cainiao.mine.net.MineService
import com.cainiao.mine.net.UserInfoRsp
import com.cainiao.service.network.onBizError
import com.cainiao.service.network.onBizOK
import com.cainiao.service.network.onFailure
import com.cainiao.service.network.onSuccess

class MineRepo(private val service: MineService) : IMineResource {

    private val _userInfoRsp = MutableLiveData<UserInfoRsp>()

    override val liveUserInfo: LiveData<UserInfoRsp> = _userInfoRsp

    override suspend fun getUserInfo(token: String?) {

        service.getUserInfo(token)
            .serverData()
            .onSuccess {
                //只要不是接口响应成功，
                onBizError { code, message ->
                    LogUtils.w("获取用户信息 BizError $code,$message")
                }
                onBizOK<UserInfoRsp> { code, data, message ->
                    _userInfoRsp.value = data
                    LogUtils.i("获取用户信息 BizOK $data")
                    return@onBizOK
                }
            }
            .onFailure {
                LogUtils.e("获取用户信息 接口异常 ${it.message}")
            }

    }
}