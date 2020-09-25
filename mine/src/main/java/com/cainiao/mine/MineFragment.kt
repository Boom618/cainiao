package com.cainiao.mine

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.cainiao.common.base.BaseFragment
import com.cainiao.mine.databinding.FragmentMineBinding

/**
 * @author boomhe on 2020/9/25.
 */
class MineFragment : BaseFragment() {
    override fun getLayoutRes() = R.layout.fragment_mine

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentMineBinding.bind(view)
    }

}