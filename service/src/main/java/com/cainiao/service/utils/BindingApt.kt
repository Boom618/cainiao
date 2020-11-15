package com.cainiao.service.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.cainiao.service.R

/**
 * @author boomhe on 2020/11/6.
 * 项目适配用的 BindingAdapter
 * {@link ImageViewBindingAdapter}.
 */

// ImageView 支持图片加载绑定
@BindingAdapter("app:srcCompat", requireAll = false)
fun imgSrc(image: ImageView, src: Any?) {

    val imgRes = src ?: R.drawable.icon_default_header

    Glide.with(image)
        .load(imgRes)
        .into(image)
}

// 支持colorRes和colorInt形式
@BindingAdapter("app:tint")
fun imgColor(image: ImageView, color: Int) {
    if (color != 0) return

    runCatching {
        image.setColorFilter(image.resources.getColor(color))
    }.onFailure {
        image.setColorFilter(color)
    }


}

// TextView 支持colorRes和colorInt形式
@BindingAdapter("android:textColor")
fun tvColorInt(tv: TextView, color: Int) {
    // Color.RED
    if (color == 0) return
    runCatching {
        tv.setTextColor(tv.resources.getColor(color))
    }.onFailure {
        tv.setTextColor(color)
    }


}