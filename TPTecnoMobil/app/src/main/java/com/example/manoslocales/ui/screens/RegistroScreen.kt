package com.example.manoslocales.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manoslocales.R
import com.example.manoslocales.ui.components.CommonComponents
import com.example.manoslocales.ui.utils.isValidEmail
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import com.example.manoslocales.ui.theme.RosaClaroSemi

@Composable
fun RegistroScreen(onNavigate: (String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var errorUsername by remember { mutableStateOf("") }
    var errorEmail by remember { mutableStateOf("") }
    var errorPassword by remember { mutableStateOf("") }
    var errorConfirmPassword by remember { mutableStateOf("") }

    fun validarCampos(): Boolean {
        var esValido = true
        errorUsername = if (username.isBlank()) {
            esValido = false
            "El usuario no puede estar vacío"
        } else ""
        errorEmail = if (email.isBlank()) {
            esValido = false
            "El email no puede estar vacío"
        } else if (!isValidEmail(email)) {
            esValido = false
            "El email no es válido"
        } else ""
        errorPassword = if (password.length < 6) {
            esValido = false
            "La contraseña debe tener al menos 6 caracteres"
        } else ""
        errorConfirmPassword = if (confirmPassword != password) {
            esValido = false
            "Las contraseñas no coinciden"
        } else ""
        return esValido
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Registrarse", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(16.dp))
        CommonComponents.OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                errorUsername = ""
            },
            label = "Nombre de usuario",
            isError = errorUsername.isNotEmpty(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        if (errorUsername.isNotEmpty()) {
            Text(errorUsername, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(8.dp))
        CommonComponents.OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                errorEmail = ""
            },
            label = "Email",
            isError = errorEmail.isNotEmpty(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        if (errorEmail.isNotEmpty()) {
            Text(errorEmail, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(8.dp))
        CommonComponents.OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                errorPassword = ""
            },
            label = "Contraseña",
            isError = errorPassword.isNotEmpty(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        if (errorPassword.isNotEmpty()) {
            Text(errorPassword, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(8.dp))
        CommonComponents.OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                errorConfirmPassword = ""
            },
            label = "Confirmar contraseña",
            isError = errorConfirmPassword.isNotEmpty(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        if (errorConfirmPassword.isNotEmpty()) {
            Text(errorConfirmPassword, color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                if (validarCampos()) {
                    onNavigate("login")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = RosaClaroSemi,
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear cuenta")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "¿Ya tenés cuenta? Iniciá sesión",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable { onNavigate("login") }
        )
    }
}