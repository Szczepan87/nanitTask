package com.example.nanittask.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.json.Json
import javax.inject.Inject

class WebsocketClient @Inject constructor() {

    private val client = HttpClient {
        install(WebSockets)
    }

    suspend fun connect(ipAddress: String, port: Int = 8080): Flow<BirthdayDTO> {
        var flow: Flow<BirthdayDTO?> = emptyFlow()
        client.webSocket(method = HttpMethod.Get, host = ipAddress, port = port, path = "/nanit") {
            flow = incoming.consumeAsFlow().mapNotNull {
                try {
                    Json.decodeFromString<BirthdayDTO>((it as? Frame.Text)?.readText() ?: "")
                } catch (e: Exception) {
                    null
                }
            }
        }
        return flow.filterNotNull()
    }
}