package com.jmartinal.madridtripplanner.application.ui.buslines

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmartinal.madridtripplanner.R
import com.jmartinal.madridtripplanner.application.common.Event
import com.jmartinal.madridtripplanner.domain.BusLine
import com.jmartinal.madridtripplanner.usecases.GetBusLines
import kotlinx.coroutines.launch

class BusLinesViewModel(private val getBusLines: GetBusLines) : ViewModel() {

    private val _state = MutableLiveData<BusLinesUiModel>()
    val state: LiveData<BusLinesUiModel>
        get() = _state

    private val _action = MutableLiveData<Event<BusLinesAction>>()
    val action: LiveData<Event<BusLinesAction>>
        get() = _action

    private val _navigation = MutableLiveData<Event<BusLinesDestination>>()
    val navigation: LiveData<Event<BusLinesDestination>>
        get() = _navigation

    init {
        fetchBusLines()
    }

    private fun fetchBusLines() {
        viewModelScope.launch {
            _state.value = BusLinesUiModel.Loading(R.string.common_loading_message)
            _state.value = BusLinesUiModel.ShowBusLines(getBusLines())
        }
    }

    fun onBusLineClicked(busLine: BusLine) {
        _navigation.value = Event(BusLinesDestination.BusLinesDetail(busLine.line))
    }

    @SuppressLint("DefaultLocale")
    fun onSearchQuerySubmitted(text: String) {
        viewModelScope.launch {
            if (text.isBlank()) {
                viewModelScope.launch {
                    _state.value = BusLinesUiModel.ShowBusLines(getBusLines())
                }
            } else {
                val filteredLines = getBusLines().filter {
                    val labelMatches = it.label.toUpperCase().contains(text.toUpperCase())
                    val nameAMatches = it.nameA.toUpperCase().contains(text.toUpperCase())
                    val nameBMatches = it.nameB.toUpperCase().contains(text.toUpperCase())
                    labelMatches || nameAMatches || nameBMatches
                }
                if (filteredLines.isEmpty()) {
                    _state.value = BusLinesUiModel.ShowEmptyResults
                } else {
                    _state.value = BusLinesUiModel.ShowBusLines(filteredLines)
                }
            }
        }
    }

    @SuppressLint("DefaultLocale")
    fun onSearchQueryTextChanged(text: String) {
        if (text.isBlank()) {
            viewModelScope.launch {
                _state.value = BusLinesUiModel.ShowBusLines(getBusLines())
            }
        }
    }

}