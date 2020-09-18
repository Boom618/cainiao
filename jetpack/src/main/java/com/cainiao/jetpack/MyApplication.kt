package com.cainiao.jetpack

import android.app.Application
import com.blankj.utilcode.util.Utils

/**
 * @author boomhe on 2020/9/18.
 */
class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}