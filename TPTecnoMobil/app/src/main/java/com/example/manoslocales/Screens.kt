package com.example.manoslocales

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreens() {
    var currentScreen by remember { mutableStateOf("login") }

    when (currentScreen) {
        "login" -> LoginScreens(onNavigate = { currentScreen = it })
        "registro" -> RegistroScreens(onNavigate = { currentScreen = it })
        //"feed" -> FeedScreens(onNavigate = { currentScreen = it })
    }
}

@Composable
fun LoginScreens(onNavigate: (String) -> Unit) {
    // Estados para almacenar lo que escribe el usuario
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Layout principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Logo de la aplicación
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título de bienvenida
        Text(
            text = "Bienvenido",
            fontSize = 28.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo de texto nombre de usuario
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Usuario") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de iniciar sesión
        Button(
            onClick = {
                // Aquí iría la lógica de validación del login
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }
        //Boton de olvide mi contrasenia
        Button(
            onClick = {
                // Aquí iría la lógica de validación del login
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors( containerColor = Color.LightGray,contentColor = Color.Black)
        ) {
            Text("Olvide mi contraseña")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto para registrarse
        Row {
            Text("¿No tenés cuenta?")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Registrate",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    // Aquí iría la lógica para navegar a la pantalla de registro
                    onNavigate("registro")
                }
            )
        }
    }
}

@Composable
fun RegistroScreens(onNavigate: (String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var errorUsername by remember { mutableStateOf("") }
    var errorEmail by remember { mutableStateOf("") }
    var errorPassword by remember { mutableStateOf("") }
    var errorConfirmPassword by remember { mutableStateOf("") }

    // Función para validar campos al apretar el botón
    fun validarCampos(): Boolean {
        var esValido = true

        // Validar nombre de usuario
        errorUsername = if (username.isBlank()) {
            esValido = false
            "El usuario no puede estar vacío"
        } else ""


        // Validar email
        errorEmail = if (email.isBlank()) {
            esValido = false
            "El email no puede estar vacío"
        } else if (!isValidEmails(email)) {
            esValido = false
            "El email no es válido"
        } else ""

        // Validar contraseña
        errorPassword = if (password.length < 6) {
            esValido = false
            "La contraseña debe tener al menos 6 caracteres"
        } else ""

        // Validar confirmación
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
        Text("Registrarse", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(16.dp))

        // Campo: Usuario
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                errorUsername = ""
            },
            label = { Text("Nombre de usuario") },
            isError = errorUsername.isNotEmpty(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        if (errorUsername.isNotEmpty()) {
            Text(errorUsername, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                errorEmail = ""
            },
            label = { Text("Email") },
            isError = errorEmail.isNotEmpty(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        if (errorEmail.isNotEmpty()) {
            Text(errorEmail, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                errorPassword = ""
            },
            label = { Text("Contraseña") },
            isError = errorPassword.isNotEmpty(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        if (errorPassword.isNotEmpty()) {
            Text(errorPassword, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Confirmar contraseña
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                errorConfirmPassword = ""
            },
            label = { Text("Confirmar contraseña") },
            isError = errorConfirmPassword.isNotEmpty(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        if (errorConfirmPassword.isNotEmpty()) {
            Text(errorConfirmPassword, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para crear cuenta
        Button(
            onClick = {
                if (validarCampos()) {
                    // Continuar con la lógica de guardado o navegación
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear cuenta")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Vuelta al login
        Text(
            text = "¿Ya tenés cuenta? Iniciá sesión",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                // Navegar al login
                onNavigate("login")
            }
        )
    }
}

fun isValidEmails(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Composable
fun FeedScreens() {

}
@Composable
fun SettingsScreens() {
    // Implementación de la pantalla de Configuración
}