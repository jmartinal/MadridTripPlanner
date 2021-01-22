package com.jmartinal.madridtripplanner.application.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmartinal.madridtripplanner.application.data.local.model.Group as DbGroup
import com.jmartinal.madridtripplanner.domain.Group as DomainGroup

@Entity(tableName = "groups")
data class Group(
    val group: String,
    @PrimaryKey
    @ColumnInfo(name = "sub_group") val subGroup: String,
    val description: String
)

fun DbGroup.toDomain() = DomainGroup(this.group, this.subGroup, this.description)

fun DomainGroup.toDatabase() = DbGroup(this.group, this.subGroup, this.description)
