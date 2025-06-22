package com.example.manoslocales.data.local.entities

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val category: String,
    val description: String,
    val owner: Seller,
    val image: Int
)