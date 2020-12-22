package com.example.madridtripplanner.application.data.remote.datasource

import com.example.madridtripplanner.application.Constants
import com.example.madridtripplanner.application.data.remote.EMTOpenDataClient
import com.example.madridtripplanner.application.data.remote.model.toDomain
import com.example.madridtripplanner.data.datasource.AppDataRemoteDataSource
import com.example.madridtripplanner.domain.ApplicationData

class AppDataWSDataSource(private val client: EMTOpenDataClient) : AppDataRemoteDataSource {

    override suspend fun getAppData(): ApplicationData {
        val apiInfo = client.service.hello()
        val appInfo = client.service.login(
            Constants.EMTOpenDataApi.CLIENT_ID,
            Constants.EMTOpenDataApi.PASS_KEY
        )
        return ApplicationData(
            apiInfo.toDomain(),
            appInfo.toDomain()
        )
    }
}