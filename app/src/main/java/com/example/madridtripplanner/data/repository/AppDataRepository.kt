package com.example.madridtripplanner.data.repository

import com.example.madridtripplanner.data.datasource.AppDataLocalDataSource
import com.example.madridtripplanner.data.datasource.AppDataRemoteDataSource
import com.example.madridtripplanner.domain.ApplicationData

class AppDataRepository(
    private val localDataSource: AppDataLocalDataSource,
    private val remoteDataSource: AppDataRemoteDataSource
) {

    suspend fun hasData(): Boolean = !localDataSource.isEmpty()

    suspend fun getData(): ApplicationData = localDataSource.getAppData()

    suspend fun isDataUpdated(): Boolean =
        remoteDataSource.getAppData().apiVersion == localDataSource.getAppData().apiVersion

    suspend fun downloadData(): String {
        val remoteAppData = remoteDataSource.getAppData()
        localDataSource.saveAppData(remoteAppData)
        return remoteAppData.accessToken
    }

    suspend fun updateData(): String {
        val remoteAppData = remoteDataSource.getAppData()
        localDataSource.updateAppData(remoteAppData)
        return remoteAppData.accessToken
    }

    suspend fun refreshAccessToken(): String {
        val remoteAppData = remoteDataSource.getAppData()
        localDataSource.refreshAccessToken(remoteAppData.apiVersion, remoteAppData.accessToken)
        return remoteAppData.accessToken
    }

}