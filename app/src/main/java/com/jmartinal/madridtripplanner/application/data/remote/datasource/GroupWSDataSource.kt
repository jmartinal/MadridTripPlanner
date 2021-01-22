package com.jmartinal.madridtripplanner.application.data.remote.datasource

import com.jmartinal.madridtripplanner.application.data.remote.EMTTransportClient
import com.jmartinal.madridtripplanner.application.data.remote.model.toDomain
import com.jmartinal.madridtripplanner.data.datasource.GroupRemoteDataSource
import com.jmartinal.madridtripplanner.domain.Group

class GroupWSDataSource(private val transportClient: EMTTransportClient) : GroupRemoteDataSource {
    override suspend fun getGroups(accessToken: String): List<Group> {
        return transportClient.transportService.getGroups(accessToken).data.map { it.toDomain() }
    }
}