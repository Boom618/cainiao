package com.cainiao.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.cainiao.app.databinding.ActivityMainBinding
import com.cainiao.app.ui.home.HomeFragment
import com.cainiao.common.base.BaseActivity
import com.cainiao.course.CourseFragment
import com.cainiao.mine.MineFragment
import com.cainiao.study.StudyFragment
import com.google.android.material.tabs.TabLayoutMediator

/**
 * ActivityMainBinding: 是根据 XML 文件中 layout 包裹后自动生成的
 * activity_main 加上后缀 Binding。
 */
//class MainActivity : AppCompatActivity() {
//class MainActivity : BaseActivity(R.layout.activity_main) {
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutRes() = R.layout.activity_main

    companion object {
        const val INDEX_HOME = 0
        const val INDEX_COURSE = 1
        const val INDEX_STUDY = 2
        const val INDEX_MINE = 3
    }

    private val fragments = mapOf<Int, Fragment>(
        INDEX_HOME to HomeFragment(),
        INDEX_COURSE to CourseFragment(),
        INDEX_STUDY to StudyFragment(),
        INDEX_MINE to MineFragment(),
    )

    override fun initView() {
        super.initView()
//        val navController = findNavController(R.id.fcv_main)
//        mBinding.bnvMain.setupWithNavController(navController)
        mBinding.apply {
            vp2Main.adapter = MainViewPageAdapter(this@MainActivity, fragments)
            // viewPage 与 fragment 绑定
            vp2Main.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    bnvMain.selectedItemId = when (position) {
                        INDEX_HOME -> R.id.homeFragment
                        INDEX_COURSE -> R.id.courseFragment
                        INDEX_STUDY -> R.id.studyFragment
                        INDEX_MINE -> R.id.mineFragment
                        else -> error("viewPage2 数组越界 position = $position")
                    }
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })
            // TabLayoutMediator 可以与 ViewPager2 关联
            // BottomNavigationView 的点击事件反向作用于 ViewPage 上
            bnvMain.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.homeFragment -> vp2Main.currentItem = INDEX_HOME
                    R.id.courseFragment -> vp2Main.currentItem = INDEX_COURSE
                    R.id.studyFragment -> vp2Main.currentItem = INDEX_STUDY
                    R.id.mineFragment -> vp2Main.currentItem = INDEX_MINE
                    else -> error("底部 navigation 与 ViewPage 绑定失败")
                }
                // TODO 忘记写返回值 卡了 半小时
                true
            }
        }

    }

    override fun initConfig() {
        super.initConfig()
    }

    override fun initData() {
        super.initData()
    }
}


// viewPage2 适配器
class MainViewPageAdapter(
    fragmentActivity: FragmentActivity,
    private val fragments: Map<Int, Fragment>
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position] ?: error("数据和 ViewPage 不匹配")
    }

}