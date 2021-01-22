package com.jmartinal.madridtripplanner.application.ui.main

import android.util.Log
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
import com.jmartinal.madridtripplanner.usecases.FetchData
import com.jmartinal.madridtripplanner.usecases.RefreshAccessTokenIfNeeded
import com.jmartinal.madridtripplanner.usecases.ValidateAppInfo
import kotlinx.coroutines.launch

class MainViewModel(
    private val connectivityManager: ConnectivityManager,
    private val validateAppInfo: ValidateAppInfo,
    private val refreshAccessTokenIfNeeded: RefreshAccessTokenIfNeeded,
    private val fetchData: FetchData
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
            val isConnected = connectivityManager.isConnected()
            if (isConnected) {
                Log.d(TAG, "Connectivity granted")
                if (validateAppInfo()) {
                    Log.d(TAG, "Application data is valid")
                    refreshAccessTokenIfNeeded()
                    _state.value = Default
                } else {
                    Log.d(TAG, "Application data is not valid")
                    _state.value = Loading(R.string.message_downloading_info)
                    fetchData()
                    _state.value = Default
                }
            } else {
                Log.d(TAG, "Connectivity not granted")
                if (validateAppInfo(isConnected)) {
                    Log.d(TAG, "Application data is not valid, but we have previous data stored")
                    _action.value = Event(ShowMinorError(R.string.main_error_data_not_updated))
                    _state.value = Default
                } else {
                    Log.d(
                        TAG,
                        "Application data is not valid and we do not have previous data stored"
                    )
                    _action.value = Event(ShowMajorError(R.string.error_no_connectivity_message))
                    _state.value = Default
                }
            }
        }
    }

    companion object {
        private val TAG = MainViewModel::class.simpleName
    }

}