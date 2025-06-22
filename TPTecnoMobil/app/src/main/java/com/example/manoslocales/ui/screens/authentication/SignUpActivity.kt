package com.example.manoslocales.ui.screens.authentication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.manoslocales.ui.theme.ManosLocalesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ManosLocalesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegistroScreen(
                        onNavigateToLogin = {
                            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                            finish()
                        },
                        onNavigateToMainMenu = {
                            // Navigate to main menu
                            finish()
                        }
                    )
                }
            }
        }
    }
}

