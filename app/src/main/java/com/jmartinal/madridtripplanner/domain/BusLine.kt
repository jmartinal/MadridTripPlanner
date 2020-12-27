package com.jmartinal.madridtripplanner.domain

data class BusLine(
    val line: String,
    val group: String,
    val label: String,
    val nameA: String,
    val nameB: String,
    val order: Int
)
