package com.yuyu.momo.detail1

import androidx.annotation.VisibleForTesting
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

    var resultItem: ResultItem? = null

    init {
        getAnimalData()
    }

    fun setBundleData(data: ResultItem) {
        resultItem = data
    }

    private fun getAnimalData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAnimalData {
                _animalItem.value = filterSameAnimal(it.result.results)
            }
        }
    }

    private fun filterSameAnimal(data: List<AResultItem>): List<AResultItem> {
        return data.filter {
            it.a_location == resultItem?.e_name
        }
    }

    @VisibleForTesting
    fun testFilterSameAnimal(data: List<AResultItem>): List<AResultItem>{
        return filterSameAnimal(data)
    }
}