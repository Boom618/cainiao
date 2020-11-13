package com.cainiao.app.di

import android.view.View
import com.cainiao.app.ui.dashboard.DashboardViewModel
import com.cainiao.app.ui.home.*
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.TypeQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * @author boomhe on 2020/9/24.
 *
 * Koin 依赖注解
 */
class CnInject {


}

/**
 * 使用 koin 依赖注入框架需要的必要声明
 */
@OptIn(KoinApiExtension::class)
val cnModules = module {

    // 单例模式
    single(createdAtStart = false) { CnPerson() }
    // 工厂模式、覆盖声明[该对象创建过，就覆盖]
    factory(override = true) { CnPerson() }

    // 多构造函数
    factory(named("name")) { CnStudent("hha") }

    factory(TypeQualifier(CnPerson::class)) { CnStudent(get<CnPerson>()) }


    // 外部参数
    factory { (view: View) ->
        ViewInfo(view)
    }

    // view model
    viewModel { DashboardViewModel() }
    viewModel { HomeViewModel() }

    //
    fragment { HomeFragment() }
}