package com.example.manoslocales.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

object CommonComponents {
    @Composable
    fun OutlinedTextField(
        value: String,
        onValueChange: (String) -> Unit,
        label: String,
        keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        visualTransformation: PasswordVisualTransformation? = null,
        isError: Boolean = false,
        modifier: Modifier = Modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            singleLine = true,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation ?: PasswordVisualTransformation(),
            isError = isError,
            modifier = modifier.fillMaxWidth()
        )
    }
}