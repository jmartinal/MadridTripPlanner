package com.jmartinal.madridtripplanner.application.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmartinal.madridtripplanner.application.data.local.model.Group

@Dao
interface GroupDao {
    @Query("SELECT COUNT(sub_group) FROM groups")
    fun getCount(): Long

    @Query("SELECT * FROM groups")
    fun findAll(): List<Group>

    @Query("SELECT * FROM groups WHERE sub_group = :group")
    fun findByGroup(group: String): Group

    @Query("SELECT * FROM groups WHERE sub_group = :subGroup")
    fun findBySubGroup(subGroup: String): Group

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(group: Group)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(groups: List<Group>)

    @Query("DELETE FROM groups WHERE sub_group = :subGroup")
    fun deleteBySubGroup(subGroup: String)

    @Query("DELETE FROM groups WHERE sub_group IN (:subGroups)")
    fun deleteInSubGroups(subGroups: List<String>)

    @Query("DELETE FROM groups")
    fun deleteAll()
}