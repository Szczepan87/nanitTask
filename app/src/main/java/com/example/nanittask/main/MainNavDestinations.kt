package com.example.nanittask.main

sealed class MainNavDestinations(val route: String) {
    data object Connection : MainNavDestinations("connection")
    data object Birthday : MainNavDestinations("birthday?$ARG_IP_ADDRESS={$ARG_IP_ADDRESS}"){
        fun createRoute(ipAddress: String): String {
            return "birthday?$ARG_IP_ADDRESS=$ipAddress"
        }
    }
}