package com.jmartinal.madridtripplanner.usecases

import com.jmartinal.madridtripplanner.data.repository.BusLineRepository
import com.jmartinal.madridtripplanner.domain.BusLine

class GetBusLines(private val busLineRepository: BusLineRepository) {
    suspend operator fun invoke(): List<BusLine> = busLineRepository.findAll()
}