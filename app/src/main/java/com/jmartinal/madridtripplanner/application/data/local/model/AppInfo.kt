package com.jmartinal.madridtripplanner.application.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmartinal.madridtripplanner.application.data.local.model.AppInfo as DbAppInfo
import com.jmartinal.madridtripplanner.domain.AppInfo as DomainAppData

@Entity(tableName = "app_data")
data class AppInfo(
    @PrimaryKey
    @ColumnInfo(name = "api_version") val apiVersion: String,
    val v1: String,
    @ColumnInfo(name = "v1_build") val v1Build: String,
    val v2: String,
    @ColumnInfo(name = "v2_build") val v2Build: String,
    @ColumnInfo(name = "app_name") val appName: String?,
    @ColumnInfo(name = "access_token") val accessToken: String?,
    @ColumnInfo(name = "access_token_expiration") val accessTokenExpiration: Long?,
    @ColumnInfo(name = "updated_at") val updatedAt: Long
)

fun DbAppInfo.toDomain() = DomainAppData(
    apiVersion,
    v1,
    this.v1Build,
    v2,
    this.v2Build,
    appName = "",
    accessToken = "",
    accessTokenExpiration = 0,
    updatedAt
)

fun DomainAppData.toDatabase() = DbAppInfo(
    this.apiVersion,
    this.v1,
    this.build1,
    this.v2,
    this.build2,
    this.appName,
    this.accessToken,
    this.accessTokenExpiration,
    this.updatedAt
)
