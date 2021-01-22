package com.jmartinal.madridtripplanner.application.data.local.datasource

import com.jmartinal.madridtripplanner.application.data.local.EMTOpenDataDatabase
import com.jmartinal.madridtripplanner.application.data.local.model.toDatabase
import com.jmartinal.madridtripplanner.application.data.local.model.toDomain
import com.jmartinal.madridtripplanner.data.datasource.AppDataLocalDataSource
import com.jmartinal.madridtripplanner.domain.AppInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppDataRoomDataSource(busDatabase: EMTOpenDataDatabase) : AppDataLocalDataSource {

    private val appDataDao = busDatabase.getAppDataDao()

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) {
        appDataDao.getCount() <= 0
    }

    override suspend fun save(appInfo: AppInfo) =
        withContext(Dispatchers.IO) {
            appDataDao.insert(appInfo.toDatabase())
        }

    override suspend fun update(appInfo: AppInfo) =
        withContext(Dispatchers.IO) {
            appDataDao.delete()
            appDataDao.insert(appInfo.toDatabase())
        }

    override suspend fun refreshAccessToken(
        apiVersion: String,
        newToken: String,
        newExpirationTime: Long
    ) =
        withContext(Dispatchers.IO) {
            appDataDao.refreshAccessToken(apiVersion, newToken, newExpirationTime)
        }

    override suspend fun getAppData(): AppInfo = withContext(Dispatchers.IO) {
        appDataDao.get().toDomain()
    }

}