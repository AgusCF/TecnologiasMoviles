package com.example.manoslocales.data.repository.helpers

import com.example.manoslocales.data.local.entities.Seller

interface SellerProvider {
    suspend fun getSellerByUserId(userId: Int): Seller?
}