package com.yuyu.momo.repository

import com.yuyu.momo.data.AnimalData
import com.yuyu.momo.data.ParkData
import retrofit2.Call

interface IDataSource {

    suspend fun getParkData(): Call<ParkData>

    suspend fun getAnimalData(): Call<AnimalData>
}
