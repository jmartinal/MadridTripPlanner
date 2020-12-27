package com.jmartinal.madridtripplanner.application.data.remote

import com.jmartinal.madridtripplanner.application.data.remote.model.ApiInfoResult
import com.jmartinal.madridtripplanner.application.data.remote.model.AppInfoResult
import com.jmartinal.madridtripplanner.application.data.remote.model.BusLinesResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface EMTOpenDataService {

    @GET("hello/")
    suspend fun hello(): ApiInfoResult

    @GET("mobilitylabs/user/login/")
    suspend fun login(
        @Header("X-ClientId") clientID: String,
        @Header("passKey") passKey: String
    ): AppInfoResult

    @GET("transport/busemtmad/lines/info/{dateRef}/")
    suspend fun getLines(
        @Header("accessToken") accessToken: String,
        @Query("dateref") dateRef: String
    ): BusLinesResult
}