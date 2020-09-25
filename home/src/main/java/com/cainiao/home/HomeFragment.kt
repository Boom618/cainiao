package com.cainiao.home

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.cainiao.common.base.BaseFragment
import com.cainiao.home.databinding.FragmentHomeBinding

/**
 * @author boomhe on 2020/9/25.
 */
class HomeFragment : BaseFragment() {
    override fun getLayoutRes() = R.layout.fragment_home

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentHomeBinding.bind(view)
    }
}