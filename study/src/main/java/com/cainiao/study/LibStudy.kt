package com.cainiao.study
import com.cainiao.common.network.KtRetrofit
import com.cainiao.common.utils.getBaseHost
import com.cainiao.study.net.StudyService
import com.cainiao.study.repo.IStudyResource
import com.cainiao.study.repo.StudyRepo
import com.cainiao.study.ui.StudyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

val moduleStudy = module {

    // service retrofit
    single {
        get<KtRetrofit> { parametersOf(getBaseHost()) }.getService(StudyService::class.java)
    }

    //repo LoginResource

    single { StudyRepo(get<StudyService>()) } bind IStudyResource::class

    viewModel { StudyViewModel(get()) }
}