package com.example.manoslocales.ui.screens.mainmenu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.manoslocales.ui.screens.authentication.LoginScreen
import com.example.manoslocales.ui.screens.authentication.RegistroScreen

@Composable
fun MainScreens() {
    var currentScreen by remember { mutableStateOf("login") }

    when (currentScreen) {
        "login" -> LoginScreen(
            onNavigateToSignup = { currentScreen = "registro" },
            onNavigateToMainMenu = { currentScreen = "feed" }
        )
        "registro" -> RegistroScreen(
            onNavigateToLogin = { currentScreen = "login" },
            onNavigateToMainMenu = { currentScreen = "feed" }
        )
        "feed" -> FeedScreen(onNavigate = { currentScreen = it })
        "settings" -> SettingsScreen(
            onNavigateToMainMenu = { currentScreen = "feed" },
            onNavigateToFavorites = { currentScreen = "favorites" },
            onNavigateToModifyAccount = { currentScreen = "modify" }
        )
    }
}