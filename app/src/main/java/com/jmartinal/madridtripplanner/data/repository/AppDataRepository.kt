package com.jmartinal.madridtripplanner.data.repository

import com.jmartinal.madridtripplanner.data.datasource.AppDataLocalDataSource
import com.jmartinal.madridtripplanner.data.datasource.AppDataRemoteDataSource
import com.jmartinal.madridtripplanner.domain.ApplicationData

class AppDataRepository(
    private val localDataSource: AppDataLocalDataSource,
    private val remoteDataSource: AppDataRemoteDataSource
) {

    suspend fun isNotEmpty(): Boolean = !localDataSource.isEmpty()

    suspend fun isUpToDate(): Boolean =
        remoteDataSource.getAppData().apiVersion == localDataSource.getAppData().apiVersion

    suspend fun download(): String {
        val remoteAppData = remoteDataSource.getAppData()
        localDataSource.saveAppData(remoteAppData)
        return remoteAppData.accessToken
    }

    suspend fun update(): String {
        val remoteAppData = remoteDataSource.getAppData()
        localDataSource.updateAppData(remoteAppData)
        return remoteAppData.accessToken
    }

    suspend fun getData(): ApplicationData = localDataSource.getAppData()

    suspend fun refreshAccessToken(): String {
        val remoteAppData = remoteDataSource.getAppData()
        localDataSource.refreshAccessToken(remoteAppData.apiVersion, remoteAppData.accessToken)
        return remoteAppData.accessToken
    }

}