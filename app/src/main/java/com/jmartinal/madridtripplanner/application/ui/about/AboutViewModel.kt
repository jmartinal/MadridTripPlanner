package com.jmartinal.madridtripplanner.application.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmartinal.madridtripplanner.R
import com.jmartinal.madridtripplanner.application.ui.about.AboutUiModel.Default
import com.jmartinal.madridtripplanner.usecases.GetApplicationData
import kotlinx.coroutines.launch

class AboutViewModel(private val getApplicationData: GetApplicationData) : ViewModel() {
    private val _state = MutableLiveData<AboutUiModel>()
    val state: LiveData<AboutUiModel>
        get() = _state

    init {
        fetchApplicationData()
    }

    private fun fetchApplicationData() {
        _state.value = AboutUiModel.Loading(R.string.common_loading_message)
        viewModelScope.launch {
            val apiVersion = getApplicationData().apiVersion
            _state.value = Default(apiVersion)
        }
    }
}