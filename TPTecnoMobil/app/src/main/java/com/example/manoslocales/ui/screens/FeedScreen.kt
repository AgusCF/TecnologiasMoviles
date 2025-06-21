package com.example.manoslocales.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.manoslocales.R
import com.example.manoslocales.data.Product
import com.example.manoslocales.ui.components.ProductCard
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings

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