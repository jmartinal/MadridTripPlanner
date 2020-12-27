package com.jmartinal.madridtripplanner.application.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jmartinal.madridtripplanner.application.data.local.dao.AppDataDao
import com.jmartinal.madridtripplanner.application.data.local.dao.BusLineDao
import com.jmartinal.madridtripplanner.application.data.local.model.AppData
import com.jmartinal.madridtripplanner.application.data.local.model.BusLine

@Database(entities = [AppData::class, BusLine::class], version = 1, exportSchema = false)
abstract class EMTDatabase : RoomDatabase() {
    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            EMTDatabase::class.java,
            "emt-db"
        ).build()
    }

    abstract fun getAppDataDao(): AppDataDao
    abstract fun getBusLinesDao(): BusLineDao
}