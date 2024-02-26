package com.example.mineexplore.RetroFit


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetroFit {
    private const val BASE_URL = "https://api.thecatapi.com/v1/"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}