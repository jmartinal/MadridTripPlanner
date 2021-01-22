package com.jmartinal.madridtripplanner.usecases

import com.jmartinal.madridtripplanner.data.repository.AppDataRepository

class ValidateAppInfo(private val appDataRepository: AppDataRepository) {
    suspend operator fun invoke(isConnected: Boolean = true): Boolean {
        return if (isConnected) {
            appDataRepository.isNotEmpty() && appDataRepository.isUpToDate()
        } else {
            appDataRepository.isNotEmpty()
        }
    }
}