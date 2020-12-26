package com.example.madridtripplanner.usecases

import com.example.madridtripplanner.data.repository.AppDataRepository

class GetApiVersion(private val infoRepository: AppDataRepository) {
    suspend operator fun invoke() = infoRepository.getData().apiVersion
}