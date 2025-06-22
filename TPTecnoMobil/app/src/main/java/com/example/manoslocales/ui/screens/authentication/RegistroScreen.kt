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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.manoslocales.R
import com.example.manoslocales.ui.screens.mainmenu.MainMenuActivity
import com.example.manoslocales.ui.screens.authentication.LoginActivity
import com.example.manoslocales.ui.theme.RosaClaro
import com.example.manoslocales.ui.theme.RosaClaroSemi
import com.example.manoslocales.ui.theme.RosaClaroSemi2
import com.example.manoslocales.presentation.UserViewModel
import com.example.manoslocales.utils.isValidEmail
import com.example.manoslocales.data.local.entities.User
import com.example.manoslocales.utils.textFieldColors
import android.app.DatePickerDialog
import java.util.Calendar

@Composable
fun RegistroScreen(
    onNavigateToLogin: () -> Unit = {},
    onNavigateToMainMenu: () -> Unit = {},
    userViewModel: UserViewModel = hiltViewModel()
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var termsConditions by remember { mutableStateOf(false) }

    var errorUsername by remember { mutableStateOf("") }
    var errorEmail by remember { mutableStateOf("") }
    var errorPassword by remember { mutableStateOf("") }
    var errorConfirmPassword by remember { mutableStateOf("") }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
                dateOfBirth = selectedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.maxDate = System.currentTimeMillis()
        }
    }

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
            .background(Color(0xFFe3d6c3))
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

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre", color = Color.Black) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = textFieldColors()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = surname,
            onValueChange = { surname = it },
            label = { Text("Apellido", color = Color.Black) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = textFieldColors()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = username,
            onValueChange = {
                username = it
                errorUsername = ""
            },
            label = { Text("Nombre de usuario", color = Color.Black) },
            isError = errorUsername.isNotEmpty(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = textFieldColors()
        )
        if (errorUsername.isNotEmpty()) {
            Text(errorUsername, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = {
                email = it
                errorEmail = ""
            },
            label = { Text("Email", color = Color.Black) },
            isError = errorEmail.isNotEmpty(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = textFieldColors()
        )
        if (errorEmail.isNotEmpty()) {
            Text(errorEmail, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = {
                password = it
                errorPassword = ""
            },
            label = { Text("Contraseña", color = Color.Black) },
            isError = errorPassword.isNotEmpty(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = textFieldColors()
        )
        if (errorPassword.isNotEmpty()) {
            Text(errorPassword, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                errorConfirmPassword = ""
            },
            label = { Text("Confirmar contraseña", color = Color.Black) },
            isError = errorConfirmPassword.isNotEmpty(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = textFieldColors()
        )
        if (errorConfirmPassword.isNotEmpty()) {
            Text(errorConfirmPassword, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = dateOfBirth,
            onValueChange = {},
            label = { Text("Nacimiento", color = Color.Black) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerDialog.show() },
            enabled = false,
            readOnly = true,
            colors = textFieldColors()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = termsConditions,
                onCheckedChange = { termsConditions = it },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = Color.White,
                    uncheckedColor = Color(0xFF7C5C44),
                    checkedColor = Color(0xFF7C5C44)
                )
            )
            Text("Aceptar \"Terminos y condiciones\".", color = Color(0xFF7C5C44))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (validarCampos()) {
                    val newUser = User(
                        id = 0,
                        username = username.trim(),
                        email = email.trim(),
                        password = password,
                        city = "",
                        dateOfBirth = dateOfBirth,
                        name = name,
                        image = 1,
                        surname = surname,
                        favoriteProducts = null
                    )
                    userViewModel.register(newUser)
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
            modifier = Modifier.clickable {
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }
        )
    }
}