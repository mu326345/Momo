package com.yuyu.momo

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("text")
fun ifNoRestData(view: TextView, text: String) {
    if (text.isNotEmpty()) {
        view.text = text
    } else {
        view.text = view.context.resources.getString(R.string.no_rest_data)
    }
}

@BindingAdapter("loadImg")
fun bindImage(view: ImageView, url: String) {
    Glide
        .with(view)
        .load(url)
        .centerCrop()
        .placeholder(R.color.black)
        .into(view)
}