package com.example.manoslocales.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.manoslocales.ui.theme.RosaClaro
import com.example.manoslocales.ui.theme.RosaClaroSemi
import com.example.manoslocales.ui.theme.RosaClaroSemi2

@Composable
fun SettingsScreen(onNavigate: (String) -> Unit) {
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
                    onCheckedChange = { isChecked: Boolean ->
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