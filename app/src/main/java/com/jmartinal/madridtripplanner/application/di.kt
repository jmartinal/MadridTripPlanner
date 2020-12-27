package com.jmartinal.madridtripplanner.application

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.jmartinal.madridtripplanner.application.common.manager.AndroidConnectivityManager
import com.jmartinal.madridtripplanner.application.data.local.EMTDatabase
import com.jmartinal.madridtripplanner.application.data.local.datasource.AppDataRoomDataSource
import com.jmartinal.madridtripplanner.application.data.local.datasource.BusLineRoomDataSource
import com.jmartinal.madridtripplanner.application.data.remote.EMTOpenDataClient
import com.jmartinal.madridtripplanner.application.data.remote.datasource.AppDataWSDataSource
import com.jmartinal.madridtripplanner.application.data.remote.datasource.BusLineWSDataSource
import com.jmartinal.madridtripplanner.application.ui.about.AboutFragment
import com.jmartinal.madridtripplanner.application.ui.about.AboutViewModel
import com.jmartinal.madridtripplanner.application.ui.lineroute.LineRouteFragment
import com.jmartinal.madridtripplanner.application.ui.lineroute.LineRouteViewModel
import com.jmartinal.madridtripplanner.application.ui.lineschedule.LineScheduleFragment
import com.jmartinal.madridtripplanner.application.ui.lineschedule.LineScheduleViewModel
import com.jmartinal.madridtripplanner.application.ui.main.MainActivity
import com.jmartinal.madridtripplanner.application.ui.main.MainViewModel
import com.jmartinal.madridtripplanner.application.ui.newroute.NewRouteFragment
import com.jmartinal.madridtripplanner.application.ui.newroute.NewRouteViewModel
import com.jmartinal.madridtripplanner.application.ui.refreshdata.RefreshDataFragment
import com.jmartinal.madridtripplanner.application.ui.refreshdata.RefreshDataViewModel
import com.jmartinal.madridtripplanner.data.datasource.AppDataLocalDataSource
import com.jmartinal.madridtripplanner.data.datasource.AppDataRemoteDataSource
import com.jmartinal.madridtripplanner.data.datasource.BusLineLocalDataSource
import com.jmartinal.madridtripplanner.data.datasource.BusLineRemoteDataSource
import com.jmartinal.madridtripplanner.data.manager.ConnectivityManager
import com.jmartinal.madridtripplanner.data.repository.AppDataRepository
import com.jmartinal.madridtripplanner.data.repository.BusLineRepository
import com.jmartinal.madridtripplanner.usecases.FetchApplicationData
import com.jmartinal.madridtripplanner.usecases.GetApplicationData
import com.jmartinal.madridtripplanner.usecases.ValidateApplicationData
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
        scoped { GetApplicationData(get()) }
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
        viewModel { RefreshDataViewModel(get(), get(), get()) }
        scoped { GetApplicationData(get()) }
        scoped { FetchApplicationData(get(), get()) }
    }
}