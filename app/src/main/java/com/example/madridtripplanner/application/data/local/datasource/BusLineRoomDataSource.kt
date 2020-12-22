package com.example.madridtripplanner.application.data.local.datasource

import com.example.madridtripplanner.application.data.local.EMTDatabase
import com.example.madridtripplanner.application.data.local.model.toDatabase
import com.example.madridtripplanner.application.data.local.model.toDomain
import com.example.madridtripplanner.data.datasource.BusLineLocalDataSource
import com.example.madridtripplanner.domain.BusLine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BusLineRoomDataSource(db: EMTDatabase) : BusLineLocalDataSource {

    private val busLinesDao = db.getBusLinesDao()

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) {
        busLinesDao.getCount() <= 0
    }

    override suspend fun getLines(): List<BusLine> = withContext(Dispatchers.IO) {
        busLinesDao.findAll().map { it.toDomain() }
    }

    override suspend fun findLineByTag(tag: String): BusLine = withContext(Dispatchers.IO) {
        busLinesDao.findByTag(tag).toDomain()
    }

    override suspend fun saveLines(busLines: List<BusLine>) = withContext(Dispatchers.IO) {
        busLinesDao.insert(busLines.map { it.toDatabase() })
    }

    override suspend fun updateLines(busLines: List<BusLine>) = withContext(Dispatchers.IO) {
        busLinesDao.deleteAll()
        busLinesDao.insert(busLines.map { it.toDatabase() })
    }


}