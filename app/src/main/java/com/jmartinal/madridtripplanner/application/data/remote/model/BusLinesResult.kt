package com.jmartinal.madridtripplanner.application.data.remote.model

import com.jmartinal.madridtripplanner.domain.BusLine
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BusLinesResult(
    val code: String,
    @Json(name = "data") val info: List<BusLinesData>,
    val description: String
)

@JsonClass(generateAdapter = true)
data class BusLinesData(
    val color: String,
    val endDate: String,
    val forecolor: String?,
    val group: String,
    val label: String,
    val line: String,
    val longLine1: Int,
    val longLine2: Int,
    val nameA: String,
    val nameB: String,
    val order: Int,
    val startDate: String
)

fun BusLinesData.toDomain() = BusLine(
    this.line,
    this.group,
    this.label,
    this.nameA,
    this.nameB,
    this.order
)