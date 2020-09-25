package com.cainiao.app

import com.cainiao.common.BaseApplication
import com.cainiao.common.ktx.application
import com.cainiao.service.assistant.AssistantApp

/**
 * @author boomhe on 2020/9/24.
 */
class CnApplication:BaseApplication() {

    override fun initConfig() {
        super.initConfig()

        //doKit的初始化配置
        AssistantApp.initConfig(application)
    }
}
