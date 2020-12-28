package com.jmartinal.madridtripplanner.application.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmartinal.madridtripplanner.R
import com.jmartinal.madridtripplanner.application.common.Event
import com.jmartinal.madridtripplanner.application.ui.main.MainAction.ShowMajorError
import com.jmartinal.madridtripplanner.application.ui.main.MainAction.ShowMinorError
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
            if (_state.value == null) refreshData()
            return _state
        }

    private val _action = MutableLiveData<Event<MainAction>>()
    val action: LiveData<Event<MainAction>>
        get() = _action

    fun refreshData() {
        _state.value = Loading(R.string.message_check_info)
        viewModelScope.launch {
            if (connectivityManager.isConnected()) {
                if (!validateApplicationData()) {
                    _state.value = Loading(R.string.message_downloading_info)
                }
                fetchApplicationData()
                _state.value = Default
            } else {
                if (validateApplicationData()) {
                    _action.value = Event(ShowMinorError(R.string.main_error_data_not_updated))
                    _state.value = Default
                } else {
                    _action.value = Event(ShowMajorError(R.string.error_no_connectivity_message))
                    _state.value = Default
                }
            }
        }

    }

}