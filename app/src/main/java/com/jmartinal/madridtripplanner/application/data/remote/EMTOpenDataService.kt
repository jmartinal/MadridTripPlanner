package com.jmartinal.madridtripplanner.application.data.remote

import com.jmartinal.madridtripplanner.application.data.remote.model.ApiInfoResult
import com.jmartinal.madridtripplanner.application.data.remote.model.ApiLoginResult
import retrofit2.http.GET
import retrofit2.http.Header

interface EMTOpenDataService {

    @GET("hello/")
    suspend fun hello(): ApiInfoResult

    @GET("mobilitylabs/user/login/")
    suspend fun login(
        @Header("X-ClientId") clientID: String,
        @Header("passKey") passKey: String
    ): ApiLoginResult

}