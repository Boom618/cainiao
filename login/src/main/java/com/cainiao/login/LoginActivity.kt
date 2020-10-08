package com.cainiao.login

import androidx.activity.viewModels
import com.cainiao.common.base.BaseActivity
import com.cainiao.login.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels { defaultViewModelProviderFactory }


    override fun getLayoutRes(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        super.initView()
        mBinding.apply {
            vm = viewModel
        }
    }

    override fun initConfig() {
        super.initConfig()
    }

    override fun initData() {
        super.initData()
    }
}
