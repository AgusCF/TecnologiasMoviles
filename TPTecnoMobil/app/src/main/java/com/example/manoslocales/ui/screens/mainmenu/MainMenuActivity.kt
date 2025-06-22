package com.example.manoslocales.ui.screens.mainmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.manoslocales.ui.screens.details.ProductDetailScreen
import com.example.manoslocales.ui.screens.details.SellerDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MenuNavHost()
        }
    }
}

@Composable
fun MenuNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination =   "mainMenu") {
        composable("MainMenu") {
            FeedScreen(
                onNavigate = { destination ->
                    navController.navigate(destination)
                }
            )
        }

        composable("Settings") {
            SettingsScreen(
                onNavigateToMainMenu = {
                    navController.navigate("MainMenu")
                },
                onNavigateToFavorites = {
                    navController.navigate("Favorites")
                },
                onNavigateToModifyAccount = {
                    navController.navigate("ModifyAccount")
                }
            )
        }
        composable ("ModifyAccount"){
            ModifyAccountScreen(
                onNavigateToFavorites = {
                    navController.navigate("Favorites")
                } ,
                onNavigateToSettings = {
                    navController.navigate("Settings")
                },
                onNavigateToMainMenu = {
                    navController.navigate("MainMenu")
                }
            )
        }
        composable ("Favorites"){
            FavoritesScreen(
                onNavigateToProduct = { productId -> navController.navigate("ProductView/$productId")},
                onNavigateToSettings = {
                    navController.navigate("Settings")
                },
                onNavigateToMainMenu = {
                    navController.navigate("MainMenu")
                }
            )
        }
        composable("ProductView/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            ProductDetailScreen(productId = productId,
                onNavigateToSeller = { sellerId -> navController.navigate("SellerView/$sellerId")},
                onNavigateToProduct = { productId -> navController.navigate("ProductView/$productId")},
                onNavigateToMainMenu = {
                    navController.navigate("MainMenu")
                },
                onNavigateToFavorites = {
                    navController.navigate("Favorites")
                },
                onNavigateToSettings = {
                    navController.navigate("Settings")
                }

            )
        }

        composable("SellerView/{sellerId}") { backStackEntry ->
            val sellerId = backStackEntry.arguments?.getString("sellerId")
            SellerDetailScreen(sellerId = sellerId,
                onNavigateToProduct = { productId -> navController.navigate("ProductView/$productId")},
                onNavigateToMainMenu = {
                    navController.navigate("MainMenu")
                },
                onNavigateToFavorites = {
                    navController.navigate("Favorites")
                },
                onNavigateToSettings = {
                    navController.navigate("Settings")
                })
        }


    }
}