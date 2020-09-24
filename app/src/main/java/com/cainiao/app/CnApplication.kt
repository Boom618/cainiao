package com.cainiao.app

import android.app.Application
import com.cainiao.app.di.cnModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

/**
 * @author boomhe on 2020/9/24.
 */
class CnApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        // koin 注解
        startKoin {
            // 除了level.error外，使用 android logger 会导致崩溃
            androidLogger(Level.ERROR)
            // context
            androidContext(this@CnApplication)
            // assets 资源文件
            androidFileProperties("ass.file")
            // 加载需要的 model
            modules(cnModules)
        }

        // 加载外部 model
        loadKoinModules(otherModel)
    }
}

val otherModel = module {

}