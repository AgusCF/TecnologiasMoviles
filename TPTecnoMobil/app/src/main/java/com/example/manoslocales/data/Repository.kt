package com.example.manoslocales.data

import com.example.manoslocales.network.RetrofitClient
import kotlinx.coroutines.flow.Flow

class Repository(
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao
) {
    suspend fun fetchProducts() {
        val products = RetrofitClient.apiService.getProducts().body() ?: emptyList()
        productDao.insertAll(products)
    }

    suspend fun fetchCategories() {
        val categories = RetrofitClient.apiService.getCategories().body() ?: emptyList()
        categoryDao.insertAll(categories)
    }

    suspend fun getProducts(): List<Product> = productDao.getAllProducts()
    suspend fun getCategories(): List<Category> = categoryDao.getAllCategories()
}