package com.jmartinal.madridtripplanner.data.repository

import com.jmartinal.madridtripplanner.data.datasource.BusLineLocalDataSource
import com.jmartinal.madridtripplanner.data.datasource.BusLineRemoteDataSource
import com.jmartinal.madridtripplanner.domain.BusLine

class BusLineRepository(
    private val localDataSource: BusLineLocalDataSource,
    private val remoteDataSource: BusLineRemoteDataSource
) {
    suspend fun hasData(): Boolean = !localDataSource.isEmpty()

    suspend fun findAll(): List<BusLine> = localDataSource.getLines()

    suspend fun downloadData(accessToken: String) {
        val lines = remoteDataSource.getLines(accessToken)
        localDataSource.saveLines(lines)
    }

    suspend fun updateData(accessToken: String) {
        val lines = remoteDataSource.getLines(accessToken)
        localDataSource.updateLines(lines)
    }
}