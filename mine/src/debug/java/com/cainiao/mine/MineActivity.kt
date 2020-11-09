package com.cainiao.mine

import android.graphics.Color
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.ToastUtils
import com.cainiao.common.base.BaseActivity
import com.cainiao.mine.databinding.ActivityMineBinding
import com.cainiao.mine.widget.ItemSettingsBean

class MineActivity : BaseActivity<ActivityMineBinding>() {

    override fun getLayoutRes() = R.layout.activity_mine

    override fun initView() {
        super.initView()

        mBinding.apply {

            val ib = ItemSettingsBean(title = "学习卡")

            val obBean = ObservableField(ib)
            // bean  : XML 中定义的变量
            bean = obBean

            ib.title = "你的学习卡"
            ib.titleColor = Color.RED

            ib.arrowColor = R.color.colorPrimary

            ib.iconRes = "https://www.easyicon.net/api/resizeApi.php?id=1283371&size=96"



//            isvCard.onClickArrow {
//                ToastUtils.showShort(" onClickArrow ")
//            }

            isvCard.setOnClickListener {
                ToastUtils.showShort("item onClick")
            }
        }
    }
}