package com.example.madridtripplanner.application.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.madridtripplanner.application.data.local.model.BusLine as DbBusLine
import com.example.madridtripplanner.domain.BusLine as DomainBusLine

@Entity(tableName = "bus_lines")
data class BusLine(
    @PrimaryKey(autoGenerate = false) val line: String,
    val group: String,
    val label: String,
    @ColumnInfo(name = "name_a") val nameA: String,
    @ColumnInfo(name = "name_b") val nameB: String,
    val order: Int
)

fun DbBusLine.toDomain() = DomainBusLine(
    this.line,
    this.group,
    this.label,
    this.nameA,
    this.nameB,
    this.order
)

fun DomainBusLine.toDatabase() = DbBusLine(
    this.line,
    this.group,
    this.label,
    this.nameA,
    this.nameB,
    this.order
)