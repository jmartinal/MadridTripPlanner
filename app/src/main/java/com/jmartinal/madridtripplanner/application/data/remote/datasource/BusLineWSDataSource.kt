package com.jmartinal.madridtripplanner.application.data.remote.datasource

import com.jmartinal.madridtripplanner.application.data.remote.EMTTransportClient
import com.jmartinal.madridtripplanner.application.data.remote.model.BusLineDetailsResult
import com.jmartinal.madridtripplanner.application.data.remote.model.toDomain
import com.jmartinal.madridtripplanner.data.datasource.BusLineRemoteDataSource
import com.jmartinal.madridtripplanner.domain.BusLine

class BusLineWSDataSource(private val transportClient: EMTTransportClient) :
    BusLineRemoteDataSource {

    override suspend fun getBusLines(accessToken: String): List<BusLine> {
        return transportClient.transportService.getBusLines(accessToken).info.map { it.toDomain() }
    }

    override suspend fun getBusLineDetails(
        accessToken: String,
        lineID: String
    ): BusLineDetailsResult {
        return transportClient.transportService.getBusLineDetails(accessToken, lineID)
    }
}