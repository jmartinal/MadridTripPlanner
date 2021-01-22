package com.jmartinal.madridtripplanner.application.data.remote.model

import com.jmartinal.madridtripplanner.domain.AppInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiInfoResult(
    @Json(name = "APIVersion") val apiVersion: APIVersion,
    val code: String,
    val developerPortal: String,
    val instant: String,
    val message: String,
    @Json(name = "morehelp") val moreHelp: String,
    val poweredBy: String,
    val versions: List<String>
)

@JsonClass(generateAdapter = true)
data class APIVersion(
    val description: String,
    val version: String
)

fun ApiInfoResult.toDomain() = AppInfo(
    this.apiVersion.version,
    this.versions[0],
    this.versions[1],
    this.versions[2],
    this.versions[3]
)