package com.cainiao.mine

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.cainiao.common.base.BaseFragment
import com.cainiao.mine.databinding.FragmentMineBinding
import com.cainiao.service.repo.CniaoDbHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author boomhe on 2020/9/25.
 */
class MineFragment : BaseFragment() {

    private val viewModel: MineViewModel by viewModel()

    override fun getLayoutRes() = R.layout.fragment_mine

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentMineBinding.bind(view).apply {
            vm = viewModel
            // UI操作 登出
            btnLogoutMine.setOnClickListener {
                CniaoDbHelper.deleteUserInfo(requireContext())
                ARouter.getInstance().build("/login/login").navigation()
            }

        }
    }


    override fun initData() {
        super.initData()
        CniaoDbHelper.getLiveUserInfo(requireContext()).observeKt {
            viewModel.liveUser.value = it
        }
    }
}