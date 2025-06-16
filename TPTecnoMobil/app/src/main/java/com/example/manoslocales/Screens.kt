package com.example.manoslocales

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

val RosaClaro = Color(0xFFFFC0CB)
val RosaClaroTransparente = RosaClaro.copy(alpha = 0.4f)
val RosaClaroSemi = RosaClaro.copy(alpha = 0.8f)
val RosaClaroSemi2 = RosaClaro.copy(alpha = 0.5f)

@Composable
fun SplashScreen(logo: Painter, onNavigate: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = logo,
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Bienvenido a la Aplicación",
            style = TextStyle(fontSize = 24.sp),
            color = Color.Black
        )
    }
    LaunchedEffect(Unit) {
        delay(3000)
        onNavigate()
    }
}

@Composable
fun MainScreen() {
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

@Composable
fun MainScreens() {
    var currentScreen by remember { mutableStateOf("login") }

    when (currentScreen) {
        "login" -> LoginScreens(onNavigate = { currentScreen = it })
        "registro" -> RegistroScreens(onNavigate = { currentScreen = it })
        "feed" -> FeedScreen(onNavigate = { currentScreen = it })
        "settings" -> SettingsScreens(onNavigate = { currentScreen = it })
    }
}

@Composable
fun LoginScreens(onNavigate: (String) -> Unit) {
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
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Usuario") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
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
        Button(
            onClick = { onNavigate("feed") },
            colors = ButtonDefaults.buttonColors(
                containerColor = RosaClaroSemi,
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = RosaClaroTransparente,
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

    fun validarCampos(): Boolean {
        var esValido = true
        errorUsername = if (username.isBlank()) {
            esValido = false
            "El usuario no puede estar vacío"
        } else ""
        errorEmail = if (email.isBlank()) {
            esValido = false
            "El email no puede estar vacío"
        } else if (!isValidEmails(email)) {
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

fun isValidEmails(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

data class Product(val name: String, val description: String, val imageUrl: Int)

@Composable
fun FeedScreen(onNavigate: (String) -> Unit) {
    val products = listOf(
        Product("Producto 1", "Descripción del producto 1", R.drawable.producto1),
        Product("Producto 2", "Descripción del producto 2", R.drawable.producto2),
        Product("Producto 3", "Descripción del producto 3", R.drawable.producto3),
        Product("Producto 4", "Descripción del producto 4", R.drawable.producto4),
        Product("Producto 5", "Descripción del producto 5", R.drawable.producto5),
        Product("Producto 6", "Descripción del producto 6", R.drawable.producto6),
        Product("Producto 7", "Descripción del producto 7", R.drawable.producto7),
        Product("Producto 8", "Descripción del producto 8", R.drawable.producto8),
        Product("Producto 9", "Descripción del producto 9", R.drawable.producto9),
        Product("Producto 10", "Descripción del producto 10", R.drawable.producto10),
        Product("Producto 11", "Descripción del producto 10", R.drawable.producto11),
        Product("Producto 12", "Descripción del producto 10", R.drawable.producto12),
        Product("Producto 13", "Descripción del producto 10", R.drawable.producto13),
        Product("Producto 14", "Descripción del producto 10", R.drawable.producto14),
        Product("Producto 15", "Descripción del producto 10", R.drawable.producto15),
        Product("Producto 16", "Descripción del producto 10", R.drawable.producto16),
        Product("Producto 17", "Descripción del producto 10", R.drawable.producto17),
        Product("Producto 18", "Descripción del producto 10", R.drawable.producto18),
        Product("Producto 19", "Descripción del producto 10", R.drawable.producto19),
        Product("Producto 20", "Descripción del producto 10", R.drawable.producto20),
    )

    Column {
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(64.dp))
                Image(
                    contentDescription = "Logo",
                    painter = painterResource(id = R.drawable.logo),
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Manos Locales",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            IconButton(onClick = { onNavigate("settings") }) {
                Icon(Icons.Filled.Settings, contentDescription = "Settings")
            }
        }
        LazyColumn(modifier = Modifier.padding(bottom = 16.dp)) {
            items(products) { product ->
                ProductCard(product = product)
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = RosaClaroTransparente)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = product.imageUrl),
                contentDescription = product.name,
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun SettingsScreens(onNavigate: (String) -> Unit) {
    var selectedCategories by remember { mutableStateOf(listOf<String>()) }
    var preferredLocation by remember { mutableStateOf("") }
    var notificationFrequency by remember { mutableStateOf("Diariamente") }

    BackHandler {
        onNavigate("feed")
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(32.dp))
        Text("Categorías de Productos", style = MaterialTheme.typography.titleLarge)
        val categories = listOf("Alimentos", "Textiles", "Artesanías")

        categories.forEach { category ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = selectedCategories.contains(category),
                    onCheckedChange = { isChecked ->
                        selectedCategories = if (isChecked) {
                            selectedCategories + category
                        } else {
                            selectedCategories - category
                        }
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = RosaClaro,
                        uncheckedColor = RosaClaroSemi,
                        checkmarkColor = Color.Black
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(category)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Ubicación Preferida", style = MaterialTheme.typography.titleLarge)
        TextField(
            value = preferredLocation,
            onValueChange = { preferredLocation = it },
            label = { Text("Ciudad o Región") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Tiempo de Notificaciones", style = MaterialTheme.typography.titleLarge)
        val frequencies = listOf("Cada hora", "Diariamente", "Semanalmente")

        frequencies.forEach { frequency ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = notificationFrequency == frequency,
                    onClick = { notificationFrequency = frequency },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = RosaClaro,
                        unselectedColor = RosaClaroSemi
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(frequency)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                println("Preferencias guardadas (simulado)")
                onNavigate("feed")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = RosaClaroSemi,
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                println("Preferencias no guardadas (simulado)")
                onNavigate("feed")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = RosaClaroSemi2,
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancelar")
        }
    }
}