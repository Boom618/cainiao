package com.cainiao.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.LogUtils
import com.cainiao.common.ktx.bindView
import com.cainiao.common.ktx.viewLifecycleOwner

/**
 * @author boomhe on 2020/9/24.
 * Ac 基类
 */
abstract class BaseActivity<ActBinding : ViewDataBinding> : AppCompatActivity {

    constructor() : super()

    // 不需要手动调用  setContentView ，在构造函数中直接传入
    constructor(@LayoutRes layout: Int) : super(layout)

    protected lateinit var mBinding: ActBinding

    @LayoutRes
    protected abstract fun getLayoutRes(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 常规写法
        // mBinding = DataBindingUtil.setContentView(this, getLayoutRes())
        mBinding = bindView<ActBinding>(getLayoutRes()).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        initView()
        initConfig()
        initData()
    }

    open fun initView() {
        LogUtils.d("${this.javaClass.simpleName} 初始化 initView")
    }

    open fun initConfig() {
        LogUtils.d("${this.javaClass.simpleName} 初始化 initConfig")
    }

    open fun initData() {
        LogUtils.d("${this.javaClass.simpleName} 初始化 initData")
    }


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

    override fun onDestroy() {
        super.onDestroy()
        // 判断懒加载 mBinding 初始化
        if (::mBinding.isInitialized) {
            mBinding.unbind()
        }
    }
}