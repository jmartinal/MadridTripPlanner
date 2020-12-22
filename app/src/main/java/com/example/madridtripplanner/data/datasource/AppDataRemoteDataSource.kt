package com.example.madridtripplanner.data.datasource

import com.example.madridtripplanner.domain.ApplicationData

interface AppDataRemoteDataSource {
    suspend fun getAppData(): ApplicationData
}