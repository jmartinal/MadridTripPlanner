package com.example.madridtripplanner.application

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}