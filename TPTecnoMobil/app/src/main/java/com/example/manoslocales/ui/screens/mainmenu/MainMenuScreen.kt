package com.example.manoslocales.ui.screens.mainmenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import com.example.manoslocales.R
import com.example.manoslocales.ui.components.ProductItem
import com.example.manoslocales.ui.components.SellerItem
import com.example.manoslocales.data.local.entities.Product
import com.example.manoslocales.data.local.entities.Seller
import com.example.manoslocales.utils.ObjectsProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Posts(
    onNavigateToSeller: (String) -> Unit,
    onNavigateToProduct: (String) -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val productsList = remember { ObjectsProvider.productsList }
    val sellersList = remember { ObjectsProvider.sellerLists }
    var selectedView by remember { mutableStateOf("Productos") }
    var selectedCategory by remember { mutableStateOf("Todos") }
    var selectedCity by remember { mutableStateOf("Todos") }
    var expandedView by remember { mutableStateOf(false) }
    var expandedCategory by remember { mutableStateOf(false) }
    var expandedCity by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf(TextFieldValue("")) }


    val categories = remember { listOf("Todos") + ObjectsProvider.categories }
    val cities = remember { listOf("Todos") + ObjectsProvider.cities }
    val view = remember { listOf("Productos", "Vendedores") }
    val filteredProducts = productsList.filter {
        (selectedCategory == "Todos" || it.category == selectedCategory) &&
                (selectedCity == "Todos" || it.owner.user.city == selectedCity) &&
                it.name.contains(searchText.text, ignoreCase = true)
    }

    val filteredSellers = sellersList.filter {
        (selectedCity == "Todos" || it.user.city == selectedCity) &&
                it.user.name.contains(searchText.text, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(end = 10.dp)
                            .height(56.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = searchText,
                            onValueChange = { searchText = it },
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .align(Alignment.CenterVertically),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color(0xFFe3d6c3),
                                unfocusedContainerColor = Color(0xFFe3d6c3),
                                focusedTextColor = Color(0xFF7C5C44),
                                unfocusedTextColor = Color(0xFF7C5C44),
                                focusedLabelColor = Color(0xFF7C5C44),
                                unfocusedLabelColor = Color(0xFF7C5C44),
                                cursorColor = Color(0xFF7C5C44),
                                focusedIndicatorColor = Color(0xFF7C5C44),
                                unfocusedIndicatorColor = Color(0xFF7C5C44)
                            ),


                            placeholder = {
                                Text(
                                    "Buscar productos o vendedores",
                                    fontWeight = FontWeight.Light,
                                    color = Color(0xFF7C5C44),

                                    )},
                            singleLine = true
                        )
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logotipo",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF404934))
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFe3d6c3),
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        IconButton(onClick = { onNavigateToFavorites() }) {
                            Icon(painter = painterResource(id = R.drawable.favorite), contentDescription = "Favoritos", modifier = Modifier.size(29.dp))
                        }
                        IconButton(onClick = { /* Acción 2 */ }) {
                            Icon(painter = painterResource(id = R.drawable.home), contentDescription = "Inicio", modifier = Modifier.size(25.dp))
                        }
                        IconButton(onClick = { onNavigateToSettings() }) {
                            Icon(painter = painterResource(id = R.drawable.submenu), contentDescription = "Settings", modifier = Modifier.size(29.dp))
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {

            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(Color(0xFF7C5C44)).padding(top = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column (modifier = Modifier.padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Ver: $selectedView", color = Color.White, modifier = Modifier
                        .clickable { expandedView = true })
                    DropdownMenu(expanded = expandedView, onDismissRequest = { expandedView = false }) {
                        view.forEach {
                            DropdownMenuItem(
                                text = { Text(it) },
                                onClick = {
                                    selectedView = it
                                    expandedView = false
                                })
                        }
                    }
                }
                Column (modifier = Modifier.padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text("Ciudad: $selectedCity", color = Color.White, modifier = Modifier
                        .clickable { expandedCity = true })
                    DropdownMenu(expanded = expandedCity, onDismissRequest = { expandedCity = false }) {
                        cities.forEach {
                            DropdownMenuItem(
                                text = { Text(it) },
                                onClick = {
                                    selectedCity = it
                                    expandedCity = false
                                })
                        }
                    }
                }
                Column (modifier = Modifier.padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    if (selectedView == "Productos") {
                        Text("Categoría: $selectedCategory", color = Color.White, modifier = Modifier
                            .clickable { expandedCategory = true })
                    }
                    DropdownMenu(expanded = expandedCategory, onDismissRequest = { expandedCategory = false }) {
                        categories.forEach {
                            DropdownMenuItem(
                                text = { Text(it) },
                                onClick = {
                                    selectedCategory = it
                                    expandedCategory = false
                                })
                        }
                    }
                }
            }

            LazyColumn(modifier = Modifier.background(Color(0xFFe3d6c3)).fillMaxSize()) {
                val itemsToShow = if (selectedView == "Productos") filteredProducts else filteredSellers
                items(itemsToShow.chunked(2)) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        rowItems.forEach { item ->
                            Box(modifier = Modifier.weight(1f).clickable {
                                if (selectedView == "Productos") onNavigateToProduct((item as Product).id.toString())
                                else onNavigateToSeller((item as Seller).user.id.toString())
                            }) {
                                if (selectedView == "Productos") ProductItem(product = item as Product)
                                else SellerItem(seller = item as Seller)
                            }
                        }
                        if (rowItems.size == 1) Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}