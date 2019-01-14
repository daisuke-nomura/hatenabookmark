package com.kyaracter.hatenabookmark.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("url")
fun ImageView.setUrl(url: String?) {
    if (url == null) return

    GlideApp
        .with(this)
        .load(url)
        .into(this)
}