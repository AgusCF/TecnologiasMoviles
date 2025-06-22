package com.example.manoslocales.ui.screens.authentication.rememberpw

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.manoslocales.ui.screens.authentication.LoginActivity


class RememberPWActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RememberPWNavHost()
        }
    }
}

@Composable
fun RememberPWNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = "rememberPW") {
        composable("RememberPW") {
            RememberPW(
                onNext = { navController.navigate("verifyCode") }
            )
        }
        composable("verifyCode") {
            VerifyCode(
                onVerified = { navController.navigate("resetPassword") }
            )
        }
        composable("resetPassword") {
            ResetPassword(
                onFinished = {
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                }
            )
        }
    }
}
