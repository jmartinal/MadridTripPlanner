package com.example.madridtripplanner.data.datasource

import com.example.madridtripplanner.domain.BusLine

interface BusLineRemoteDataSource {
    suspend fun getLines(accessToken: String): List<BusLine>
}