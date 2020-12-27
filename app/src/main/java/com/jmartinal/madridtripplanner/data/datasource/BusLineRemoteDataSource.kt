package com.jmartinal.madridtripplanner.data.datasource

import com.jmartinal.madridtripplanner.domain.BusLine

interface BusLineRemoteDataSource {
    suspend fun getLines(accessToken: String): List<BusLine>
}