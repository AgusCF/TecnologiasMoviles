package com.example.manoslocales.utils

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun textFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    focusedTextColor = Color(0xFF7C5C44),
    unfocusedTextColor = Color(0xFF7C5C44),
    focusedLabelColor = Color(0xFF7C5C44),
    unfocusedLabelColor = Color(0xFF7C5C44),
    cursorColor = Color(0xFF7C5C44),
    focusedIndicatorColor = Color(0xFF7C5C44),
    unfocusedIndicatorColor = Color(0xFF7C5C44)
)