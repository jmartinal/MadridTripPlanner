package com.example.madridtripplanner.application.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madridtripplanner.R
import com.example.madridtripplanner.application.ui.about.AboutUiModel.Default
import com.example.madridtripplanner.usecases.GetApiVersion
import kotlinx.coroutines.launch

class AboutViewModel(private val getApiVersion: GetApiVersion) : ViewModel() {
    private val _state = MutableLiveData<AboutUiModel>()
    val state: LiveData<AboutUiModel>
        get() = _state

    init {
        fetchApiData()
    }

    private fun fetchApiData() {
        _state.value = AboutUiModel.Loading(R.string.about_loading_message)
        viewModelScope.launch {
            val apiVersion = getApiVersion()
            _state.value = Default(apiVersion)
        }
    }
}