package com.example.manoslocales.ui.screens.authentication.rememberpw

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manoslocales.utils.textFieldColors

@Composable
fun VerifyCode(onVerified: () -> Unit) {
    val context = LocalContext.current
    val code = remember { mutableStateOf("") }

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

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text("Solo un paso mas...",
                    color = Color(0xFF404934),
                    fontSize = 40.sp)
                Text("Introduce el código de verificación enviado a su email",
                    color = Color(0xFF404934),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 10.dp))
            }
            TextField(
                value = code.value,
                onValueChange = {
                    if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                        code.value = it
                        if(it.length == 6) {
                            if (it == "123456") {
                                onVerified()
                            } else {
                                Toast.makeText(context, "Codigo incorrecto", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                },
                label = { Text("Código de verificación") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors()
            )

        }
    }

}