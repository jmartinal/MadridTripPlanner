package com.jmartinal.madridtripplanner.application.ui.refreshdata

sealed class RefreshDataAction {
    class ShowError(val messageResID: Int) : RefreshDataAction()
}
