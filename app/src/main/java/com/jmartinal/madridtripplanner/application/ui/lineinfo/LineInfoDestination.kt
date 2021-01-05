package com.jmartinal.madridtripplanner.application.ui.lineinfo

sealed class LineInfoDestination {
    class LineInfoDetail(val line: String) : LineInfoDestination()

}
