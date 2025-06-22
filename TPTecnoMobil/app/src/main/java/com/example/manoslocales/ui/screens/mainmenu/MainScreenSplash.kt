package com.example.manoslocales.ui.screens.mainmenu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.example.manoslocales.R

@Composable
fun MainScreenSplash() {
    var isSplashScreenVisible by remember { mutableStateOf(true) }
    val logo = painterResource(id = R.drawable.logo)

    if (isSplashScreenVisible) {
        SplashScreen(logo = logo) {
            isSplashScreenVisible = false
        }
    } else {
        MainScreens()
    }
}