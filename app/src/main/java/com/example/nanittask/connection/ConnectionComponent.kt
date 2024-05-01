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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.nanittask.R
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
            navController.navigate(MainNavDestinations.Birthday.createRoute(it))
        }
    )
}

@Composable
fun ConnectionComponent(
    connectionScreenState: ConnectionScreenState,
    onIpAddressChanged: (String) -> Unit = {},
    onConnectClicked: (String) -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TextField(
                value = connectionScreenState.ipAddress,
                onValueChange = onIpAddressChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                label = { Text(text = stringResource(R.string.connection_label)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                singleLine = true
            )

            Button(
                onClick = { onConnectClicked(connectionScreenState.ipAddress) },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = stringResource(R.string.connection_button_text))
            }
        }
    }
}

@Preview
@Composable
private fun ConnectionComponentPreview() {
    ConnectionComponent(ConnectionScreenState())
}