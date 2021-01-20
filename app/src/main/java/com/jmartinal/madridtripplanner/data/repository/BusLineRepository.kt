package com.jmartinal.madridtripplanner.data.repository

import com.jmartinal.madridtripplanner.application.data.remote.model.BusLineDetailsResult
import com.jmartinal.madridtripplanner.data.datasource.BusLineLocalDataSource
import com.jmartinal.madridtripplanner.data.datasource.BusLineRemoteDataSource
import com.jmartinal.madridtripplanner.domain.BusLine

class BusLineRepository(
    private val localDataSource: BusLineLocalDataSource,
    private val remoteDataSource: BusLineRemoteDataSource
) {
    suspend fun isNotEmpty(): Boolean = !localDataSource.isEmpty()

    suspend fun download(accessToken: String) {
        val lines = remoteDataSource.getBusLines(accessToken)
        localDataSource.saveLines(lines)
    }

    suspend fun update(accessToken: String) {
        val lines = remoteDataSource.getBusLines(accessToken)
        localDataSource.updateLines(lines)
    }

    suspend fun findAll(): List<BusLine> = localDataSource.getLines()

    suspend fun findDetails(accessToken: String, lineID: String): BusLineDetailsResult {
        return remoteDataSource.getBusLineDetails(accessToken, lineID)
    }
}