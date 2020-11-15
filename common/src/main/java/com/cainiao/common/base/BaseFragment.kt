package com.cainiao.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.LogUtils

/**
 * @author boomhe on 2020/9/24.
 * Fg 抽象类 基类封装
 */
abstract class BaseFragment : Fragment {
    // 如果不是  ：Fragment() 需要显示调用构造函数
    constructor() : super()

    constructor(@LayoutRes layout: Int) : super(layout)

    // UI 的 ViewDataBinding 对象
    private var mBinding: ViewDataBinding? = null

    @LayoutRes
    abstract fun getLayoutRes(): Int

    // onAttach -> onCreate -> onCreateView ->  onViewCreated -> onActivityCreated -> onStart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ViewDataBinding
        mBinding = bindView(view, savedInstanceState)
        mBinding?.lifecycleOwner = viewLifecycleOwner

        initConfig()
        initData()
    }


    open fun initConfig() {
//        LogUtils.d("${this.javaClass.simpleName} 初始化 initConfig")
    }

    open fun initData() {
//        LogUtils.d("${this.javaClass.simpleName} 初始化 initData")
    }

    abstract fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding


    protected fun <T : Any> LiveData<T>.observeKt(block: (T?) -> Unit) {
        this.observe(viewLifecycleOwner, Observer {
            block(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding?.unbind()
    }

}