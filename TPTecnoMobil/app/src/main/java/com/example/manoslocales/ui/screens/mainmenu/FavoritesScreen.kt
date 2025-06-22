package com.example.manoslocales.ui.screens.mainmenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import com.example.manoslocales.R
import com.example.manoslocales.ui.components.ProductItem
import com.example.manoslocales.utils.ObjectsProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(onNavigateToProduct: (String) -> Unit,
                    onNavigateToSettings: () -> Unit,
                    onNavigateToMainMenu: () -> Unit) {
    var selectedCategory by remember { mutableStateOf("Todos") }
    var expandedCategory by remember { mutableStateOf(false) }
    val categories = remember {  listOf("Todos") + ObjectsProvider.categories }

    val user = remember {
        ObjectsProvider.sellerLists.find { it.user.id == 1 }
    }
    val favoriteList = remember { user?.user?.favoriteProducts }

    val filteredProducts = if (selectedCategory == "Todos") {
        favoriteList
    } else {
        favoriteList?.filter { it.category == selectedCategory }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding( end = 10.dp),
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
                        IconButton(onClick = { /* Acción 1 */ }) {
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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFe3d6c3))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF7C5C44)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Favoritos",
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                    color = Color(0xFFe3d6c3),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Box {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "Categoría: $selectedCategory",
                            color = Color(0xFFe3d6c3),
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable { expandedCategory = !expandedCategory }
                        )
                        Row(horizontalArrangement = Arrangement.End,
                            modifier = Modifier.padding(top = 56.dp)){
                            DropdownMenu(
                                expanded = expandedCategory,
                                onDismissRequest = { expandedCategory = false },
                                modifier = Modifier.padding(10.dp)
                            ) {
                                categories.forEach { category ->
                                    DropdownMenuItem(
                                        text = { Text(category) },
                                        onClick = {
                                            selectedCategory = category
                                            expandedCategory = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                }
            }

            Row(
                modifier = Modifier
                    .background(Color(0xFFe3d6c3))
                    .fillMaxHeight()
            ) {
                LazyColumn {
                    items(filteredProducts?.chunked(2) ?: emptyList()) { rowItems ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            rowItems.forEach { product ->
                                Box(modifier = Modifier.weight(1f)
                                    .clickable{
                                        onNavigateToProduct(product.id.toString())
                                    }) {
                                    ProductItem(product = product)
                                }
                            }
                            if (rowItems.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }

                }
            }
        }
    }
}


