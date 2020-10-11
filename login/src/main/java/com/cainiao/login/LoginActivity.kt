package com.cainiao.login

import androidx.activity.viewModels
import com.cainiao.common.base.BaseActivity
import com.cainiao.login.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    //private val viewModel: LoginViewModel by viewModels { defaultViewModelProviderFactory }
    private val viewModel: LoginViewModel by viewModel()


    override fun getLayoutRes(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        super.initView()
        mBinding.apply {
            vm = viewModel
            //mtoobar_login.setNavigationOnClickListener { finish() }
            //mtoolbarLogin.setNavigationOnClickListener { finish() }
        }
    }

    override fun initConfig() {
        super.initConfig()
    }

    override fun initData() {
        super.initData()
    }
}
