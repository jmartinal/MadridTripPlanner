package com.example.madridtripplanner.application.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madridtripplanner.R
import com.example.madridtripplanner.application.common.Event
import com.example.madridtripplanner.application.ui.main.MainUiModel.Default
import com.example.madridtripplanner.application.ui.main.MainUiModel.Loading
import com.example.madridtripplanner.data.manager.ConnectivityManager
import com.example.madridtripplanner.usecases.FetchApplicationData
import com.example.madridtripplanner.usecases.ValidateApplicationData
import kotlinx.coroutines.launch

class MainViewModel(
    private val connectivityManager: ConnectivityManager,
    private val validateApplicationData: ValidateApplicationData,
    private val fetchApplicationData: FetchApplicationData
) : ViewModel() {

    private val _state = MutableLiveData<MainUiModel>()
    val state: LiveData<MainUiModel>
        get() {
            if (_state.value == null) refresh()
            return _state
        }

    private val _action = MutableLiveData<Event<MainAction>>()
    val action: LiveData<Event<MainAction>>
        get() = _action

    private fun refresh() {
        _state.value = Loading(R.string.message_check_info)
        if (connectivityManager.isConnected()) {
            viewModelScope.launch {
                if (!validateApplicationData()) {
                    _state.value = Loading(R.string.message_downloading_info)
                }
                fetchApplicationData()
                _state.value = Default
            }
        } else {
            _action.value = Event(MainAction.ShowError(R.string.error_no_connectivity_message))
        }
    }

}