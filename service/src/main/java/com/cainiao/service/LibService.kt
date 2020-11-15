package com.cainiao.service

import com.cainiao.common.network.KtRetrofit
import org.koin.dsl.module

val moduleService = module {

    single<KtRetrofit> { (host: String) -> KtRetrofit.initConfig(host) }
}