package com.jmartinal.madridtripplanner.application.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmartinal.madridtripplanner.application.data.local.model.BusLine

@Dao
interface BusLineDao {

    @Query("SELECT COUNT(line) FROM bus_lines")
    fun getCount(): Int

    @Query("SELECT * FROM bus_lines")
    fun findAll(): List<BusLine>

    @Query("SELECT * FROM bus_lines WHERE line = :line")
    fun findByLine(line: String): BusLine

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(busLines: BusLine)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(busLines: List<BusLine>)

    @Query("DELETE FROM bus_lines WHERE line = :line")
    fun deleteByLine(line: String)

    @Query("DELETE FROM bus_lines WHERE line IN (:lines)")
    fun deleteInLines(lines: List<String>)

    @Query("DELETE FROM bus_lines")
    fun deleteAll()

}