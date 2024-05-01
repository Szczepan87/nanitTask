package com.example.nanittask.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nanittask.birthday.BirthdayScreen
import com.example.nanittask.connection.ConnectionScreen
import com.example.nanittask.ui.theme.NanitTaskTheme
import dagger.hilt.android.AndroidEntryPoint

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
                    composable(
                        route = MainNavDestinations.Birthday.route,
                        arguments = listOf(navArgument(ARG_IP_ADDRESS) {
                            type = NavType.StringType
                        })
                    ) {
                        BirthdayScreen()
                    }
                }
            }
        }
    }
}
