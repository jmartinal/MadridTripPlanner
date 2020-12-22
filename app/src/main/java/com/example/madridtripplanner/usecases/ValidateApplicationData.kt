package com.example.madridtripplanner.usecases

import com.example.madridtripplanner.data.repository.AppDataRepository
import com.example.madridtripplanner.data.repository.BusLineRepository

class ValidateApplicationData(
    private val infoRepository: AppDataRepository,
    private val busLineRepository: BusLineRepository
) {
    suspend operator fun invoke() = infoRepository.hasData() && busLineRepository.hasData()
}