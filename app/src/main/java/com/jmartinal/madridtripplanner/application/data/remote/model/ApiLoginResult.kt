package com.jmartinal.madridtripplanner.application.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiLoginResult(
    val code: String,
    @Json(name = "data") val info: List<ApiLoginData>,
    val datetime: String,
    val description: String
)

@JsonClass(generateAdapter = true)
data class ApiLoginData(
    val accessToken: String,
    val apiCounter: ApiCounter,
    val email: String,
    val idUser: String,
    val nameApp: String,
    val priv: String,
    @Json(name = "tokenDteExpiration") val tokenDateTimeExpirationApi: ApiLoginDate,
    val updatedAt: String,
    val userName: String?
)

@JsonClass(generateAdapter = true)
data class ApiLoginDate(
    @Json(name = "\$date") val tokenExpirationTime: Long
)

@JsonClass(generateAdapter = true)
data class ApiCounter(
    val aboutUses: String?,
    val current: Int,
    val dailyUse: Int,
    val licenceUse: String,
    val owner: Int
)