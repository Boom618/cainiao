package com.cainiao.service.assistant

import android.app.Application
import com.didichuxing.doraemonkit.DoraemonKit

/**
 * @author boomhe on 2020/9/25.
 * 配置 doKit 类
 */
object AssistantApp {

    private const val productId = "6b19135d27dba190054f15315540afe9"
    fun initConfig(application: Application) {
        DoraemonKit.install(application, mutableListOf(ServiceHostKit()), productId)
    }
}