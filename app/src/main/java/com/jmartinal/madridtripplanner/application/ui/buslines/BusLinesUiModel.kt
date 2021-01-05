package com.jmartinal.madridtripplanner.application.ui.buslines

import com.jmartinal.madridtripplanner.domain.BusLine

sealed class BusLinesUiModel {
    object ShowEmptyResults : BusLinesUiModel()
    class Loading(val messageResID: Int) : BusLinesUiModel()
    class ShowBusLines(val busLines: List<BusLine>) : BusLinesUiModel()
}
