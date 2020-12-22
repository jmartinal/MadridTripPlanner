package com.example.madridtripplanner.application.data.remote.model

import com.example.madridtripplanner.domain.AppInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppInfoResult(
    val code: String,
    @Json(name = "data") val info: List<AppInfoData>,
    val datetime: String,
    val description: String
)

@JsonClass(generateAdapter = true)
data class AppInfoData(
    val accessToken: String,
    val apiCounter: ApiCounter,
    val email: String,
    val idUser: String,
    val nameApp: String,
    val priv: String,
    val tokenSecExpiration: Int,
    val updatedAt: String,
    val userName: String
)

@JsonClass(generateAdapter = true)
data class ApiCounter(
    val aboutUses: String?,
    val current: Int,
    val dailyUse: Int,
    val licenceUse: String,
    val owner: Int
)

fun AppInfoResult.toDomain() = AppInfo(this.info[0].nameApp, this.info[0].accessToken)