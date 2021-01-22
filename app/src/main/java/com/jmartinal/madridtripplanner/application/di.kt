package com.jmartinal.madridtripplanner.application

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.jmartinal.madridtripplanner.application.common.manager.AndroidConnectivityManager
import com.jmartinal.madridtripplanner.application.data.local.EMTOpenDataDatabase
import com.jmartinal.madridtripplanner.application.data.local.EMTTransportDatabase
import com.jmartinal.madridtripplanner.application.data.local.datasource.AppDataRoomDataSource
import com.jmartinal.madridtripplanner.application.data.local.datasource.BusLineRoomDataSource
import com.jmartinal.madridtripplanner.application.data.local.datasource.GroupRoomDataSource
import com.jmartinal.madridtripplanner.application.data.remote.EMTOpenDataClient
import com.jmartinal.madridtripplanner.application.data.remote.EMTTransportClient
import com.jmartinal.madridtripplanner.application.data.remote.datasource.AppDataWSDataSource
import com.jmartinal.madridtripplanner.application.data.remote.datasource.BusLineWSDataSource
import com.jmartinal.madridtripplanner.application.data.remote.datasource.GroupWSDataSource
import com.jmartinal.madridtripplanner.application.ui.about.AboutFragment
import com.jmartinal.madridtripplanner.application.ui.about.AboutViewModel
import com.jmartinal.madridtripplanner.application.ui.buslinedetail.BusLineDetailFragment
import com.jmartinal.madridtripplanner.application.ui.buslinedetail.BusLineDetailViewModel
import com.jmartinal.madridtripplanner.application.ui.buslines.BusLinesFragment
import com.jmartinal.madridtripplanner.application.ui.buslines.BusLinesViewModel
import com.jmartinal.madridtripplanner.application.ui.favorites.FavoritesFragment
import com.jmartinal.madridtripplanner.application.ui.favorites.FavoritesViewModel
import com.jmartinal.madridtripplanner.application.ui.main.MainActivity
import com.jmartinal.madridtripplanner.application.ui.main.MainViewModel
import com.jmartinal.madridtripplanner.application.ui.refreshdata.RefreshDataFragment
import com.jmartinal.madridtripplanner.application.ui.refreshdata.RefreshDataViewModel
import com.jmartinal.madridtripplanner.application.ui.route.RouteFragment
import com.jmartinal.madridtripplanner.application.ui.route.RouteViewModel
import com.jmartinal.madridtripplanner.data.datasource.*
import com.jmartinal.madridtripplanner.data.manager.ConnectivityManager
import com.jmartinal.madridtripplanner.data.repository.AppDataRepository
import com.jmartinal.madridtripplanner.data.repository.BusLineRepository
import com.jmartinal.madridtripplanner.data.repository.GroupRepository
import com.jmartinal.madridtripplanner.usecases.*
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
    single { EMTTransportDatabase.build(get()) }
    single { EMTOpenDataDatabase.build(get()) }

    single { EMTTransportClient() }
    single { EMTOpenDataClient() }

    factory<AppCompatActivity> { MainActivity() }

    factory<ConnectivityManager> { AndroidConnectivityManager(get()) }

    factory<AppDataLocalDataSource> { AppDataRoomDataSource(get()) }
    factory<AppDataRemoteDataSource> { AppDataWSDataSource(get()) }
    factory<GroupLocalDataSource> { GroupRoomDataSource(get()) }
    factory<GroupRemoteDataSource> { GroupWSDataSource(get()) }
    factory<BusLineLocalDataSource> { BusLineRoomDataSource(get()) }
    factory<BusLineRemoteDataSource> { BusLineWSDataSource(get()) }
}

private val dataModule = module {
    factory { AppDataRepository(get(), get()) }
    factory { GroupRepository(get(), get()) }
    factory { BusLineRepository(get(), get()) }
}

private val scopesModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get(), get(), get(), get()) }
        scoped { ValidateAppInfo(get()) }
        scoped { RefreshAccessTokenIfNeeded(get()) }
        scoped { FetchData(get(), get(), get()) }
    }
    scope(named<AboutFragment>()) {
        viewModel { AboutViewModel(get()) }
        scoped { GetAppInfo(get()) }
    }
    scope(named<BusLinesFragment>()) {
        viewModel { BusLinesViewModel(get()) }
        scoped { GetBusLines(get()) }
    }
    scope(named<BusLineDetailFragment>()) {
        viewModel { (lineID: String) -> BusLineDetailViewModel(lineID, get()) }
        scoped { GetBusLineDetail(get(), get()) }
    }
    scope(named<FavoritesFragment>()) {
        viewModel { FavoritesViewModel() }
    }
    scope(named<RouteFragment>()) {
        viewModel { RouteViewModel() }
    }
    scope(named<RefreshDataFragment>()) {
        viewModel { RefreshDataViewModel(get(), get(), get()) }
        scoped { GetAppInfo(get()) }
        scoped { FetchData(get(), get(), get()) }
    }
}