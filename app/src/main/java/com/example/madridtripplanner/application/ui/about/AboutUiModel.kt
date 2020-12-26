package com.example.madridtripplanner.application.ui.about

sealed class AboutUiModel {
    class Loading(val messageResId: Int) : AboutUiModel()
    class Default(val apiVersion: String) : AboutUiModel()
}
