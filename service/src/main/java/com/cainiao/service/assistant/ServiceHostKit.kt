package com.cainiao.service.assistant

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.ToastUtils
import com.cainiao.common.utils.*
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

    private val hostMap = mapOf(
        "开发环境Host" to HOST_DEV,
        "QA测试Host" to HOST_QA,
        "线上正式Host" to HOST_PRODUCT,
    )

    private val hosts = hostMap.values.toTypedArray()
    private val names = hostMap.keys.toTypedArray()

    override fun onAppInit(context: Context?) {
    }

    override fun onClick(context: Context?) {
        // 当前选择的 dialog 位置
        val pos = hostMap.values.indexOf(getBaseHost()) % hostMap.size
        // 弹窗 用于选择 不同的 host
        context ?: return ToastUtils.showShort("context is null ~")
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.str_server_host_dokit))
            .setSingleChoiceItems(names, pos) { dialog, which ->
                setBaseHost(hosts[which])
                dialog.dismiss()
            }
            .show()
    }
}