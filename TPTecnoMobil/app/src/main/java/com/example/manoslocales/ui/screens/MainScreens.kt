package com.example.manoslocales.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.manoslocales.LoginScreen
import com.example.manoslocales.ui.screens.FeedScreen
import com.example.manoslocales.ui.screens.RegistroScreen
import com.example.manoslocales.ui.screens.SettingsScreen
import com.example.manoslocales.viewmodel.HomeViewModel

@Composable
fun MainScreens() {
    var currentScreen by remember { mutableStateOf("login") }

    when (currentScreen) {
        "login" -> LoginScreen(onNavigate = { currentScreen = it })
        "registro" -> RegistroScreen(onNavigate = { currentScreen = it })
        "feed" -> {
            val viewModel = viewModel { HomeViewModel() }
            FeedScreen(viewModel = viewModel, onNavigate = { currentScreen = it })
        }
        "settings" -> SettingsScreen(onNavigate = { currentScreen = it })
    }
}