package com.jmartinal.madridtripplanner.application.ui.main

sealed class MainUiModel {
    class Loading(val messageResID: Int) : MainUiModel()
    object Default : MainUiModel()
}
