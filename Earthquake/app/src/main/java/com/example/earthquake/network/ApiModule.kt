package com.example.earthquake.network

import com.example.earthquake.model.EarthquakeApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

object ApiModule {

    @JvmStatic
    private val API_URL = "https://earthquake.usgs.gov/"

    @JvmStatic
    fun provideEarthquakeApi(): EarthquakeApi {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(MediaType.parse("application/json")!!))
            .build()
            .create(EarthquakeApi::class.java)
    }

}