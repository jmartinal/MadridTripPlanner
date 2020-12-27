package com.jmartinal.madridtripplanner.domain

import java.util.*

data class ApplicationData(
    val apiVersion: String,
    val v1: String,
    val build1: String,
    val v2: String,
    val build2: String,
    val appName: String,
    val accessToken: String,
    val updatedAt: Long
) {
    constructor(apiInfo: ApiInfo, appInfo: AppInfo) : this(
        apiInfo.apiVersion,
        apiInfo.v1,
        apiInfo.build1,
        apiInfo.v2,
        apiInfo.build2,
        appInfo.appName,
        appInfo.accessToken,
        updatedAt = Date().time
    )
}
