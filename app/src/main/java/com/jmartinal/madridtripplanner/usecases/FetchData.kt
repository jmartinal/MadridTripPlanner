package com.jmartinal.madridtripplanner.usecases

import com.jmartinal.madridtripplanner.data.repository.AppDataRepository
import com.jmartinal.madridtripplanner.data.repository.BusLineRepository
import com.jmartinal.madridtripplanner.data.repository.GroupRepository
import com.jmartinal.madridtripplanner.domain.AppInfo

class FetchData(
    private val appDataRepository: AppDataRepository,
    private val groupRepository: GroupRepository,
    private val busLineRepository: BusLineRepository
) {
    suspend operator fun invoke(): AppInfo {
        val accessToken = appDataRepository.fetchData()
        groupRepository.fetchData(accessToken)
        busLineRepository.fetchData(accessToken)
        return appDataRepository.getData()
    }
}