package com.example.nanittaskdomain

import com.example.nanittask.data.WebsocketClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class GetBirthdayDetailsUseCase @Inject constructor(
    private val client: WebsocketClient
) {
    operator fun invoke(ipAddress: String): Flow<BirthdayDetails> {
        return flow<BirthdayDetails> {
            client.connect(ipAddress)
            client.birthdayChannel.consumeEach {
                if (it == null) return@consumeEach
                emit(BirthdayDetails(it.name, getLocalDateTime(it.dob), getTheme(it.theme)))
            }
        }.flowOn(Dispatchers.IO)
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