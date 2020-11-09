package com.cainiao.mine

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleMine = module {

    viewModel { MineViewModel() }
}