package com.example.manoslocales.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manoslocales.data.Category
import com.example.manoslocales.data.Product
import com.example.manoslocales.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val _products = MutableStateFlow(emptyList<Product>())
    val products: StateFlow<List<Product>> = _products

    private val _categories = MutableStateFlow(emptyList<Category>())
    val categories: StateFlow<List<Category>> = _categories

    init {
        viewModelScope.launch {
            repository.fetchProducts()
            repository.fetchCategories()
            repository.getProducts().collect { _products.value = it }
            repository.getCategories().collect { _categories.value = it }
        }
    }
}