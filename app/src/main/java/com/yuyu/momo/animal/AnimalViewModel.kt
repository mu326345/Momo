package com.yuyu.momo.animal

import androidx.lifecycle.ViewModel
import com.yuyu.momo.data.AResultItem

class AnimalViewModel: ViewModel() {

    lateinit var animalData: AResultItem

    fun takeBundleData(data: AResultItem) {
        animalData = data
    }
}