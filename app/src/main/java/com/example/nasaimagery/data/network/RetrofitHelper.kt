package com.example.nasaimagery.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val baseUrL = "https://api.nasa.gov"

    private val retrofit = Retrofit.Builder().baseUrl(baseUrL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val retrofitService : APODApi by lazy {
        retrofit.create(APODApi::class.java)
    }

}