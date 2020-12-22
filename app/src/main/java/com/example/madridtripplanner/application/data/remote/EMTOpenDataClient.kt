package com.example.madridtripplanner.application.data.remote

import com.example.madridtripplanner.BuildConfig
import com.example.madridtripplanner.application.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class EMTOpenDataClient {
    private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.EMTOpenDataApi.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service: EMTOpenDataService = retrofit.create(EMTOpenDataService::class.java)
}