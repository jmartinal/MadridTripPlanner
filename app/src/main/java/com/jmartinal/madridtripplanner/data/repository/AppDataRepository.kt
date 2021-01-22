package com.jmartinal.madridtripplanner.data.repository

import android.util.Log
import com.jmartinal.madridtripplanner.data.datasource.AppDataLocalDataSource
import com.jmartinal.madridtripplanner.data.datasource.AppDataRemoteDataSource
import com.jmartinal.madridtripplanner.domain.AppInfo
import java.util.*

class AppDataRepository(
    private val localDataSource: AppDataLocalDataSource,
    private val remoteDataSource: AppDataRemoteDataSource
) {

    suspend fun isNotEmpty(): Boolean {
        Log.d(TAG, "Has data: ${!localDataSource.isEmpty()}")
        return !localDataSource.isEmpty()
    }

    suspend fun isUpToDate(): Boolean {
        val isUpToDate =
            remoteDataSource.getAppData().apiVersion == localDataSource.getAppData().apiVersion
        Log.d(TAG, "Data is ${if (!isUpToDate) "not" else ""} up to date")
        return remoteDataSource.getAppData().apiVersion == localDataSource.getAppData().apiVersion
    }

    suspend fun getData(): AppInfo {
        Log.d(TAG, "Getting Application data")
        return localDataSource.getAppData()
    }

    suspend fun fetchData(): String {
        val data = remoteDataSource.getAppData()
        if (localDataSource.isEmpty()) {
            Log.d(TAG, "LocalDataSource is empty. Saving...")
            localDataSource.save(data)
        } else {
            Log.d(TAG, "LocalDataSource is not empty. Updating...")
            localDataSource.update(data)
        }
        return data.accessToken
    }

    suspend fun refreshAccessTokenIfNeeded() {
        val currentTime = Date().time
        val expirationTime = localDataSource.getAppData().accessTokenExpiration
        if (currentTime <= expirationTime) {
            Log.d(TAG, "AccessToken VALID")
        } else {
            Log.d(TAG, "AccessToken INVALID")
            val remoteAppData = remoteDataSource.getAppData()
            localDataSource.refreshAccessToken(
                remoteAppData.apiVersion,
                remoteAppData.accessToken,
                remoteAppData.accessTokenExpiration
            )
        }
    }

    companion object {
        private val TAG = AppDataRepository::class.simpleName
    }

}