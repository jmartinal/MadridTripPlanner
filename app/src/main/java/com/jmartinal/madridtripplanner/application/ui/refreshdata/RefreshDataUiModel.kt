package com.jmartinal.madridtripplanner.application.ui.refreshdata

sealed class RefreshDataUiModel {
    class Loading(val messageResID: Int) : RefreshDataUiModel()
    class Default(val updatedAt: Long) : RefreshDataUiModel()
}
