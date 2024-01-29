package com.example.earthquake.viewmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class EarthquakeResult(
    val type: String?,
    val metadata: MetaData?,
    val features: List<Feature>?,
    val bbox: List<Double>?
) : Parcelable

@Serializable
@Parcelize
data class Feature(
    val type: String?,
    val properties: FeatureProperties?,
    val geometry: Geometry?,
    val id: String?
) : Parcelable


@Serializable
@Parcelize
data class Geometry(
    val type: String?,
    val coordinates: List<Double>?
) : Parcelable

@Serializable
@Parcelize
data class FeatureProperties(
    val mag: Float?,
    val place: String?,
    val time: Long?,
    val updated: Long?,
    val tz: String?,
    val url: String?,
    val detail: String?,
    val title: String?,
    val alert: String?,
    val status: String?
) : Parcelable

@Serializable
@Parcelize
data class MetaData(
    val generated: Long?,
    val url: String?,
    val title: String?,
    val status: Int?,
    val api: String?,
    val count: Int?
) : Parcelable