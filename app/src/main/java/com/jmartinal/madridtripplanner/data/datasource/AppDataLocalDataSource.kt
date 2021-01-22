package com.jmartinal.madridtripplanner.data.datasource

import com.jmartinal.madridtripplanner.domain.AppInfo


interface AppDataLocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun save(appInfo: AppInfo)
    suspend fun update(appInfo: AppInfo)
    suspend fun refreshAccessToken(apiVersion: String, newToken: String, newExpirationTime: Long)
    suspend fun getAppData(): AppInfo
}