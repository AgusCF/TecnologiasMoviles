package com.example.manoslocales.data.mapper

import com.example.manoslocales.data.local.entities.room.SellerEntity
import com.example.manoslocales.data.local.entities.Seller
import com.example.manoslocales.data.local.entities.User


// De SellerEntity + User a Seller (dominio)
fun SellerEntity.toDomain(user: User): Seller {
    return Seller(
        user = user,
        rating = rating,
        businessName = businessName
    )
}

// De Seller (dominio) a SellerEntity
fun Seller.toEntity(): SellerEntity {
    return SellerEntity(
        userId = user.id,
        rating = rating,
        businessName = businessName
    )
}