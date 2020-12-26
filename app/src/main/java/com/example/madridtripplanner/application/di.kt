package com.example.madridtripplanner.application

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.example.madridtripplanner.application.common.manager.AndroidConnectivityManager
import com.example.madridtripplanner.application.data.local.EMTDatabase
import com.example.madridtripplanner.application.data.local.datasource.AppDataRoomDataSource
import com.example.madridtripplanner.application.data.local.datasource.BusLineRoomDataSource
import com.example.madridtripplanner.application.data.remote.EMTOpenDataClient
import com.example.madridtripplanner.application.data.remote.datasource.AppDataWSDataSource
import com.example.madridtripplanner.application.data.remote.datasource.BusLineWSDataSource
import com.example.madridtripplanner.application.ui.about.AboutFragment
import com.example.madridtripplanner.application.ui.about.AboutViewModel
import com.example.madridtripplanner.application.ui.lineroute.LineRouteFragment
import com.example.madridtripplanner.application.ui.lineroute.LineRouteViewModel
import com.example.madridtripplanner.application.ui.lineschedule.LineScheduleFragment
import com.example.madridtripplanner.application.ui.lineschedule.LineScheduleViewModel
import com.example.madridtripplanner.application.ui.main.MainActivity
import com.example.madridtripplanner.application.ui.main.MainViewModel
import com.example.madridtripplanner.application.ui.newroute.NewRouteFragment
import com.example.madridtripplanner.application.ui.newroute.NewRouteViewModel
import com.example.madridtripplanner.application.ui.refreshdata.RefreshDataFragment
import com.example.madridtripplanner.application.ui.refreshdata.RefreshDataViewModel
import com.example.madridtripplanner.data.datasource.AppDataLocalDataSource
import com.example.madridtripplanner.data.datasource.AppDataRemoteDataSource
import com.example.madridtripplanner.data.datasource.BusLineLocalDataSource
import com.example.madridtripplanner.data.datasource.BusLineRemoteDataSource
import com.example.madridtripplanner.data.manager.ConnectivityManager
import com.example.madridtripplanner.data.repository.AppDataRepository
import com.example.madridtripplanner.data.repository.BusLineRepository
import com.example.madridtripplanner.usecases.FetchApplicationData
import com.example.madridtripplanner.usecases.GetApiVersion
import com.example.madridtripplanner.usecases.ValidateApplicationData
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger(Level.NONE)
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single { EMTDatabase.build(get()) }
    single { EMTOpenDataClient() }
    factory<AppCompatActivity> { MainActivity() }
    factory<ConnectivityManager> { AndroidConnectivityManager(get()) }
    factory<AppDataLocalDataSource> { AppDataRoomDataSource(get()) }
    factory<AppDataRemoteDataSource> { AppDataWSDataSource(get()) }
    factory<BusLineLocalDataSource> { BusLineRoomDataSource(get()) }
    factory<BusLineRemoteDataSource> { BusLineWSDataSource(get()) }
}

private val dataModule = module {
    factory { AppDataRepository(get(), get()) }
    factory { BusLineRepository(get(), get()) }
}

private val scopesModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get(), get(), get()) }
        scoped { FetchApplicationData(get(), get()) }
        scoped { ValidateApplicationData(get(), get()) }
    }
    scope(named<AboutFragment>()) {
        viewModel { AboutViewModel(get()) }
        scoped { GetApiVersion(get()) }
    }
    scope(named<LineRouteFragment>()) {
        viewModel { LineRouteViewModel() }
    }
    scope(named<LineScheduleFragment>()) {
        viewModel { LineScheduleViewModel() }
    }
    scope(named<NewRouteFragment>()) {
        viewModel { NewRouteViewModel() }
    }
    scope(named<RefreshDataFragment>()) {
        viewModel { RefreshDataViewModel() }
    }
}