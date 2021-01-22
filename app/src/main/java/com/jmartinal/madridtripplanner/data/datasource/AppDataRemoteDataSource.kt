package com.jmartinal.madridtripplanner.data.datasource

import com.jmartinal.madridtripplanner.domain.AppInfo

interface AppDataRemoteDataSource {
    suspend fun getAppData(): AppInfo
}