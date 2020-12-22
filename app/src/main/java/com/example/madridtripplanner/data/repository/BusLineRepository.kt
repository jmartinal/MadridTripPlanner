package com.example.madridtripplanner.data.repository

import com.example.madridtripplanner.data.datasource.BusLineLocalDataSource
import com.example.madridtripplanner.data.datasource.BusLineRemoteDataSource

class BusLineRepository(
    private val localDataSource: BusLineLocalDataSource,
    private val remoteDataSource: BusLineRemoteDataSource
) {
    suspend fun hasData(): Boolean = !localDataSource.isEmpty()

    suspend fun downloadData(accessToken: String) {
        val lines = remoteDataSource.getLines(accessToken)
        localDataSource.saveLines(lines)
    }

    suspend fun updateData(accessToken: String) {
        val lines = remoteDataSource.getLines(accessToken)
        localDataSource.updateLines(lines)
    }
}