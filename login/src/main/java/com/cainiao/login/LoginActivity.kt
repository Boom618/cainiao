package com.cainiao.login

import com.blankj.utilcode.util.ToastUtils
import com.cainiao.common.base.BaseActivity
import com.cainiao.login.databinding.ActivityLoginBinding
import com.cainiao.login.net.RegisterRsp
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
        viewModel.apply {
            liveRegisterRsp.observeKt {
                if (it?.is_register == RegisterRsp.FLAG_IS_REGISTERED) {
                    repoLogin()
                }
            }
            liveLoginRsp.observeKt {
                ToastUtils.showShort("登录结果 token = ${it?.token}")
            }
        }
    }

    override fun initData() {
        super.initData()
    }
}
