package com.cainiao.service.utils

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * @author boomhe on 2020/11/6.
 * 项目适配用的 BindingAdapter
 * {@link ImageViewBindingAdapter}.
 */

// ImageView 支持图片加载绑定
@BindingAdapter("app:srcCompat", requireAll = false)
fun imgSrc(image: ImageView, src: Any?) {
    Glide.with(image)
        .load(src)
        .into(image)
}

@BindingAdapter("app:tint")
fun imgColor(image: ImageView, @ColorRes color: Int) {
    if (color != 0) image.setColorFilter(image.resources.getColor(color))
}

@BindingAdapter("app:tint")
fun imgColorInt(image: ImageView, @ColorInt color: Int) {
    if (color != 0) image.setColorFilter(color)
}

// TextView 的重载
@BindingAdapter("android:textColor")
fun tvColorRes(tv: TextView, @ColorRes color: Int) {
    if (color != 0) tv.setTextColor(tv.resources.getColor(color))
}

@BindingAdapter("android:textColor")
fun tvColorInt(tv: TextView, @ColorInt color: Int) {
    // Color.RED
    if (color != 0) tv.setTextColor(color)
}