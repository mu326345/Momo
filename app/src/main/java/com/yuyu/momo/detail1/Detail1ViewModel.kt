package com.yuyu.momo.detail1

import androidx.lifecycle.ViewModel
import com.yuyu.momo.data.ResultItem

class Detail1ViewModel: ViewModel() {

    lateinit var resultItem: ResultItem

    fun takeBundleData(data: ResultItem) {
        resultItem = data
    }
}