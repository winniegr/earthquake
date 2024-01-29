package com.example.earthquake.model

import com.example.earthquake.network.ApiModule
import com.example.earthquake.viewmodel.EarthquakeResult

class EarthquakeService {

    private val api = ApiModule.provideEarthquakeApi()

    suspend fun getEarthquakes(): EarthquakeResult? {
        return api.getEarthquake()
    }
}