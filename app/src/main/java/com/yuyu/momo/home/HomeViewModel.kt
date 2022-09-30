package com.yuyu.momo.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuyu.momo.data.ParkData
import com.yuyu.momo.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: IRepository): ViewModel() {

    private val _parkData = MutableLiveData<ParkData>()
    val parkData: LiveData<ParkData>
        get() = _parkData

    init {
        getParkData()
    }

    private fun getParkData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getParkData {
                _parkData.value = it
            }
        }
    }
}