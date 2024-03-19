package com.example.earthquake.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast


fun openTencent(activity: Activity, title: String, latitude: Double, longitude: Double) {
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        addCategory(Intent.CATEGORY_DEFAULT)
    }
    val uri = Uri.parse("qqmap://map/routeplan?type=drive&to=$title&tocoord=$latitude,$longitude")
    intent.data = uri
    if (intent.resolveActivity(activity.packageManager) != null) {
        activity.startActivity(intent)
    } else {
        Toast.makeText(activity, "you haven't installed tecent map", Toast.LENGTH_SHORT).show()
        val uri2 = Uri.parse("market://details?id=com.tencent.map")
        val intent2 = Intent(Intent.ACTION_VIEW, uri2)
        if (intent2.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(intent2)
        }
    }
}

fun openGaode(activity: Activity, title: String, latitude: Double, longitude: Double) {
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        addCategory(Intent.CATEGORY_DEFAULT)
    }
    val uri = Uri.parse("qqmap://map/routeplan?type=drive&to=$title&tocoord=$latitude,$longitude")
    intent.data = uri
    if (intent.resolveActivity(activity.packageManager) != null) {
        activity.startActivity(intent)
    } else {
        Toast.makeText(activity, "you haven't installed Gaode map", Toast.LENGTH_SHORT).show()
        val uri2 = Uri.parse("market://details?id=com.autonavi.minimap")
        val intent2 = Intent(Intent.ACTION_VIEW, uri2)
        if (intent2.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(intent2)
        }
    }
}
