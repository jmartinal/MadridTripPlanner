package com.example.madridtripplanner.usecases

import android.util.Log
import com.example.madridtripplanner.data.repository.AppDataRepository
import com.example.madridtripplanner.data.repository.BusLineRepository

class FetchApplicationData(
    private val appDataRepository: AppDataRepository,
    private val busLineRepository: BusLineRepository
) {

    suspend operator fun invoke() {
        if (appDataRepository.hasData() && busLineRepository.hasData()) {
            if (appDataRepository.isDataUpdated()) {
                Log.d(FetchApplicationData::class.java.canonicalName, "Data is up to date")
                appDataRepository.refreshAccessToken()
            } else {
                Log.d(FetchApplicationData::class.java.canonicalName, "Updating data")
                val accessToken = appDataRepository.updateData()
                busLineRepository.updateData(accessToken)
            }
        } else {
            Log.d(FetchApplicationData::class.java.canonicalName, "Downloading data")
            val accessToken = appDataRepository.downloadData()
            busLineRepository.downloadData(accessToken)
        }
    }

}