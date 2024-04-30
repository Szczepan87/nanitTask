package com.example.nanittask.birthday

import com.example.nanittaskdomain.ColorTheme

data class BirthdayScreenState(
    val name: String = "Christiano Ronaldo",
    val age: Int = 1,
    val theme: com.example.nanittaskdomain.ColorTheme = com.example.nanittaskdomain.ColorTheme.ELEPHANT,
    val isInMonths: Boolean = true
)
