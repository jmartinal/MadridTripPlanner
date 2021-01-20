package com.jmartinal.madridtripplanner.application.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusLineDetailsResult(
    val code: String,
    @Json(name = "data") val info: List<BusLineDetailsData>,
    val description: String,
    @Json(name = "datetime") val dateTime: String
)

@JsonClass(generateAdapter = true)
data class BusLineDetailsData(
    val dateRef: String,
    val nameA: String,
    val label: String,
    val timeTable: List<BusLineDetailTimeTable>,
    val nameB: String,
    val line: String
)

@JsonClass(generateAdapter = true)
data class BusLineDetailTimeTable(
    @Json(name = "Direction1") val direction1: BusLineDetailDirection,
    @Json(name = "Direction2") val direction2: BusLineDetailDirection,
    val idDayType: String
)

@JsonClass(generateAdapter = true)
data class BusLineDetailDirection(
    @Json(name = "MaximumFrequency") val maximumFrequency: String,
    @Json(name = "MinimunFrequency") val minimumFrequency: String,
    @Json(name = "FrequencyText") val frequencyText: String,
    @Json(name = "StopTime") val stopTime: String,
    @Json(name = "StartTime") val startTime: String
)
