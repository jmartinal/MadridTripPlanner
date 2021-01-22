package com.jmartinal.madridtripplanner.application.data.remote.datasource

import com.jmartinal.madridtripplanner.application.Constants
import com.jmartinal.madridtripplanner.application.data.remote.EMTOpenDataClient
import com.jmartinal.madridtripplanner.application.data.remote.model.toDomain
import com.jmartinal.madridtripplanner.data.datasource.AppDataRemoteDataSource
import com.jmartinal.madridtripplanner.domain.AppInfo

class AppDataWSDataSource(private val client: EMTOpenDataClient) : AppDataRemoteDataSource {

    override suspend fun getAppData(): AppInfo {
        val apiInfo = client.service.hello().toDomain()
        val appInfo = client.service.login(
            Constants.EMTOpenDataApi.CLIENT_ID,
            Constants.EMTOpenDataApi.PASS_KEY
        )
        return with(appInfo.info[0]) {
            apiInfo.copy(
                appName = nameApp,
                accessToken = accessToken,
                accessTokenExpiration = tokenDateTimeExpirationApi.tokenExpirationTime
            )
        }
    }
}