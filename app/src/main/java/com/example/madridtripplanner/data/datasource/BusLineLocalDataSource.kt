package com.example.madridtripplanner.data.datasource

import com.example.madridtripplanner.domain.BusLine

interface BusLineLocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun getLines(): List<BusLine>
    suspend fun saveLines(busLines: List<BusLine>)
    suspend fun updateLines(busLines: List<BusLine>)
    suspend fun findLineByTag(tag: String): BusLine
}