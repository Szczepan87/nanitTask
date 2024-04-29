package com.example.nanittask.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nanittask.birthday.BirthdayScreen
import com.example.nanittask.connection.ConnectionScreen
import com.example.nanittask.ui.theme.NanitTaskTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NanitTaskTheme {
                NavHost(
                    navController = navController,
                    startDestination = MainNavDestinations.Connection.route
                ) {
                    composable(MainNavDestinations.Connection.route) {
                        ConnectionScreen(navController = navController)
                    }
                    composable(MainNavDestinations.Birthday.route) {
                        BirthdayScreen()
                    }
                }
            }
        }
    }
}
