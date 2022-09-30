package com.yuyu.momo

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("text")
fun text(view: TextView, text: String) {
    if(text != null) {
        view.text = text
    }
}