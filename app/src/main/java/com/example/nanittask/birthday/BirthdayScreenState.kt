package com.example.nanittask.birthday

import com.example.nanittaskdomain.ColorTheme

data class BirthdayScreenState(
    val isLoading: Boolean = false,
    val name: String = "Christiano Ronaldo",
    val age: Int = 1,
    val theme: ColorTheme = ColorTheme.ELEPHANT,
    val isInMonths: Boolean = true
)
