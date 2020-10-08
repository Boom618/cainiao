package com.cainiao.jetpack

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.HandlerThread
import android.service.autofill.UserData
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.blankj.utilcode.util.LogUtils
import com.cainiao.jetpack.databinding.ActivityMainBinding
import com.cainiao.jetpack.db.UserDataBase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_login)
        // DataBind 模式 关联 视图
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // LiveData 是一个可观察的对象
        val instance = UserDataBase.getInstance(this)
//        instance.getUserDao().query().observe(this, Observer {
//
//        })
        instance.getStateDao().query().observe(this, {
            LogUtils.d("it = $it")
            if (it != true) {
                // 没有登录，去登录界面 Nav 方式
                Navigation.findNavController(this, R.id.nav_host_main)
                    .navigate(R.id.nav_to_login)
            }
        })
        val ht = HandlerThread("h-t")


    }
}
