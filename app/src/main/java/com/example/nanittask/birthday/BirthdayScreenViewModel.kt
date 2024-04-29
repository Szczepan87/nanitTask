package com.example.nanittask.birthday

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BirthdayScreenViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(BirthdayScreenState())
    val state: StateFlow<BirthdayScreenState> = _state
}