package com.cainiao.login

import com.cainiao.common.network.KtRetrofit
import com.cainiao.common.utils.getBaseHost
import com.cainiao.login.net.LoginService
import com.cainiao.login.repo.ILoginResource
import com.cainiao.login.repo.LoginRepo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * @author boomhe on 2020/10/12.
 */
val moduleLogin = module {

    // Koin  注解注入
    // 1、retrofit interface LoginService
    single {
//        KtRetrofit.initConfig("https://course.api.cniao5.com/").getService(LoginService::class.java)
        get<KtRetrofit> { parametersOf(getBaseHost()) }.getService(LoginService::class.java)

    }

    // 2、repo ILoginResource
    single { LoginRepo(get()) } bind ILoginResource::class

    // 3、view module
    viewModel { LoginViewModel(get()) }
}