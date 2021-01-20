package com.jmartinal.madridtripplanner.application.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.jmartinal.madridtripplanner.domain.Group as DomainGroup

@JsonClass(generateAdapter = true)
data class GroupsResult(
    val code: String,
    val description: String,
    @Json(name = "datetime") val dateTime: String,
    val `data`: List<Group>
)

@JsonClass(generateAdapter = true)
data class Group(
    val group: String,
    val subGroup: String,
    val description: String
)

fun Group.toDomain() = DomainGroup(this.group, this.subGroup, this.description)
