package com.example.manoslocales.ui.screens.authentication

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.manoslocales.R
import com.example.manoslocales.ui.screens.mainmenu.MainMenuActivity
import com.example.manoslocales.ui.screens.authentication.rememberpw.RememberPWActivity
import com.example.manoslocales.ui.screens.authentication.SignUpActivity
import com.example.manoslocales.ui.theme.ManosLocalesTheme
import com.example.manoslocales.presentation.UserViewModel
import com.example.manoslocales.utils.textFieldColors
@Composable
fun LoginScreen(
    onNavigateToSignup: () -> Unit = {},
    onNavigateToMainMenu: () -> Unit = {},
    userViewModel: UserViewModel = hiltViewModel()
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val isAuthenticated by userViewModel.isAuthenticated.collectAsState()
    val loginError by userViewModel.loginError.collectAsState()

    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) {
            val intent = Intent(context, MainMenuActivity::class.java)
            context.startActivity(intent)
        }
    }

    LaunchedEffect(loginError) {
        loginError?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            userViewModel.clearLoginError()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFe3d6c3)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(0.85f)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo de la app",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(120.dp)
                )
                Text("Bienvenido", color = Color.Black, fontSize = 28.sp)
            }

            Text(
                "Inicie sesión para continuar",
                color = Color.Black,
                fontSize = 15.sp,
                modifier = Modifier.padding(top = 10.dp)
            )

            TextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text("Usuario", color = Color.Black) },
                singleLine = true,
                colors = textFieldColors()
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña", color = Color.Black) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = textFieldColors()
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Olvidé mi contraseña",
                    color = Color.Black,
                    modifier = Modifier.clickable {
                        val intent = Intent(context, RememberPWActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            }

            Button(
                onClick = {
                    userViewModel.login(username, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC0CB).copy(alpha = 0.8f)),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("Iniciar Sesión", color = Color.Black)
            }

            Text(
                text = "¿No tenés cuenta? Registrate.",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    val intent = Intent(context, SignUpActivity::class.java)
                    context.startActivity(intent)
                }
            )
        }
    }
}