package com.cainiao.service.assistant

import android.content.Context
import com.cainiao.service.R
import com.didichuxing.doraemonkit.kit.AbstractKit

/**
 * @author boomhe on 2020/9/25.
 *
 * 用于配置切换不同的接口 host，调试工具
 *
 * 自定义功能组件（可选）
 */
class ServiceHostKit : AbstractKit() {

    override val icon: Int = R.drawable.icon_server_host
    override val name: Int get() = R.string.str_server_host_dokit

    override fun onAppInit(context: Context?) {
    }

    override fun onClick(context: Context?) {
    }
}