package com.jmartinal.madridtripplanner.data.datasource

import com.jmartinal.madridtripplanner.domain.BusLine

interface BusLineLocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun getLines(): List<BusLine>
    suspend fun saveLines(busLines: List<BusLine>)
    suspend fun updateLines(busLines: List<BusLine>)
    suspend fun findLineByTag(tag: String): BusLine
}