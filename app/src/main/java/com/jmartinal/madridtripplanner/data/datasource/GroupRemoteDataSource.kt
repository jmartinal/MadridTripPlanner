package com.jmartinal.madridtripplanner.data.datasource

import com.jmartinal.madridtripplanner.domain.Group

interface GroupRemoteDataSource {
    suspend fun getGroups(accessToken: String): List<Group>
}