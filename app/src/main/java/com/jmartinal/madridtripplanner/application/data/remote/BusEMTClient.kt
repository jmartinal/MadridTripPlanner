package com.jmartinal.madridtripplanner.application.data.remote

import com.jmartinal.madridtripplanner.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class BusEMTClient {
    private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service: BusEMTService = retrofit.create(BusEMTService::class.java)

    companion object {
        private const val BASE_URL = "https://openapi.emtmadrid.es/v2/transport/busemtmad/"
    }
}