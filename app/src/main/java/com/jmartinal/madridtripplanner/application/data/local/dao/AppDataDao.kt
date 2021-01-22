package com.jmartinal.madridtripplanner.application.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmartinal.madridtripplanner.application.data.local.model.AppInfo

@Dao
interface AppDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(appInfo: AppInfo)

    @Query("SELECT COUNT(api_version) FROM app_data")
    fun getCount(): Int

    @Query("SELECT * FROM app_data")
    fun get(): AppInfo

    @Query("UPDATE app_data SET access_token = :accessToken, access_token_expiration = :accessTokenExpiration WHERE api_version LIKE :apiVersion")
    fun refreshAccessToken(apiVersion: String, accessToken: String, accessTokenExpiration: Long)

    @Query("DELETE FROM app_data")
    fun delete()
}