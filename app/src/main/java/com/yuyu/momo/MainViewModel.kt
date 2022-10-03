package com.yuyu.momo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _barTitle = MutableLiveData<String>()
    val barTitle: LiveData<String>
        get() = _barTitle

    private val _barImage = MutableLiveData<Int>()
    val barImage: LiveData<Int>
        get() = _barImage

    fun changeBarTitle(title: String) {
        _barTitle.value = title
    }

    fun changeBarImage(image: Int) {
        _barImage.value = image
    }
}