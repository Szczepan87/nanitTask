package com.example.nanittaskdomain

import com.example.nanittask.data.WebsocketClient
import javax.inject.Inject

class DisconnectWebClientUseCase @Inject constructor(
private val client: WebsocketClient
) {
    operator fun invoke() {
        client.disconnect()
    }
}