package com.example.madridtripplanner.application.ui.main

sealed class MainAction {
    class ShowError(val messageResID: Int): MainAction()
}
