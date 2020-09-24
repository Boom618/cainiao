package com.cainiao.common.ktx

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

/**
 * @author boomhe on 2020/9/24.
 * Activity 相关扩展函数
 */

/**
 * 简化 Activity 在DataBinding 时 setContentView 操作
 * @return 返回 binding 对象实例
 */
fun <T : ViewDataBinding> Activity.bindView(@LayoutRes layout: Int): T {
    return DataBindingUtil.setContentView(this, layout)
}

fun <T : ViewDataBinding> Activity.bindView(view: View): T? {
    return DataBindingUtil.bind<T>(view)
}

/**
 * 扩展属性 LifecycleOwner 便于 FG 和 AC 直接的传递
 */
val ComponentActivity.viewLifecycleOwner: LifecycleOwner
    get() = this

/**
 * 扩展属性 context
 */
val Activity.context: Context get() = this

/**
 * 扩展沉侵式状态栏
 * 需要在 setContentView 之前调用
 */
fun Activity.immediateStatusBar() {
    window.apply {
        addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
        addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}

/**
 * 隐藏键盘
 */
fun Activity.dismissKeyBoard(view: View) {
    // as? : 安全转换运算符
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager?
    imm?.hideSoftInputFromWindow(view.windowToken, 0)

}
