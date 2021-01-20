package com.jmartinal.madridtripplanner.application.ui.buslines

sealed class BusLinesDestination {
    class BusLinesDetail(val lineLabel: String) : BusLinesDestination()
}
