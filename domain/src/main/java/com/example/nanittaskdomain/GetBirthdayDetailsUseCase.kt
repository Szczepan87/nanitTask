package com.example.nanittaskdomain

import com.example.nanittask.data.WebsocketClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class GetBirthdayDetailsUseCase @Inject constructor(
    private val client: WebsocketClient
) {

    suspend operator fun invoke(ipAddress: String): Flow<BirthdayDetails> {
        return client.connect(ipAddress).map {
            BirthdayDetails(it.name, getLocalDateTime(it.dob), getTheme(it.theme))
        }
    }

    private fun getTheme(theme: String): ColorTheme {
        return try {
            ColorTheme.valueOf(theme.uppercase())
        } catch (e: IllegalArgumentException) {
            ColorTheme.ELEPHANT
        }
    }

    private fun getLocalDateTime(dob: Long): LocalDateTime {
        return LocalDateTime.ofEpochSecond(dob, 0, ZoneOffset.UTC)
    }
}