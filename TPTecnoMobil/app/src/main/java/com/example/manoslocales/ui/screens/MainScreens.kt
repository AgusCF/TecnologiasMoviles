package com.example.manoslocales.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.manoslocales.LoginScreen

@Composable
fun MainScreens() {
    var currentScreen by remember { mutableStateOf("login") }

    when (currentScreen) {
        "login" -> LoginScreen(onNavigate = { currentScreen = it })
        "registro" -> RegistroScreen(onNavigate = { currentScreen = it })
        "feed" -> FeedScreen(onNavigate = { currentScreen = it })
        "settings" -> SettingsScreen(onNavigate = { currentScreen = it })
    }
}