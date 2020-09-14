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
        )
            .get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        LogUtils.d("HomeFragment", "onCreateView")
        return root
    }
}