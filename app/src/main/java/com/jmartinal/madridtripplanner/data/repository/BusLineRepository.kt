package com.jmartinal.madridtripplanner.data.repository

import android.util.Log
import com.jmartinal.madridtripplanner.application.data.remote.model.BusLineDetailsResult
import com.jmartinal.madridtripplanner.data.datasource.BusLineLocalDataSource
import com.jmartinal.madridtripplanner.data.datasource.BusLineRemoteDataSource
import com.jmartinal.madridtripplanner.domain.BusLine

class BusLineRepository(
    private val localDataSource: BusLineLocalDataSource,
    private val remoteDataSource: BusLineRemoteDataSource
) {
    suspend fun isNotEmpty(): Boolean {
        Log.d(TAG, "Has data: ${!localDataSource.isEmpty()}")
        return !localDataSource.isEmpty()
    }

    suspend fun fetchData(accessToken: String) {
        Log.d(TAG, "Fetching Bus Lines Data")
        val remoteLines = remoteDataSource.getBusLines(accessToken)
        val localLines = localDataSource.findAll()
        val deletedLines = localLines.filter { !remoteLines.contains(it) }
        if (deletedLines.isNotEmpty()) {
            Log.d(
                TAG,
                "${deletedLines.size} Bus lines to be deleted: ${deletedLines.map { it.line }}"
            )
            localDataSource.delete(deletedLines)
        }
        localDataSource.save(remoteLines)
    }

    suspend fun findAll(): List<BusLine> {
        Log.d(TAG, "Finding all bus lines")
        return localDataSource.findAll()
    }

    suspend fun findDetails(accessToken: String, lineID: String): BusLineDetailsResult {
        Log.d(TAG, "Finding details for lineID $lineID")
        return remoteDataSource.getBusLineDetails(accessToken, lineID)
    }

    companion object {
        private val TAG = BusLineRepository::class.simpleName
    }
}