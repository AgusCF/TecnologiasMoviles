package com.example.manoslocales.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.manoslocales.R

@Composable
fun BottomNavigationBar(onNavigate: (String) -> Unit) {
    val items = listOf(
        BottomNavItem("Home", painterResource(id = R.drawable.ic_home), "home"),
        BottomNavItem("Search", painterResource(id = R.drawable.ic_search), "search"),
        BottomNavItem("Favorites", painterResource(id = R.drawable.ic_favorite), "favorites"),
        BottomNavItem("Profile", painterResource(id = R.drawable.ic_profile), "profile")
    )

    BottomNavigation {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painter = item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = false,
                onClick = { onNavigate(item.screenRoute) }
            )
        }
    }
}

data class BottomNavItem(
    val title: String,
    val icon: Painter,
    val screenRoute: String
)