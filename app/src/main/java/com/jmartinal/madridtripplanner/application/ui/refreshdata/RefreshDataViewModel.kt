package com.jmartinal.madridtripplanner.application.ui.refreshdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmartinal.madridtripplanner.R
import com.jmartinal.madridtripplanner.application.common.Event
import com.jmartinal.madridtripplanner.data.manager.ConnectivityManager
import com.jmartinal.madridtripplanner.usecases.FetchData
import com.jmartinal.madridtripplanner.usecases.GetAppInfo
import kotlinx.coroutines.launch

class RefreshDataViewModel(
    private val connectivityManager: ConnectivityManager,
    private val getAppInfo: GetAppInfo,
    private val fetchData: FetchData
) : ViewModel() {

    private val _state = MutableLiveData<RefreshDataUiModel>()
    val state: LiveData<RefreshDataUiModel>
        get() = _state

    private val _action = MutableLiveData<Event<RefreshDataAction>>()
    val action: LiveData<Event<RefreshDataAction>>
        get() = _action

    init {
        fetchApplicationData()
    }

    private fun fetchApplicationData() {
        _state.value = RefreshDataUiModel.Loading(R.string.common_loading_message)
        viewModelScope.launch {
            _state.value = RefreshDataUiModel.Default(getAppInfo().updatedAt)
        }
    }

    fun onRefreshAppDataClicked() {
        _state.value = RefreshDataUiModel.Loading(R.string.message_downloading_info)
        if (connectivityManager.isConnected()) {
            viewModelScope.launch {
                _state.value = RefreshDataUiModel.Default(fetchData().updatedAt)
            }
        } else {
            _action.value =
                Event(RefreshDataAction.ShowError(R.string.error_no_connectivity_message))
        }
    }
}