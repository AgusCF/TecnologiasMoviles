@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.manoslocales

import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.painter.Painter
import androidx.activity.compose.BackHandler



val RosaClaro = Color(0xFFFFC0CB)
val RosaClaroTransparente = RosaClaro.copy(alpha = 0.4f)
val RosaClaroSemi = RosaClaro.copy(alpha = 0.8f)
val RosaClaroSemi2 = RosaClaro.copy(alpha = 0.5f)
@Composable
fun SplashScreen(logo: Painter, onNavigate: () -> Unit) {
    // Usar un Column para centrar el contenido verticalmente
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), // Color de fondo de la splash screen
        verticalArrangement = Arrangement.Center, // Alinear verticalmente al centro
        horizontalAlignment = Alignment.CenterHorizontally // Alinear horizontalmente al centro
    ) {
        // Mostrar el logo
        Image(
            painter = logo,
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp) // Ajusta el tamaño según sea necesario
        )

        // Espaciador entre el logo y el texto
        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar el mensaje adicional
        Text(
            text = "Bienvenido a la Aplicación",
            style = TextStyle(fontSize = 24.sp),
            color = Color.Black
        )
    }

    // Aquí puedes usar un Timer o un delay para navegar a la siguiente pantalla después de un tiempo
    LaunchedEffect(Unit) {
        delay(3000) // Espera 3 segundos
        onNavigate() // Navega a la siguiente pantalla
    }
}
@Composable
fun MainScreen() {
    // Aquí puedes utilizar un estado para controlar si la pantalla de splash se debe mostrar
    var isSplashScreenVisible by remember { mutableStateOf(true) }

    // Cargar tu logo
    val logo = painterResource(id = R.drawable.logo) // Asegúrate de que la ruta sea correcta

    if (isSplashScreenVisible) {
        SplashScreen(logo = logo) {
            // Cuando termine la splash screen, cambia el estado
            isSplashScreenVisible = false
        }
    } else {
        // Aquí irían los contenidos de tu aplicación después de la splash screen
        MainScreens() // Por ejemplo, tu contenido principal
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
                onNavigate("feed")//Deberia llevarte al feed solo si las credenciales son correctas
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = RosaClaroSemi,
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }
        //Boton de olvide mi contrasenia
        Button(
            onClick = {
                // Aquí iría la lógica de validación del login
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = RosaClaroTransparente,
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
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
        // Logo de la aplicación
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
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
                onNavigate("login")//Deberia llevarte al login solo si los campos estan bien cargados
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
data class Product(val name: String, val description: String, val imageUrl: String)

@Composable
fun FeedScreen(onNavigate: (String) -> Unit) {
    val products = listOf(
        Product("Producto 1", "Descripción del producto 1", "url_imagen_1"),
        Product("Producto 2", "Descripción del producto 2", "url_imagen_2"),
        Product("Producto 3", "Descripción del producto 3", "url_imagen_3"),
        Product("Producto 1", "Descripción del producto 4", "url_imagen_4"),
        Product("Producto 2", "Descripción del producto 5", "url_imagen_5"),
        Product("Producto 1", "Descripción del producto 6", "url_imagen_6"),
        Product("Producto 2", "Descripción del producto 7", "url_imagen_7"),
        Product("Producto 1", "Descripción del producto 8", "url_imagen_8"),
        Product("Producto 2", "Descripción del producto 9", "url_imagen_9"),
        Product("Producto 1", "Descripción del producto 10", "url_imagen_10"),
        Product("Producto 2", "Descripción del producto 11", "url_imagen_11"),
        Product("Producto 1", "Descripción del producto 12", "url_imagen_12"),
        Product("Producto 2", "Descripción del producto 13", "url_imagen_13"),
        Product("Producto 1", "Descripción del producto 14", "url_imagen_14"),
        Product("Producto 2", "Descripción del producto 15", "url_imagen_15"),
    ) // Datos estáticos

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
                // Imagen del logo
                Spacer(modifier = Modifier.width(64.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Título de la app
                Text(
                    text = "Manos Locales",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            // Botón de configuración (ícono de engranaje)
            IconButton(onClick = { onNavigate("settings") }) {
                Icon(Icons.Filled.Settings, contentDescription = "Settings")
            }
        }



        LazyColumn(modifier = Modifier.padding(bottom = 16.dp)) {
            items(products.size) { index ->
                ProductCard(product = products[index])
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = RosaClaroTransparente // Rosa claro con 40% de opacidad
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Coming Soon",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun SettingsScreens(onNavigate: (String) -> Unit) {
    var selectedCategories by remember { mutableStateOf(listOf<String>()) }
    var preferredLocation by remember { mutableStateOf("") }
    var notificationFrequency by remember { mutableStateOf("Diariamente") }

    // Manejar el botón de retroceso
    BackHandler {
        onNavigate("feed") // Navegar de regreso al feed
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