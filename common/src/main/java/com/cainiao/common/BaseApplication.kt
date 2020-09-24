package com.cainiao.common

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @author boomhe on 2020/9/24.
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Koin 依赖注解
        startKoin {
            androidLogger(Level.ERROR)

            androidContext(this@BaseApplication)
        }
    }
}