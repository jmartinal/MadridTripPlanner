package com.jmartinal.madridtripplanner.application.data.local.datasource

import com.jmartinal.madridtripplanner.application.data.local.EMTTransportDatabase
import com.jmartinal.madridtripplanner.application.data.local.model.toDatabase
import com.jmartinal.madridtripplanner.application.data.local.model.toDomain
import com.jmartinal.madridtripplanner.data.datasource.GroupLocalDataSource
import com.jmartinal.madridtripplanner.domain.Group
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GroupRoomDataSource(db: EMTTransportDatabase) : GroupLocalDataSource {

    private val groupDao = db.getGroupDao()

    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) {
        groupDao.getCount() <= 0
    }

    override suspend fun findAll(): List<Group> = withContext(Dispatchers.IO) {
        groupDao.findAll().map { it.toDomain() }
    }

    override suspend fun save(group: Group) = withContext(Dispatchers.IO) {
        groupDao.insert(group.toDatabase())
    }

    override suspend fun save(groups: List<Group>) = withContext(Dispatchers.IO) {
        groupDao.insert(groups.map { it.toDatabase() })
    }

    override suspend fun delete(group: Group) = withContext(Dispatchers.IO) {
        groupDao.deleteBySubGroup(group.subGroup)
    }

    override suspend fun delete(groups: List<Group>) = withContext(Dispatchers.IO) {
        groupDao.deleteInSubGroups(groups.map { it.subGroup })
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        groupDao.deleteAll()
    }
}