package com.jmartinal.madridtripplanner.application.ui.buslines

sealed class BusLinesDestination {
    class BusLinesDetail(val line: String) : BusLinesDestination()

}
