package com.example.manoslocales.data.local.entities

data class User(
    val id: Int,
    val name: String,
    val surname: String,
    val dateOfBirth: String,
    val username: String,
    val email: String,
    val password: String,
    val city: String,
    var favoriteProducts: MutableList<Product>?,
    val image: Int
)