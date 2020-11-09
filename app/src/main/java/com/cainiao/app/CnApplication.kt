package com.cainiao.app

import com.alibaba.android.arouter.launcher.ARouter
import com.cainiao.common.BaseApplication
import com.cainiao.common.ktx.application
import com.cainiao.login.moduleLogin
import com.cainiao.mine.moduleMine
import com.cainiao.service.assistant.AssistantApp
import com.cainiao.service.moduleService
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

/**
 * @author boomhe on 2020/9/24.
 */
class CnApplication : BaseApplication() {

    private val modules = arrayListOf<Module>(
        moduleService, /*moduleHome,*/ moduleLogin, moduleMine
    )

    override fun initConfig() {
        super.initConfig()
        //添加common 模块之外的其他模块，组件的koin的modules
        loadKoinModules(modules)

        AssistantApp.initConfig(application)

        ARouter.init(application)
    }
}
