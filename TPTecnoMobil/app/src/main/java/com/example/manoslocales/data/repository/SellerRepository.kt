package com.example.manoslocales.data.repository

import com.example.manoslocales.data.local.entities.Seller
import com.example.manoslocales.data.local.entities.User


interface SellerRepository {
    suspend fun getSellerByUserId(userId: Int): Seller?
    suspend fun insertSeller(seller: Seller)
    suspend fun deleteSellerByUserId(userId: Int)
    suspend fun deleteAllSellers()
    suspend fun registerSeller(user: User)
}