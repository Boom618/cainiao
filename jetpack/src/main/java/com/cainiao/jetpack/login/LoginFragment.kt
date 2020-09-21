package com.cainiao.jetpack.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cainiao.jetpack.R
import com.cainiao.jetpack.databinding.FragmentLoginBinding

/**
 * @author boomhe on 2020/9/18.
 *  onAttach -> onCreate -> onCreateView -> onActivityCreated -> onStart
 */
class LoginFragment : Fragment() {

    private var loginViewModel:LoginViewModel?= null
    // 调用一次
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(viewModelStore,
            defaultViewModelProviderFactory)[LoginViewModel::class.java]
            .apply {
                loginOK.observe(this@LoginFragment, Observer {
                    if (it) {
                        // 登录成功 去主页
                        Navigation.findNavController(requireActivity(), R.id.nav_host_main).navigateUp()
                    }
                })
            }



    }

    // 每次进来都会调用
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // 配置文件中开启 dataBinding = true
        val inflate = FragmentLoginBinding.inflate(inflater, container, false)
        inflate.vm = loginViewModel
        // 得到 视图
        return inflate.root
    }
}