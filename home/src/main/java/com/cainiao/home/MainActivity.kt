package com.cainiao.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.cainiao.netlibrary.IHttpCallback
import com.cainiao.netlibrary.OkHttpApi
import com.cainiao.netlibrary.model.NetResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val httpApi = OkHttpApi()
        httpApi.get(emptyMap(), "https://course.api.cniao5.com/member/userinfo", object : IHttpCallback {
            override fun onSuccess(data: Any?) {
                LogUtils.e(data.toString())
                runOnUiThread {
                    textView.text = data.toString()
                }
            }

            override fun onFailed(error: Any?) {
                LogUtils.e(error.toString())
            }
        })

        httpApi.post(req, "https://course.api.cniao5.com/accounts/course/10301/login", object : IHttpCallback {
            override fun onSuccess(data: Any?) {
                LogUtils.d("success result : ${data.toString()}")
                runOnUiThread {
                    val dataString = data.toString()
                    val (code, dataObj, message) =
                        GsonUtils.fromJson<NetResponse>(dataString, NetResponse::class.java)
                    textView.text = dataObj.toString()
                }
            }

            override fun onFailed(error: Any?) {
                LogUtils.e(error.toString())
            }

        })
    }


    val req = LoginReq()

    data class LoginReq(
        val mobi: String = "18648957777",
        val password: String = "cn5123456"
    )
}
