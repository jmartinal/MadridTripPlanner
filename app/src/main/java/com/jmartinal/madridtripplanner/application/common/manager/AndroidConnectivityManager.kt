package com.jmartinal.madridtripplanner.application.common.manager

import android.app.Application
import android.content.Context
import android.net.NetworkCapabilities
import android.os.Build
import com.jmartinal.madridtripplanner.data.manager.ConnectivityManager

class AndroidConnectivityManager(application: Application) : ConnectivityManager {

    private val connectivityManager: android.net.ConnectivityManager =
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager

    override fun isConnected(): Boolean = isConnectedByWifi() || isConnectedBy4G()

    @Suppress("DEPRECATION")
    override fun isConnectedByWifi(): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } else {
            connectivityManager.activeNetworkInfo?.let {
                it.type == android.net.ConnectivityManager.TYPE_WIFI && it.isConnectedOrConnecting
            } ?: false

        }

    @Suppress("DEPRECATION")
    override fun isConnectedBy4G(): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } else {
            connectivityManager.activeNetworkInfo?.let {
                it.type == android.net.ConnectivityManager.TYPE_MOBILE && it.isConnectedOrConnecting
            } ?: false
        }

}