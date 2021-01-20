package com.jmartinal.madridtripplanner.usecases

import com.jmartinal.madridtripplanner.data.repository.AppDataRepository
import com.jmartinal.madridtripplanner.data.repository.BusLineRepository

class GetBusLineDetail(
    private val appDataRepository: AppDataRepository,
    private val busLinesRepository: BusLineRepository
) {
    suspend operator fun invoke(lineID: String) =
        busLinesRepository.findDetails(appDataRepository.getData().accessToken, lineID)
}
