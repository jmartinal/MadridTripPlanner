package com.jmartinal.madridtripplanner.application.data.remote.datasource

import com.jmartinal.madridtripplanner.application.data.remote.BusEMTClient
import com.jmartinal.madridtripplanner.application.data.remote.model.BusLineDetailsResult
import com.jmartinal.madridtripplanner.application.data.remote.model.toDomain
import com.jmartinal.madridtripplanner.data.datasource.BusLineRemoteDataSource
import com.jmartinal.madridtripplanner.domain.BusLine
import com.jmartinal.madridtripplanner.domain.Group

class BusLineWSDataSource(private val client: BusEMTClient) : BusLineRemoteDataSource {

    override suspend fun getGroups(accessToken: String): List<Group> {
        return client.service.getGroups(accessToken).data.map { it.toDomain() }
    }

    override suspend fun getBusLines(accessToken: String): List<BusLine> {
        return client.service.getBusLines(accessToken).info.map { it.toDomain() }
    }

    override suspend fun getBusLineDetails(
        accessToken: String,
        lineID: String
    ): BusLineDetailsResult {
        return client.service.getBusLineDetails(accessToken, lineID)
    }
}