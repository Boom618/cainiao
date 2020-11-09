package com.cainiao.mine.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.annotation.Keep
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ObservableField
import com.cainiao.mine.R
import com.cainiao.mine.databinding.VItemSettingsBinding

/**
 * 自定义 itemSetting View  的组合控件
 *
 *  JvmOverloads  : 告诉虚拟机 重写出各个参数的构造方法
 */
class ItemSettingsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr) {


    private var itemBean = ItemSettingsBean()
    private val obItemInfo = ObservableField(itemBean)

    init {
        // 管理布局：组合控件  this ，true 关联到 root
        VItemSettingsBinding.inflate(LayoutInflater.from(context), this, true).apply {
            info = obItemInfo
        }
        setBackgroundColor(Color.WHITE)

        // 读取配置属性
        context.obtainStyledAttributes(attrs, R.styleable.ItemSettingsView).apply {
            // 标题
            itemBean.title = getString(R.styleable.ItemSettingsView_title) ?: ""
            val titleRGB = getColor(
                R.styleable.ItemSettingsView_titleColor,
                resources.getColor(R.color.colorPrimaryText)
            )
            itemBean.titleColor = titleRGB
            // icon
            itemBean.iconRes =
                getResourceId(R.styleable.ItemSettingsView_icon, R.drawable.ic_gift_card)
            val iconRGB = getColor(R.styleable.ItemSettingsView_iconColor, 0)
            itemBean.iconColor = iconRGB
            //desc
            itemBean.desc = getString(R.styleable.ItemSettingsView_desc) ?: ""
            val descRGB = getColor(R.styleable.ItemSettingsView_descColor, 0)
            itemBean.descColor = descRGB
            // arrow
            itemBean.arrowRes = getResourceId(R.styleable.ItemSettingsView_arrow, R.drawable.ic_right)
            val arrowRGB = getColor(R.styleable.ItemSettingsView_arrowColor, resources.getColor(R.color.colorSecondaryText))
            itemBean.arrowColor = arrowRGB
        }
    }
    //region 设置资源

    fun setInfo(info: ItemSettingsBean) {
        itemBean = info
        obItemInfo.set(info)
    }

    fun setTitle(title: String) {
        itemBean.title = title
    }

    // 设置内容描述
    fun setDesc(desc: String) {
        itemBean.desc = desc
    }

    fun setIcon(iconRes: Any) {
        itemBean.iconRes = iconRes
    }

    fun setArrow(arrowRes: Any) {
        itemBean.arrowRes = arrowRes
    }

    //endregion

    //region 点击事件

    fun onClickIcon(listener: OnClickListener) {
        itemBean.iconListener = listener
    }

    fun onClickTitle(listener: OnClickListener) {
        itemBean.titleListener = listener
    }

    fun onClickDesc(listener: OnClickListener) {
        itemBean.descListener = listener
    }

    fun onClickArrow(listener: OnClickListener) {
        itemBean.arrowListener = listener
    }

    //endregion

    //region 设置颜色

    fun setIconColor(colorRes: Int) {
        itemBean.iconColor = colorRes
    }

    fun setTitleColor(colorRes: Int) {
        itemBean.titleColor = colorRes
    }

    fun setDescColor(colorRes: Int) {
        itemBean.descColor = colorRes
    }

    fun setArrowColor(colorRes: Int) {
        itemBean.arrowColor = colorRes
    }

    //endregion


    // 整个 item 自身有 点击事件的时候，事件就不向下分发了
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return hasOnClickListeners()
    }

}

@Keep
data class ItemSettingsBean(
    // 支持 本地资源，网络资源
    var iconRes: Any = R.drawable.ic_gift_card,
    var title: String = "",
    var desc: String = "",
    var titleColor: Int = R.color.colorPrimaryText,
    var descColor: Int = R.color.colorSecondaryText,
    var iconColor: Int = 0,
    var arrowColor: Int = 0,
    var arrowRes: Any = R.drawable.ic_right
) {


    // item 点击
    var iconListener: View.OnClickListener? = null
    var titleListener: View.OnClickListener? = null
    var descListener: View.OnClickListener? = null
    var arrowListener: View.OnClickListener? = null


}