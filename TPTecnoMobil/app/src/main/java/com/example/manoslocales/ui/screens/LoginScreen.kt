package com.example.manoslocales

import androidx.compose.foundation.Image
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
import com.example.manoslocales.ui.components.CommonComponents
import androidx.compose.foundation.clickable
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(onNavigate: (String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Bienvenido",
            fontSize = 28.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(24.dp))
        CommonComponents.OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = "Usuario",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(16.dp))
        CommonComponents.OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = "Contraseña",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { onNavigate("feed") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC0CB).copy(alpha = 0.8f),
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC0CB).copy(alpha = 0.4f),
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Olvide mi contraseña")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text("¿No tenés cuenta?")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Registrate",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onNavigate("registro") }
            )
        }
    }
}