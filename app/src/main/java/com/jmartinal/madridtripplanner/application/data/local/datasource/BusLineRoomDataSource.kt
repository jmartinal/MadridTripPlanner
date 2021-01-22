package com.jmartinal.madridtripplanner.application.data.local.datasource

import com.jmartinal.madridtripplanner.application.data.local.EMTTransportDatabase
import com.jmartinal.madridtripplanner.application.data.local.model.toDatabase
import com.jmartinal.madridtripplanner.application.data.local.model.toDomain
import com.jmartinal.madridtripplanner.data.datasource.BusLineLocalDataSource
import com.jmartinal.madridtripplanner.domain.BusLine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BusLineRoomDataSource(db: EMTTransportDatabase) : BusLineLocalDataSource {

    private val busLinesDao = db.getBusLineDao()

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) {
        busLinesDao.getCount() <= 0
    }

    override suspend fun save(busLine: BusLine) = withContext(Dispatchers.IO) {
        busLinesDao.insert(busLine.toDatabase())
    }

    override suspend fun save(busLines: List<BusLine>) = withContext(Dispatchers.IO) {
        busLinesDao.insert(busLines.map { it.toDatabase() })
    }

    override suspend fun delete(busLine: BusLine) = withContext(Dispatchers.IO) {
        busLinesDao.deleteByLine(busLine.line)
    }

    override suspend fun delete(busLines: List<BusLine>) = withContext(Dispatchers.IO) {
        busLinesDao.deleteInLines(busLines.map { it.line })
    }

    override suspend fun findAll(): List<BusLine> = withContext(Dispatchers.IO) {
        busLinesDao.findAll().map { it.toDomain() }
    }

}