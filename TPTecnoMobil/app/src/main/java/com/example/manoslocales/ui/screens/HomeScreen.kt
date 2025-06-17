package com.example.manoslocales.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.manoslocales.data.Product
import com.example.manoslocales.ui.components.BottomNavigationBar
import com.example.manoslocales.ui.components.ProductCard
import com.example.manoslocales.ui.theme.ManosLocalesTheme
import com.example.manoslocales.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val products = viewModel.productsproducts.collectAsState().value
    val categories = viewModel.categories.collectAsState().value

    Scaffold(
        bottomBar = { BottomNavigationBar(onNavigate = {}) }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            categories.forEach { category ->
                item {
                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                items(products.filter { it.categoryId == category.id }) { product ->
                    ProductCard(product = product)
                }
            }
        }
    }
}