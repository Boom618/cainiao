package com.cainiao.study.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.blankj.utilcode.util.LogUtils
import com.cainiao.common.network.support.serverData
import com.cainiao.service.network.onBizError
import com.cainiao.service.network.onBizOK
import com.cainiao.service.network.onFailure
import com.cainiao.service.network.onSuccess
import com.cainiao.study.net.StudiedRsp
import com.cainiao.study.net.StudyInfoRsp
import com.cainiao.study.net.StudyService
import com.cainiao.study.repo.data.BoughtItemPagingSource
import com.cainiao.study.repo.data.StudiedItemPagingSource
import kotlinx.coroutines.flow.Flow

class StudyRepo(private val service: StudyService) : IStudyResource {


    private val _studyInfo = MutableLiveData<StudyInfoRsp>()


    override val liveStudyInfo: LiveData<StudyInfoRsp>
        get() = _studyInfo

    // 页码大小
    private val pageSize = 10

    override suspend fun getStudyInfo() {
        service.getStudyInfo()
            .serverData()
            .onSuccess {
                onBizError { code, message ->
                    LogUtils.w("获取学习信息 BizError $code,$message")
                }
                onBizOK<StudyInfoRsp> { code, data, message ->
                    _studyInfo.value = data
                    LogUtils.i("获取学习信息 BizOK $data")
                    return@onBizOK
                }
            }
            .onFailure {
                LogUtils.e("获取学习信息 接口异常 ${it.message}")
            }

    }

    override suspend fun getStudyList(): Flow<PagingData<StudiedRsp.Data>> {

        val config =
            PagingConfig(
                pageSize = pageSize,
                prefetchDistance = 5,
                initialLoadSize = 10,
                maxSize = pageSize * 3
            )

        return Pager(config = config, null) {
            StudiedItemPagingSource(service)
        }.flow


    }

    override suspend fun getBoughtCourse(): Flow<PagingData<StudiedRsp.Data>> {

        val config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = 5,
            initialLoadSize = 10,
            maxSize = pageSize * 3
        )
        return Pager(config, null) {
            BoughtItemPagingSource(service)
        }.flow
    }

}