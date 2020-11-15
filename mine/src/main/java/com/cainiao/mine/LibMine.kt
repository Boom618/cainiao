package com.cainiao.mine

import com.cainiao.common.network.KtRetrofit
import com.cainiao.common.utils.getBaseHost
import com.cainiao.mine.repo.IMineResource
import com.cainiao.mine.repo.MineRepo
import com.cainiao.mine.ui.MineViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

// Koin mine module
val moduleMine = module {

    single { get<KtRetrofit> { parametersOf(getBaseHost()) }.getService(KtRetrofit::class.java) }


    single { MineRepo(get()) } bind IMineResource::class

    viewModel { MineViewModel(get()) }
}