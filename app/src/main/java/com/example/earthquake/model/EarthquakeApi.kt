package com.example.earthquake.model

import com.example.earthquake.viewmodel.EarthquakeResult
import org.junit.Test
import retrofit2.http.GET


interface EarthquakeApi {

    @Test
    @GET("fdsnws/event/1/query?format=geojson&starttime=2023-01-01&endtime=2024-01-01&minmagnitude=7")
    suspend fun getEarthquake(): EarthquakeResult?

}

