package com.cainiao.login

import com.cainiao.common.BaseApplication
import org.koin.core.context.loadKoinModules

class LoginApplication : BaseApplication() {


    override fun initConfig() {
        super.initConfig()
//        loadKoinModules(moduleService)
        loadKoinModules(modelLogin)

    }

}