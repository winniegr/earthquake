package com.example.earthquake.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquake.model.EarthquakeService
import kotlinx.coroutines.launch

class EarthquakeViewModel : ViewModel() {

    val earthquakeService = EarthquakeService()
    val earthquake = MutableLiveData<EarthquakeResult>()
    val loading = MutableLiveData<Boolean>()
    val failed = MutableLiveData<Boolean>()

    fun getEarthquakes() {
        viewModelScope.launch {
            loading.value = true
            failed.value = false
            try {
                when (val response = earthquakeService.getEarthquakes()) {
                    is EarthquakeResult -> {
                        loading.value = false
                        earthquake.value = response!!
                    }

                    else -> {
                        loading.value = false
                        failed.value = true
                    }

                }
            } catch (e: Exception) {
                loading.value = false
                failed.value = true
            }
        }
    }

}