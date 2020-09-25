package com.cainiao.app

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cainiao.app.databinding.ActivityMainBinding
import com.cainiao.common.base.BaseActivity

/**
 * ActivityMainBinding: 是根据 XML 文件中 layout 包裹后自动生成的
 * activity_main 加上后缀 Binding。
 */
//class MainActivity : AppCompatActivity() {
//class MainActivity : BaseActivity(R.layout.activity_main) {
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutRes() = R.layout.activity_main

    override fun initView() {
        super.initView()
        val navController = findNavController(R.id.fcv_main)
        mBinding.bnvMain.setupWithNavController(navController)
    }

    override fun initConfig() {
        super.initConfig()
    }

    override fun initData() {
        super.initData()
    }
}