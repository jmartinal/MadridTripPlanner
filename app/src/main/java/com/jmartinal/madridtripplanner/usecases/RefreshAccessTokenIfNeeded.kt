package com.jmartinal.madridtripplanner.usecases

import com.jmartinal.madridtripplanner.data.repository.AppDataRepository

class RefreshAccessTokenIfNeeded(private val appDataRepository: AppDataRepository) {
    suspend operator fun invoke() = appDataRepository.refreshAccessTokenIfNeeded()
}