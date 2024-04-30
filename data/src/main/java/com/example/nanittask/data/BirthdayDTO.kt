package com.example.nanittask.data

import kotlinx.serialization.Serializable

@Serializable
data class BirthdayDTO(
    val name: String,
    val dob: Long,
    val theme: String
) {
}