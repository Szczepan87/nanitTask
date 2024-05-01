package com.example.nanittask.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

class WebsocketClient @Inject constructor() {
    private val client = HttpClient {
        install(WebSockets)
    }
    private var job: Job = Job()
    val birthdayChannel = Channel<BirthdayDTO?>()

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun connect(ipAddress: String, port: Int = 8080) {
        job = GlobalScope.launch {
            client.webSocket(
                method = HttpMethod.Get,
                host = ipAddress,
                port = port,
                path = "/nanit"
            ) {
                updateChannel()
            }
        }
    }

    fun disconnect() {
        client.close()
        birthdayChannel.close()
        job.cancel()
    }

    private suspend fun DefaultClientWebSocketSession.updateChannel() {
        incoming.consumeEach { frame ->
            if (frame is Frame.Text) {
                val birthdayDetails = try {
                    Json.decodeFromString<BirthdayDTO>(frame.readText())
                } catch (e: Exception) {
                    null
                }
                birthdayChannel.trySend(birthdayDetails)
            }
        }
    }
}
