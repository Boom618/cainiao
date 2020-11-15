package com.cainiao.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @author boomhe on 2020/10/12.
 * ViewModel 公共基类
 */
abstract class BaseViewModel : ViewModel() {

    private val jobs = mutableListOf<Job>()

    // 网络 loading 状态
    val isLoading = MutableLiveData<Boolean>()

    /**
     * 协程 网络请求
     */
    protected fun serverAwait(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        isLoading.value = true
        block.invoke(this)
        isLoading.value = false
    }.addTo(jobs)

    override fun onCleared() {
        jobs.forEach {
            it.cancel()
        }
        super.onCleared()
    }
}

/**
 * 扩展函数  job 添加到 list
 */
private fun Job.addTo(list: MutableList<Job>) {
    list.add(this)
}