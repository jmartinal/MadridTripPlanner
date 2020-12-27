package com.jmartinal.madridtripplanner.usecases

import com.jmartinal.madridtripplanner.data.repository.AppDataRepository
import com.jmartinal.madridtripplanner.data.repository.BusLineRepository

class ValidateApplicationData(
    private val infoRepository: AppDataRepository,
    private val busLineRepository: BusLineRepository
) {
    suspend operator fun invoke() = infoRepository.hasData() && busLineRepository.hasData()
}