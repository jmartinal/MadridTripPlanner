package com.jmartinal.madridtripplanner.application.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jmartinal.madridtripplanner.application.data.local.dao.AppDataDao
import com.jmartinal.madridtripplanner.application.data.local.model.AppInfo

@Database(entities = [AppInfo::class], version = 1, exportSchema = false)
abstract class EMTOpenDataDatabase : RoomDatabase() {
    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            EMTOpenDataDatabase::class.java,
            "emt-data-db"
        ).build()
    }

    abstract fun getAppDataDao(): AppDataDao
}