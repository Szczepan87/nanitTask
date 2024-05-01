package com.example.nanittask.birthday

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanittask.main.ARG_IP_ADDRESS
import com.example.nanittaskdomain.DisconnectWebClientUseCase
import com.example.nanittaskdomain.GetBirthdayDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlin.math.absoluteValue

@HiltViewModel
class BirthdayScreenViewModel @Inject constructor(
    getBirthdayDetailsUseCase: GetBirthdayDetailsUseCase,
    private val disconnectWebClientUseCase: DisconnectWebClientUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val ipAddressEntered = requireNotNull(savedStateHandle.get<String>(ARG_IP_ADDRESS))

    private val _state = MutableStateFlow(BirthdayScreenState())
    val state: StateFlow<BirthdayScreenState> = _state

    init {
        _state.update { it.copy(isLoading = true) }
        getBirthdayDetailsUseCase(ipAddressEntered).onEach { details ->
            _state.update {
                it.copy(
                    isLoading = false,
                    name = details.name,
                    age = calculateAge(details.dob),
                    theme = details.theme,
                )
            }
        }.launchIn(viewModelScope)
    }

    // refactor to return Age object of int and flag of months or years
    private fun calculateAge(dob: LocalDateTime): Int {
        val currentDateTime = LocalDateTime.now()
        return ChronoUnit.MONTHS.between(dob, currentDateTime).toInt().absoluteValue
    }

    override fun onCleared() {
        super.onCleared()
        disconnectWebClientUseCase()
    }
}