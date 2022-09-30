package com.yuyu.momo.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuyu.momo.data.ResultItem
import com.yuyu.momo.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: IRepository): ViewModel() {

    private val _resultItem = MutableLiveData<List<ResultItem>>()
    val resultItem: LiveData<List<ResultItem>>
        get() = _resultItem

    init {
        getParkData()
    }

    private fun getParkData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getParkData {
                _resultItem.value = it.result.results
            }
        }
    }
}