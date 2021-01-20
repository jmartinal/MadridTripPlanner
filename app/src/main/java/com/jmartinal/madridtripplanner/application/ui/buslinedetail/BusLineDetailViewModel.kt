package com.jmartinal.madridtripplanner.application.ui.buslinedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmartinal.madridtripplanner.R
import com.jmartinal.madridtripplanner.application.common.Event
import com.jmartinal.madridtripplanner.usecases.GetBusLineDetail
import kotlinx.coroutines.launch

class BusLineDetailViewModel(
    private val lineID: String,
    private val getBusLineDetail: GetBusLineDetail
) : ViewModel() {
    private val _state = MutableLiveData<BusLineDetailUiModel>()
    val state: LiveData<BusLineDetailUiModel>
        get() = _state

    private val _action = MutableLiveData<Event<BusLineDetailAction>>()
    val action: LiveData<Event<BusLineDetailAction>>
        get() = _action

    private val _navigation = MutableLiveData<Event<BusLineDetailDestination>>()
    val navigation: LiveData<Event<BusLineDetailDestination>>
        get() = _navigation

    init {
        fetchLineInfo()
    }

    private fun fetchLineInfo() {
        viewModelScope.launch {
            _state.value = BusLineDetailUiModel.Loading(R.string.common_loading_message)
            getBusLineDetail(lineID)

        }
    }
}