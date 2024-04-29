package com.example.nanittask.connection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nanittask.main.MainNavDestinations

@Composable
fun ConnectionScreen(
    viewModel: ConnectionScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsState()
    ConnectionComponent(
        connectionScreenState = state.value,
        onIpAddressChanged = { viewModel.onIpAddressChanged(it) },
        onConnectClicked = {
            viewModel.onConnectClicked()
            navController.navigate(MainNavDestinations.Birthday.route)
        }
    )
}

@Composable
fun ConnectionComponent(
    connectionScreenState: ConnectionScreenState,
    onIpAddressChanged: (String) -> Unit = {},
    onConnectClicked: () -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TextField(
                value = connectionScreenState.ipAddress,
                onValueChange = onIpAddressChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                label = { Text(text = "IP Address") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Button(onClick = onConnectClicked, modifier = Modifier.padding(16.dp)) {
                Text(text = "Connect")
            }
        }
    }
}

@Preview
@Composable
private fun ConnectionComponentPreview() {
    ConnectionComponent(ConnectionScreenState())
}