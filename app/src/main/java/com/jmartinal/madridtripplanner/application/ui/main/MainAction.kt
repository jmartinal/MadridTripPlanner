package com.jmartinal.madridtripplanner.application.ui.main

sealed class MainAction {
    class ShowError(val messageResID: Int): MainAction()
}
