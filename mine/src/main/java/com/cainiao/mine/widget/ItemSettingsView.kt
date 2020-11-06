package com.cainiao.mine.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
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
    defStyleAttr: Int
) :
    ConstraintLayout(context, attrs, defStyleAttr) {


    private var itemBean = ItemSettingsBean()
    private val obItemInfo = ObservableField(itemBean)

    init {
        // 管理布局：组合控件  this ，true 关联到 root
        VItemSettingsBinding.inflate(LayoutInflater.from(context), this, true)
            .apply {
                info = obItemInfo
            }
        setBackgroundColor(Color.WHITE)
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
    fun setDesc(desc: String){
        itemBean.desc = desc
    }

    fun setIcon(iconRes: Any){
        itemBean.iconRes = iconRes
    }

    //region end


}

@Keep
data class ItemSettingsBean(
    // 支持 本地资源，网络资源
    var iconRes: Any = R.drawable.ic_gift_card,
    var title: String = "Title 标题",
    var desc: String = "标题内容描述",
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