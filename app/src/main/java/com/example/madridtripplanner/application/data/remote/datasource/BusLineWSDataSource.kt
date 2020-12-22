package com.example.madridtripplanner.application.data.remote.datasource

import android.annotation.SuppressLint
import com.example.madridtripplanner.application.data.remote.EMTOpenDataClient
import com.example.madridtripplanner.application.data.remote.model.toDomain
import com.example.madridtripplanner.data.datasource.BusLineRemoteDataSource
import com.example.madridtripplanner.domain.BusLine
import java.text.SimpleDateFormat
import java.util.*

class BusLineWSDataSource(private val client: EMTOpenDataClient) : BusLineRemoteDataSource {
    @SuppressLint("SimpleDateFormat")
    override suspend fun getLines(accessToken: String): List<BusLine> {
        val dateRef = SimpleDateFormat("yyyyMMdd").format(Date())
        return client.service.getLines(accessToken, dateRef).info.map { it.toDomain() }
    }
}