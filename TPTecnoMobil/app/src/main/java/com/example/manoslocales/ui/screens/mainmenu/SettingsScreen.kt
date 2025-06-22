package com.example.manoslocales.ui.screens.mainmenu

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.manoslocales.R
import com.example.manoslocales.ui.screens.authentication.LoginActivity
import com.example.manoslocales.ui.theme.RosaClaro
import com.example.manoslocales.ui.theme.RosaClaroSemi
import com.example.manoslocales.ui.theme.RosaClaroSemi2
import com.example.manoslocales.presentation.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateToMainMenu: () -> Unit = {},
    onNavigateToFavorites: () -> Unit = {},
    onNavigateToModifyAccount: () -> Unit = {},
    userViewModel: UserViewModel = hiltViewModel()
) {
    var selectedCategories by remember { mutableStateOf(listOf<String>()) }
    var preferredLocation by remember { mutableStateOf("") }
    var notificationFrequency by remember { mutableStateOf("Diariamente") }
    var notificationTimer by remember { mutableStateOf("") }
    var isFavoriteChecked by remember { mutableStateOf(false) }
    var isNotificationChecked by remember { mutableStateOf(false) }

    val context = LocalContext.current

    BackHandler {
        // Navegar a la pantalla de inicio
        val intent = Intent(context, MainMenuActivity::class.java)
        context.startActivity(intent)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Manos Locales",
                            color = Color.White
                        )
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logotipo",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF404934)
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFe3d6c3),
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        IconButton(onClick = { /* Navegar a favoritos */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.favorite),
                                contentDescription = "Favoritos",
                                modifier = Modifier.size(29.dp)
                            )
                        }
                        IconButton(onClick = { /* Navegar a inicio */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.home),
                                contentDescription = "Inicio",
                                modifier = Modifier.size(25.dp)
                            )
                        }
                        IconButton(onClick = { /* Navegar a submenu */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.submenu),
                                contentDescription = "Submenu",
                                modifier = Modifier.size(29.dp)
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFe3d6c3))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(56.dp)
                        .clickable { /* Navegar a modificar cuenta */ }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.usuario),
                            contentDescription = "Mi cuenta",
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Mi cuenta", fontSize = 20.sp, color = Color(0xFF7C5C44))
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(56.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.favorite),
                            contentDescription = "Favoritos",
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Recibir actualizaciones de favoritos", fontSize = 20.sp, color = Color(0xFF7C5C44))
                    }

                    Checkbox(
                        checked = isFavoriteChecked,
                        onCheckedChange = { isFavoriteChecked = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF7C5C44),
                            uncheckedColor = Color(0xFF7C5C44),
                            checkmarkColor = Color.White
                        )
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(56.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.campana),
                            contentDescription = "Campana",
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Recibir notificaciones de ofertas", fontSize = 20.sp, color = Color(0xFF7C5C44))
                    }

                    Checkbox(
                        checked = isNotificationChecked,
                        onCheckedChange = { isNotificationChecked = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF7C5C44),
                            uncheckedColor = Color(0xFF7C5C44),
                            checkmarkColor = Color.White
                        )
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(56.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.alarma),
                        contentDescription = "tiempo",
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Recibir notificaciones cada", fontSize = 20.sp, color = Color(0xFF7C5C44))
                    TextField(
                        value = notificationTimer,
                        onValueChange = { notificationTimer = it },
                        modifier = Modifier
                            .width(60.dp)
                            .height(56.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text("0", fontSize = 20.sp) },
                        textStyle = TextStyle(fontSize = 20.sp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color(0xFF7C5C44),
                            unfocusedTextColor = Color(0xFF7C5C44),
                            focusedLabelColor = Color(0xFF7C5C44),
                            unfocusedLabelColor = Color(0xFF7C5C44),
                            cursorColor = Color(0xFF7C5C44),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    Text("hs", fontSize = 20.sp, color = Color(0xFF7C5C44))
                }

                /*Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(56.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "message/rfc822"
                                putExtra(Intent.EXTRA_EMAIL, arrayOf("cgomez453@alumnos.iua.edu.ar"))
                                putExtra(Intent.EXTRA_SUBJECT, "Consulta sobre la app")
                                putExtra(Intent.EXTRA_TEXT, "Hola, tengo una consulta sobre...")
                            }

                            if (intent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(Intent.createChooser(intent, "Enviar correo con:"))
                            } else {
                                Toast.makeText(
                                    context,
                                    "No se encontró una app de correo",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.mail),
                            contentDescription = "mail",
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Contactar con el desarrollador", fontSize = 20.sp, color = Color(0xFF7C5C44))
                    }
                }*/

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(56.dp)
                        .clickable {
                            userViewModel.logout()
                            val intent = Intent(context, LoginActivity::class.java)
                            context.startActivity(intent)
                        }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.usuario),
                            contentDescription = "cerrar sesión",
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Cerrar sesión", fontSize = 20.sp, color = Color(0xFF7C5C44))
                    }
                }
            }
        }
    }
}