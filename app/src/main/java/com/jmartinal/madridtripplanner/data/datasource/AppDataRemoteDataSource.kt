package com.jmartinal.madridtripplanner.data.datasource

import com.jmartinal.madridtripplanner.domain.ApplicationData

interface AppDataRemoteDataSource {
    suspend fun getAppData(): ApplicationData
}