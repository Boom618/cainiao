package com.cainiao.common

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @author boomhe on 2020/9/24.
 */
abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Koin 依赖注解
        startKoin {
            androidLogger(Level.ERROR)

            androidContext(this@BaseApplication)
        }

        initConfig()
        initData()

//        LogUtils.d("BaseApplication onCreate")
    }

    // 实现子类的必要配置
    protected open fun initConfig() {}

    // 实现子类的数据初始化
    protected open fun initData() {}

}