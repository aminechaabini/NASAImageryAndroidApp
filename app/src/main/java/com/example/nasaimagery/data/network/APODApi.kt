package com.example.nasaimagery.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APODApi {

    @GET("/planetary/apod")
    fun getResult(
        @Query("api_key") apiKey: String,
        @Query("date") date: String
    ): Call<Result>
}