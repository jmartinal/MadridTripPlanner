package com.jmartinal.madridtripplanner.application.ui.lineinfo

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

class LineInfoViewModel(private val getBusLines: GetBusLines) : ViewModel() {

    private val _state = MutableLiveData<LineInfoUiModel>()
    val state: LiveData<LineInfoUiModel>
        get() = _state

    private val _action = MutableLiveData<Event<LineInfoAction>>()
    val action: LiveData<Event<LineInfoAction>>
        get() = _action

    private val _navigation = MutableLiveData<Event<LineInfoDestination>>()
    val navigation: LiveData<Event<LineInfoDestination>>
        get() = _navigation

    init {
        fetchBusLines()
    }

    private fun fetchBusLines() {
        viewModelScope.launch {
            _state.value = LineInfoUiModel.Loading(R.string.common_loading_message)
            _state.value = LineInfoUiModel.ShowBusLines(getBusLines())
        }
    }

    fun onBusLineClicked(busLine: BusLine) {

    }

    @SuppressLint("DefaultLocale")
    fun onSearchQuerySubmitted(text: String) {
        viewModelScope.launch {
            if (text.isBlank()) {
                viewModelScope.launch {
                    _state.value = LineInfoUiModel.ShowBusLines(getBusLines())
                }
            } else {
                val filteredLines = getBusLines().filter {
                    val labelMatches = it.label.toUpperCase().contains(text.toUpperCase())
                    val nameAMatches = it.nameA.toUpperCase().contains(text.toUpperCase())
                    val nameBMatches = it.nameB.toUpperCase().contains(text.toUpperCase())
                    labelMatches || nameAMatches || nameBMatches
                }
                if (filteredLines.isEmpty()) {
                    _state.value = LineInfoUiModel.ShowEmptyResults
                } else {
                    _state.value = LineInfoUiModel.ShowBusLines(filteredLines)
                }
            }
        }
    }

    @SuppressLint("DefaultLocale")
    fun onSearchQueryTextChanged(text: String) {
        if (text.isBlank()) {
            viewModelScope.launch {
                _state.value = LineInfoUiModel.ShowBusLines(getBusLines())
            }
        }
    }

}