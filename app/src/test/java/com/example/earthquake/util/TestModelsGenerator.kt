package com.example.earthquake.util

import com.example.earthquake.viewmodel.EarthquakeResult
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File


class TestModelsGenerator {
    private var earthquakes: EarthquakeResult? = null

    init {
        val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

        val adapter: JsonAdapter<EarthquakeResult> = moshi.adapter(EarthquakeResult::class.java)
        val jsonString = getJson("earthquake.json")
        adapter.fromJson(jsonString)?.let {
            earthquakes = it
        }
        print("earthquakes is $earthquakes")
    }

    fun generateEarthquakes(): EarthquakeResult? {
        return earthquakes
    }

    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */

    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }
}
