package com.cainiao.mine.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import com.cainiao.common.base.BaseFragment
import com.cainiao.mine.R
import com.cainiao.mine.databinding.FragmentUserInfoBinding
import com.cainiao.service.repo.CniaoDbHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author boomhe on 2020/11/12.
 */
class UserInfoFragment : BaseFragment() {

    private val viewModel: MineViewModel by viewModel()

    override fun getLayoutRes() = R.layout.fragment_user_info

    override fun bindView(view: View, savedInstanceState: Bundle?) =
        FragmentUserInfoBinding.bind(view).apply {
            vm = viewModel
            // tool bar
            toolbarUserInfo.setNavigationOnClickListener { findNavController().navigateUp() }
            // 保存返回
            btnSaveUserInfo.setOnClickListener { findNavController().navigateUp() }
        }


    override fun initData() {
        super.initData()
        CniaoDbHelper.getLiveUserInfo(requireContext()).observeKt {
            // 获取用户的 userInfo 的接口数据
        }

    }
}