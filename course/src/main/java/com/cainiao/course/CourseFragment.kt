package com.cainiao.course

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.cainiao.common.base.BaseFragment
import com.cainiao.course.databinding.FragmentCourseBinding

/**
 * @author boomhe on 2020/9/25.
 */
class CourseFragment : BaseFragment() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_course
    }

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentCourseBinding.bind(view)
    }
}