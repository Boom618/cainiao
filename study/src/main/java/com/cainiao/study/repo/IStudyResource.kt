package com.cainiao.study.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.cainiao.study.net.StudiedRsp
import com.cainiao.study.net.StudyInfoRsp
import kotlinx.coroutines.flow.Flow

interface IStudyResource {

    val liveStudyInfo: LiveData<StudyInfoRsp>

    /**
     * 学习情况
     */
    suspend fun getStudyInfo()

    /**
     * 最近学习列表
     */
    suspend fun getStudyList(): Flow<PagingData<StudiedRsp.Data>>

    /**
     * 购买的课程
     */
    suspend fun getBoughtCourse(): Flow<PagingData<StudiedRsp.Data>>
}