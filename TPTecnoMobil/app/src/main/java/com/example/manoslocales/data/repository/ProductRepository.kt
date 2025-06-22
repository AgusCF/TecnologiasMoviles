package com.example.manoslocales.data.repository

import com.example.manoslocales.data.local.entities.Product


interface ProductRepository {
    suspend fun getProductById(id: Int): Product?
    suspend fun getProductsByOwnerId(ownerId: Int): List<Product>
    suspend fun insertProduct(product: Product)
    suspend fun deleteProductById(id: Int)
    suspend fun deleteAllProducts()
}