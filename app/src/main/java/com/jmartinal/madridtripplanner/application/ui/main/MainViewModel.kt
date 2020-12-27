package com.jmartinal.madridtripplanner.application.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmartinal.madridtripplanner.R
import com.jmartinal.madridtripplanner.application.common.Event
import com.jmartinal.madridtripplanner.application.ui.main.MainAction.ShowError
import com.jmartinal.madridtripplanner.application.ui.main.MainUiModel.Default
import com.jmartinal.madridtripplanner.application.ui.main.MainUiModel.Loading
import com.jmartinal.madridtripplanner.data.manager.ConnectivityManager
import com.jmartinal.madridtripplanner.usecases.FetchApplicationData
import com.jmartinal.madridtripplanner.usecases.ValidateApplicationData
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
            _action.value = Event(ShowError(R.string.error_no_connectivity_message))
        }
    }

}