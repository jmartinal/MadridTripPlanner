package com.jmartinal.madridtripplanner.usecases

import com.jmartinal.madridtripplanner.data.repository.AppDataRepository

class GetApplicationData(private val infoRepository: AppDataRepository) {
    suspend operator fun invoke() = infoRepository.getData()
}