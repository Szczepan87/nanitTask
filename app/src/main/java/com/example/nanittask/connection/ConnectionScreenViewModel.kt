package com.example.nanittask.connection

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ConnectionScreenViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ConnectionScreenState())
    val state: StateFlow<ConnectionScreenState> = _state

    fun onIpAddressChanged(ipAddress: String) {
        _state.value = _state.value.copy(ipAddress = ipAddress)
    }

    fun onConnectClicked() {
        _state.value = _state.value.copy(isLoading = true)
    }
}