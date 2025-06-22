package com.example.manoslocales.data.mapper

import com.example.manoslocales.data.local.entities.Product
import com.example.manoslocales.data.local.entities.Seller
import com.example.manoslocales.data.local.entities.room.ProductEntity


// De ProductEntity + Seller a Product (dominio)
fun ProductEntity.toDomain(owner: Seller): Product {
    return Product(
        id = id,
        name = name,
        price = price,
        category = category,
        description = description,
        owner = owner,
        image = image
    )
}

// De Product (dominio) a ProductEntity
fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        price = price,
        category = category,
        description = description,
        ownerId = owner.user.id,
        image = image
    )
}