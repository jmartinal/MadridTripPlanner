package com.jmartinal.madridtripplanner.data.datasource

import com.jmartinal.madridtripplanner.domain.Group

interface GroupLocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun findAll(): List<Group>
    suspend fun save(group: Group)
    suspend fun save(groups: List<Group>)
    suspend fun delete(group: Group)
    suspend fun delete(groups: List<Group>)
    suspend fun deleteAll()
}