package com.jmartinal.madridtripplanner.application.ui.lineinfo

import com.jmartinal.madridtripplanner.domain.BusLine

sealed class LineInfoUiModel {
    class Loading(val messageResID: Int) : LineInfoUiModel()
    class ShowBusLines(val busLines: List<BusLine>) : LineInfoUiModel()
}
