package com.jmartinal.madridtripplanner.domain

import java.util.*

data class AppInfo(
    val apiVersion: String,
    val v1: String,
    val build1: String,
    val v2: String,
    val build2: String,
    val appName: String = "",
    val accessToken: String = "",
    val accessTokenExpiration: Long = 0,
    val updatedAt: Long = Date().time
)
