package com.cainiao.common.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * @author boomhe on 2020/9/24.
 * 创建一个 空 的 liveData 对象
 */
class AbsentLiveData<T> private constructor() : MutableLiveData<T>() {

    init {
        postValue(null)
    }

    companion object {
        fun <T : Any> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }

}