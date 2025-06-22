package com.example.manoslocales.ui.screens.mainmenu

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manoslocales.R
import com.example.manoslocales.utils.textFieldColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyAccountScreen(onNavigateToMainMenu: () -> Unit = {},
                  onNavigateToFavorites: () -> Unit = {},
                  onNavigateToSettings: () -> Unit = {}) {
    val name = remember { mutableStateOf("") }
    val surname = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordChecker = remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val oldPassword = remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
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
                        IconButton(onClick = { onNavigateToFavorites() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.favorite),
                                contentDescription = "Favoritos",
                                modifier = Modifier.size(29.dp)
                            )
                        }
                        IconButton(onClick = { onNavigateToMainMenu() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.home),
                                contentDescription = "Inicio",
                                modifier = Modifier.size(25.dp)
                            )
                        }
                        IconButton(onClick = { onNavigateToSettings() }) {
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
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
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

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text("Modifica tus datos", color = Color(0xFF404934), fontSize = 30.sp)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(
                            value = name.value,
                            onValueChange = { name.value = it },
                            label = { Text("Nombre", color = Color(0xFF7C5C44)) },
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            colors = textFieldColors()
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        TextField(
                            value = surname.value,
                            onValueChange = { surname.value = it },
                            label = { Text("Apellido", color = Color(0xFF7C5C44)) },
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            colors = textFieldColors()
                        )
                    }

                    TextField(
                        value = userName.value,
                        onValueChange = { userName.value = it },
                        label = { Text("Nombre de usuario", color = Color(0xFF7C5C44)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        colors = textFieldColors()
                    )

                    TextField(
                        value = oldPassword.value,
                        onValueChange = { oldPassword.value = it },
                        label = { Text("Contraseña actual", color = Color(0xFF7C5C44)) },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = textFieldColors()
                    )

                    TextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        label = { Text("Contraseña nueva", color = Color(0xFF7C5C44)) },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = textFieldColors()
                    )

                    TextField(
                        value = passwordChecker.value,
                        onValueChange = { passwordChecker.value = it },
                        label = { Text("Repita la contraseña", color = Color(0xFF7C5C44)) },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = textFieldColors()
                    )


                    Button(
                        onClick = {
                            if (password.value.length >= 8 && passwordChecker.value == password.value) {
                                Toast.makeText(
                                    context,
                                    "Datos actualizados exitosamente",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (password.value.length < 8) {
                                Toast.makeText(
                                    context,
                                    "La contraseña es menor a 8 caracteres",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (passwordChecker.value != password.value) {
                                Toast.makeText(
                                    context,
                                    "Las contraseñas no coinciden",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF997052)
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text("Actualizar datos", color = Color.White)
                    }

                }
            }
        }
    }
}
