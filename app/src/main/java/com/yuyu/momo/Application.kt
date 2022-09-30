package com.yuyu.momo

import android.app.Application
import com.yuyu.momo.network.ParkService
import com.yuyu.momo.repository.DefaultRepository
import com.yuyu.momo.repository.RemoteDataSource

class Application: Application() {

    lateinit var repository: DefaultRepository

    override fun onCreate() {
        super.onCreate()
        repository = DefaultRepository(RemoteDataSource(ParkService))
    }
}