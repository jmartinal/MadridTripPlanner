package com.jmartinal.madridtripplanner.application.data.remote

import android.annotation.SuppressLint
import com.jmartinal.madridtripplanner.application.data.remote.model.BusLineDetailsResult
import com.jmartinal.madridtripplanner.application.data.remote.model.BusLinesResult
import com.jmartinal.madridtripplanner.application.data.remote.model.GroupsResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
interface EMTTransportService {

    @GET("lines/groups")
    suspend fun getGroups(@Header("accessToken") accessToken: String): GroupsResult

    @GET("lines/info/{dateRef}/")
    suspend fun getBusLines(
        @Header("accessToken") accessToken: String,
        @Path("dateRef") dateRef: String = SimpleDateFormat("yyyyMMdd").format(Date())
    ): BusLinesResult

    @GET("lines/{lineId}/info/{dateRef}/")
    suspend fun getBusLineDetails(
        @Header("accessToken") accessToken: String,
        @Path("lineId") lineId: String,
        @Path("dateRef") dateRef: String = SimpleDateFormat("yyyyMMdd").format(Date())
    ): BusLineDetailsResult
}