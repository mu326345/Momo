package com.yuyu.momo.repository

import android.util.Log
import com.yuyu.momo.data.AnimalData
import com.yuyu.momo.data.ParkData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DefaultRepository(private val remoteDataSource: IDataSource) : IRepository {

    override suspend fun getParkData(callback: (ParkData) -> Unit) {
        remoteDataSource.getParkData().enqueue(object : Callback<ParkData> {
            override fun onResponse(call: Call<ParkData>, response: Response<ParkData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback(it)
                    }
                }
            }

            override fun onFailure(call: Call<ParkData>, t: Throwable) {
                Log.v("DefaultRepository", "retrofit on fail ${t.message}")
            }
        })
    }

    override suspend fun getAnimalData(callback: (AnimalData) -> Unit) {
        remoteDataSource.getAnimalData().enqueue(object : Callback<AnimalData> {
            override fun onResponse(call: Call<AnimalData>, response: Response<AnimalData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback(it)
                    }
                }
            }

            override fun onFailure(call: Call<AnimalData>, t: Throwable) {
                Log.v("DefaultRepository", "retrofit on fail ${t.message}")
            }
        })
    }
}