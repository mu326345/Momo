package com.yuyu.momo.detail1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuyu.momo.data.AResultItem
import com.yuyu.momo.data.ResultItem
import com.yuyu.momo.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Detail1ViewModel(private val repository: IRepository) : ViewModel() {

    private val _animalItem = MutableLiveData<List<AResultItem>>()
    val animalItem: LiveData<List<AResultItem>>
        get() = _animalItem

    lateinit var resultItem: ResultItem

    init {
        getAnimalData()
    }

    fun takeBundleData(data: ResultItem) {
        resultItem = data
    }

    private fun getAnimalData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAnimalData {
                val item = it.result.results.filter {
                    it.a_location == resultItem.e_name
                }
                _animalItem.value = item
            }
        }
    }
}