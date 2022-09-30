package com.yuyu.momo.repository

import com.yuyu.momo.data.ParkData

interface IRepository {

    suspend fun getParkData(callback: (ParkData) -> Unit)
}
