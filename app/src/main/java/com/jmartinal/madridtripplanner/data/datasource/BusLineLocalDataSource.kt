package com.jmartinal.madridtripplanner.data.datasource

import com.jmartinal.madridtripplanner.domain.BusLine

interface BusLineLocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun findAll(): List<BusLine>
    suspend fun save(busLine: BusLine)
    suspend fun save(busLines: List<BusLine>)
    suspend fun delete(busLine: BusLine)
    suspend fun delete(busLines: List<BusLine>)
}