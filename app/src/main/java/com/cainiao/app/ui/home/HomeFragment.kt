package com.cainiao.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils
import com.cainiao.app.R
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.inject
import org.koin.core.qualifier.named

class HomeFragment : Fragment() {

    // 方式一：by lazy
    val homeViewModel1: HomeViewModel by ViewModelLazy(HomeViewModel::class,
        { viewModelStore }, { defaultViewModelProviderFactory })

    // 方式二 :
    val homeViewModel2: HomeViewModel by viewModels { defaultViewModelProviderFactory }

    // 方式三 :手动创建
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 2.0 + 版本方式获得 view model
        homeViewModel = ViewModelProvider(
            viewModelStore,
            defaultViewModelProviderFactory
        )[HomeViewModel::class.java]
        val root = inflater.inflate(R.layout.fragment_home1, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        LogUtils.d("HomeFragment", "onCreateView")
        return root
    }
}


class CnPerson() {
    fun running() {
        LogUtils.d("run ing")
    }
}

// 带参构造方法
class CnStudent(val name: String) {

    // 次构造函数，都必须调用主构造函数
    constructor(p: CnPerson) : this(p.toString())

    fun study() {
        LogUtils.i("Person", "CnStudent ${name}学习很努力！")
    }
}

// 接受外部参

class ViewInfo(private val view: View) : KoinComponent {

    val s: CnStudent by inject(named("name"))

    // 方式一 、方式二
    val p = get<CnPerson>()
    val p1: CnPerson = get()


    fun showId() {
        LogUtils.d("ViewInfo", "show view的id ${view.id} p $p  s ${s.name}")
    }
}