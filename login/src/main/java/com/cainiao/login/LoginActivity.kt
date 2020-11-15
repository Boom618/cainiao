package com.cainiao.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.cainiao.common.base.BaseActivity
import com.cainiao.common.ktx.context
import com.cainiao.common.network.config.SP_KEY_USER_TOKEN
import com.cainiao.common.utils.CniaoSpUtils
import com.cainiao.login.databinding.ActivityLoginBinding
import com.cainiao.login.net.RegisterRsp
import com.cainiao.service.repo.CniaoDbHelper
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

@Route(path = "/login/login")
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
            mtoobar_login.setNavigationOnClickListener { finish() }
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
            liveLoginRsp.observeKt { resp ->
                ToastUtils.showShort("登录结果 token = ${resp?.token}")

                // 插入数据到数据库
                resp?.let {
                    CniaoDbHelper.insertUserInfo(context, it)
                    // 腾讯 MMKV 存储
                    CniaoSpUtils.put(SP_KEY_USER_TOKEN, it.token)
                }

                // 关闭页面
                finish()
            }
        }
    }
}
