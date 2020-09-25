package com.cainiao.common.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @author boomhe on 2020/9/24.
 * Ac 基类
 */
abstract class BaseActivity : AppCompatActivity {

    constructor() : super()

    // 不需要手动调用  setContentView ，在构造函数中直接传入
    constructor(@LayoutRes layout: Int) : super(layout)

    /**
     * 扩展 LiveData 的 Observer 函数
     */
    protected fun <T : Any> LiveData<T>.observeKt(block: (T?) -> Unit) {
        // 只有一个函数参数时
        this.observe(this@BaseActivity, Observer {
            // 方式一：
            // block.invoke(it)
            // 方式二：
            block(it)
        })

    }
}