package com.example.manoslocales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.manoslocales.data.AppDatabase
import com.example.manoslocales.data.Repository
import com.example.manoslocales.ui.screens.FeedScreen
import com.example.manoslocales.ui.theme.ManosLocalesTheme
import com.example.manoslocales.viewmodel.HomeViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ManosLocalesTheme {
                val repository = remember {
                    Repository(
                        productDao = AppDatabase.getDatabase(this).productDao(),
                        categoryDao = AppDatabase.getDatabase(this).categoryDao()
                    )
                }
                val viewModel = viewModel { HomeViewModel(repository) }
                FeedScreen(viewModel = viewModel)
            }
        }
    }
}