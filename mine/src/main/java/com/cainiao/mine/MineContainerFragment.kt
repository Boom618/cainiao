package com.cainiao.mine

import android.os.Bundle
import android.view.View
import com.cainiao.common.base.BaseFragment
import com.cainiao.mine.databinding.FragmentContainerMineBinding

/**
 * Mine 模块的主Fragment  用于内部navigation的容器
 */
class MineContainerFragment : BaseFragment() {

    override fun getLayoutRes() = R.layout.fragment_container_mine

    override fun bindView(view: View, savedInstanceState: Bundle?) =
        FragmentContainerMineBinding.bind(view).apply {

        }


}