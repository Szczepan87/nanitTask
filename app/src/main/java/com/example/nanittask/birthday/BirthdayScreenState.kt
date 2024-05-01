package com.example.nanittask.birthday

import com.example.nanittaskdomain.ColorTheme

data class BirthdayScreenState(
    val isLoading: Boolean = false,
    val name: String = "Christiano Ronaldo",
    val age: Age = Age(age = 1, isInMonths = true),
    val theme: ColorTheme = ColorTheme.ELEPHANT
)
