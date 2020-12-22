package com.example.madridtripplanner.data.manager

interface ConnectivityManager {
    fun isConnected(): Boolean
    fun isConnectedByWifi(): Boolean
    fun isConnectedBy4G(): Boolean
}