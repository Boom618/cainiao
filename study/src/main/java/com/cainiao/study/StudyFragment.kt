package com.cainiao.study

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.blankj.utilcode.util.LogUtils
import com.cainiao.common.base.BaseFragment
import com.cainiao.service.repo.CniaoDbHelper
import com.cainiao.study.databinding.FragmentStudyBinding
import com.cainiao.study.ui.StudyLoadStateAdapter
import com.cainiao.study.ui.StudyViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author boomhe on 2020/9/25.
 */
class StudyFragment : BaseFragment() {

    private val viewModel: StudyViewModel by viewModel()

    override fun getLayoutRes() = R.layout.fragment_study


    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentStudyBinding.bind(view).apply {
            vm = viewModel
        }
    }

    override fun initData() {
        super.initData()
        //观察数据库中的userInfo

        CniaoDbHelper.getLiveUserInfo(requireContext())
            .observeKt {
                viewModel.obUserInfo.set(it)
            }
        viewModel.getStudyData()
        //获取到最近学习的数据列表
        viewModel.apply {
            // 添加头
            adapter.withLoadStateFooter(footer = StudyLoadStateAdapter())
            adapter.addLoadStateListener { state ->
                when (state.refresh) {
                    is LoadState.NotLoading -> {
                    }
                    is LoadState.Loading -> {
                    }
                    is LoadState.Error -> {
                    }
                }
                LogUtils.i("loadState $state")
            }
            lifecycleScope.launchWhenStarted {
                studiedList().asFlow().collectLatest {
                    adapter.submitData(lifecycle, it)
                }
            }
        }
    }
}