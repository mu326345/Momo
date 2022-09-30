package com.yuyu.momo.network

import com.yuyu.momo.data.ParkData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://data.taipei/api/v1/dataset/"
private const val PARK_PATH = "5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire"

private val client = OkHttpClient.Builder()
    .addInterceptor(
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
    )
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

interface ParkApiService {
    @GET(PARK_PATH)
    fun getParkData(): Call<ParkData>
}

object ParkService {
    val service: ParkApiService by lazy { retrofit.create(ParkApiService::class.java) }
}