package com.jmartinal.madridtripplanner.application.ui.buslinedetail

sealed class BusLineDetailUiModel {
    class Loading(val commonLoadingMessage: Int) : BusLineDetailUiModel()
}
