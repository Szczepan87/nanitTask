package com.example.nanittask.birthday

import com.example.nanittask.ColorTheme

data class BirthdayScreenState(
    val name: String = "Christiano Ronaldo",
    val age: Int = 1,
    val theme: ColorTheme = ColorTheme.ELEPHANT,
    val isInMonths: Boolean = true
)
