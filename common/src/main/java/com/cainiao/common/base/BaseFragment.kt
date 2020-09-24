package com.cainiao.common.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @author boomhe on 2020/9/24.
 * Fg 抽象类 基类封装
 */
abstract class BaseFragment : Fragment {
    // 如果不是  ：Fragment() 需要显示调用构造函数
    constructor() : super()

    constructor(@LayoutRes layout: Int) : super(layout)

    protected fun <T : Any> LiveData<T>.observeKt(block: (T) -> Unit) {
        this.observe(viewLifecycleOwner, Observer {
            block(it)
        })
    }

}