package com.cainiao.common.widget

import android.view.MenuItem
import androidx.core.view.forEachIndexed
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * @author boomhe on 2020/9/30.
 * 封装 ViewPage 和 Nav 关系，中介者
 *  config: 函数参数，可配置化
 */
class BnvMediator(
    private val bvn: BottomNavigationView,
    private val viewPager2: ViewPager2,
    private val config: ((bvn: BottomNavigationView, viewPager2: ViewPager2) -> Unit)? = null
) {

    private val map = mutableMapOf<MenuItem, Int>()

    init {
        bvn.menu.forEachIndexed { index, item ->
            map[item] = index
        }
    }

    fun attach() {

        config?.invoke(bvn, viewPager2)
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bvn.selectedItemId = bvn.menu.getItem(position).itemId
            }
        })
        // BottomNavigationView 的点击事件反向作用于 ViewPage 上
        bvn.setOnNavigationItemSelectedListener {
            viewPager2.currentItem = map[it] ?: error("底部 navigation 与 ViewPage 绑定失败")
            // TODO 忘记写返回值 卡了 半小时
            true
        }
    }
}