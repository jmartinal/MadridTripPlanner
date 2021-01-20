package com.jmartinal.madridtripplanner.usecases

import android.util.Log
import com.jmartinal.madridtripplanner.data.repository.AppDataRepository
import com.jmartinal.madridtripplanner.data.repository.BusLineRepository

class FetchApplicationData(
    private val appDataRepository: AppDataRepository,
    private val busLineRepository: BusLineRepository
) {

    suspend operator fun invoke(forceUpdate: Boolean = false) {
        if (forceUpdate) {
            Log.d(FetchApplicationData::class.java.canonicalName, "Forcing data update")
            val accessToken = appDataRepository.update()
            busLineRepository.update(accessToken)
        } else {
            if (appDataRepository.isNotEmpty() && busLineRepository.isNotEmpty()) {
                if (appDataRepository.isUpToDate()) {
                    Log.d(FetchApplicationData::class.java.canonicalName, "Data is up to date")
                    appDataRepository.refreshAccessToken()
                } else {
                    Log.d(FetchApplicationData::class.java.canonicalName, "Updating data")
                    val accessToken = appDataRepository.update()
                    busLineRepository.update(accessToken)
                }
            } else {
                Log.d(FetchApplicationData::class.java.canonicalName, "Downloading data")
                val accessToken = appDataRepository.download()
                busLineRepository.download(accessToken)
            }
        }
    }

}