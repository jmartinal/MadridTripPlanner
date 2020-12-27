package com.jmartinal.madridtripplanner.data.datasource

import com.jmartinal.madridtripplanner.domain.ApplicationData


interface AppDataLocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveAppData(applicationData: ApplicationData)
    suspend fun updateAppData(applicationData: ApplicationData)
    suspend fun refreshAccessToken(apiVersion: String, newToken: String)
    suspend fun getAppData(): ApplicationData
}