package com.example.madridtripplanner.application.data.local.dao

import androidx.room.*
import com.example.madridtripplanner.application.data.local.model.AppData

@Dao
interface AppDataDao {

    @Query("SELECT COUNT(api_version) FROM app_data")
    fun getCount(): Int

    @Query("SELECT * FROM app_data")
    fun getApiInfo(): AppData

    @Query("UPDATE app_data SET access_token = :accessToken WHERE api_version LIKE :apiVersion")
    fun refreshAccessToken(apiVersion: String, accessToken: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(appData: AppData)

    @Query("DELETE FROM app_data")
    fun deleteAll()
}