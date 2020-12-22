package com.example.madridtripplanner.application.data.local.datasource

import com.example.madridtripplanner.application.data.local.EMTDatabase
import com.example.madridtripplanner.application.data.local.model.toDatabase
import com.example.madridtripplanner.application.data.local.model.toDomain
import com.example.madridtripplanner.data.datasource.AppDataLocalDataSource
import com.example.madridtripplanner.domain.ApplicationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppDataRoomDataSource(database: EMTDatabase) : AppDataLocalDataSource {

    private val appDataDao = database.getAppDataDao()

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) {
        appDataDao.getCount() <= 0
    }

    override suspend fun saveAppData(applicationData: ApplicationData) =
        withContext(Dispatchers.IO) {
            appDataDao.insert(applicationData.toDatabase())
        }

    override suspend fun updateAppData(applicationData: ApplicationData) =
        withContext(Dispatchers.IO) {
            appDataDao.deleteAll()
            appDataDao.insert(applicationData.toDatabase())
        }

    override suspend fun refreshAccessToken(apiVersion: String, newToken: String) =
        withContext(Dispatchers.IO) {
            appDataDao.refreshAccessToken(apiVersion, newToken)
        }

    override suspend fun getAppData(): ApplicationData = withContext(Dispatchers.IO) {
        appDataDao.getApiInfo().toDomain()
    }

}