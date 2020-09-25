package com.cainiao.study

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.cainiao.common.base.BaseFragment
import com.cainiao.study.databinding.FragmentStudyBinding

/**
 * @author boomhe on 2020/9/25.
 */
class StudyFragment : BaseFragment() {
    override fun getLayoutRes() = R.layout.fragment_study

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentStudyBinding.bind(view)
    }
}