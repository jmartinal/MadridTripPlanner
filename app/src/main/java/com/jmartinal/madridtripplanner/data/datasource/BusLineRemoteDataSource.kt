package com.jmartinal.madridtripplanner.data.datasource

import com.jmartinal.madridtripplanner.application.data.remote.model.BusLineDetailsResult
import com.jmartinal.madridtripplanner.domain.BusLine

interface BusLineRemoteDataSource {
    suspend fun getBusLines(accessToken: String): List<BusLine>
    suspend fun getBusLineDetails(accessToken: String, lineID: String): BusLineDetailsResult
}