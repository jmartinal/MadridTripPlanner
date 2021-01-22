package com.jmartinal.madridtripplanner.application.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jmartinal.madridtripplanner.application.data.local.dao.BusLineDao
import com.jmartinal.madridtripplanner.application.data.local.dao.GroupDao
import com.jmartinal.madridtripplanner.application.data.local.model.BusLine
import com.jmartinal.madridtripplanner.application.data.local.model.Group

@Database(entities = [Group::class, BusLine::class], version = 1, exportSchema = false)
abstract class EMTTransportDatabase : RoomDatabase() {
    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            EMTTransportDatabase::class.java,
            "emt-bus-db"
        ).build()
    }

    abstract fun getGroupDao(): GroupDao
    abstract fun getBusLineDao(): BusLineDao
}