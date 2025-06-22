package com.example.manoslocales.ui.screens.mainmenu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.manoslocales.R
import com.example.manoslocales.ui.components.ProductCard
import com.example.manoslocales.ui.components.SimpleProduct
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings

@Composable
fun FeedScreen(onNavigate: (String) -> Unit) {
    val products = listOf(
        SimpleProduct("Producto 1", "Descripción del producto 1", R.drawable.ic_launcher_foreground),
        SimpleProduct("Producto 2", "Descripción del producto 2", R.drawable.ic_launcher_foreground),
        SimpleProduct("Producto 3", "Descripción del producto 3", R.drawable.ic_launcher_foreground),
        SimpleProduct("Producto 4", "Descripción del producto 4", R.drawable.ic_launcher_foreground),
        SimpleProduct("Producto 5", "Descripción del producto 5", R.drawable.ic_launcher_foreground),
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