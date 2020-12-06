package com.cainiao.study.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cainiao.common.base.BaseViewModel
import com.cainiao.service.repo.CniaoUserInfo
import com.cainiao.study.net.StudyInfoRsp
import com.cainiao.study.repo.IStudyResource

class StudyViewModel(private val repo: IStudyResource) : BaseViewModel() {

    // 学习页面的数据
    val liveStudyInfo: LiveData<StudyInfoRsp> = repo.liveStudyInfo

    //用户基本信息，头像和名字
    val obUserInfo = ObservableField<CniaoUserInfo>()


    // adapter
    val adapter = StudyPageAdapter()

    // 学习详情
    fun getStudyData() = serverAwait {
        repo.getStudyInfo()
    }

    // 学习列表
    suspend fun studiedList() =
        repo.getStudyList().asLiveData(viewModelScope.coroutineContext).cachedIn(viewModelScope)


    suspend fun boughtList() = repo.getBoughtCourse().asLiveData().cachedIn(viewModelScope)
}