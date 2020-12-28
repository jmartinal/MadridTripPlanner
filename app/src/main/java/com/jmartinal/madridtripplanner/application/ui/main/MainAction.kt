package com.jmartinal.madridtripplanner.application.ui.main

sealed class MainAction {
    class ShowMinorError(val messageResID: Int) : MainAction()
    class ShowMajorError(val messageResID: Int) : MainAction()
}
