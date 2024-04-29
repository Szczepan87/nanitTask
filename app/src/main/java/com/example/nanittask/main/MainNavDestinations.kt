package com.example.nanittask.main

sealed class MainNavDestinations(val route: String) {
    data object Connection : MainNavDestinations("connection")
    data object Birthday : MainNavDestinations("birthday")
}