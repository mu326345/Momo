package com.yuyu.momo.repository

import com.yuyu.momo.data.AnimalData
import com.yuyu.momo.data.ParkData
import com.yuyu.momo.network.ParkService
import retrofit2.Call

class RemoteDataSource(private val parkService: ParkService) : IDataSource {

    override suspend fun getParkData(): Call<ParkData> {
        return parkService.service.getParkData()
    }

    override suspend fun getAnimalData(): Call<AnimalData> {
        return parkService.service.getAnimalData()
    }
}