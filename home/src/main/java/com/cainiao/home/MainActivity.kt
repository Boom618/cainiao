package com.cainiao.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.cainiao.common.network.OkHttpApi
import com.cainiao.common.network.support.IHttpCallback
import com.cainiao.common.network.KtRetrofit
import com.cainiao.common.network.model.*
import com.cainiao.common.network.support.serverRsp
import com.cainiao.common.network.support.toLiveData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val httpApi = OkHttpApi.getInstance()
        httpApi.get(emptyMap(), "https://course.api.cniao5.com/member/userinfo", object :
            IHttpCallback {
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

        httpApi.post(req, "https://course.api.cniao5.com/accounts/course/10301/login", object :
            IHttpCallback {
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

        // ======= 封装 Retrofit 接口

        // userInfo 接口
        val baseUrl = "https://course.api.cniao5.com/"
        val service = KtRetrofit.initConfig(baseUrl)
            .getService(CniaoService::class.java)

        val retrofitCall = service.userInfo()

        val liveInfo = retrofitCall.toLiveData()
        liveInfo.observe(this, { LogUtils.d("retrofit info ${it.toString()}") })

        // userInfo2 接口
        KtRetrofit.initConfig(baseUrl).getService(CniaoService::class.java)
            .userInfo2().observe(this, {
                LogUtils.d("retrofit liveRsp $it")
            })

        // login 接口
        val login = service.login(LoginReq())
        lifecycleScope.launch {
            when (val serverRsp = login.serverRsp()) {
                is ApiSuccessResponse -> {
                    LogUtils.d("apiService ${serverRsp.body.data.toString()}")
                }
                is ApiErrorResponse -> {
                    LogUtils.d("apiService error ${serverRsp.errorMsg}")
                }
                is ApiEmptyResponse -> LogUtils.d("data null")
            }
        }
    }


    val req = LoginReq()

    data class LoginReq(
        val mobi: String = "18648957777",
        val password: String = "cn5123456"
    )
}

interface CniaoService {

    @POST("accounts/course/10301/login")
    fun login(@Body body: MainActivity.LoginReq): Call<NetResponse>

    @GET("member/userinfo")
    fun userInfo(): Call<NetResponse>

    @GET("member/userinfo")
    fun userInfo2(): LiveData<ApiResponse<NetResponse>>

}
