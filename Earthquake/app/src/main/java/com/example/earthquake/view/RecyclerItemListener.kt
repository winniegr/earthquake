package com.example.earthquake.view

import android.app.Activity
import com.example.earthquake.viewmodel.Feature

interface RecyclerItemListener {
    fun onItemSelected(context: Activity, earthquakeItem : Feature)
}