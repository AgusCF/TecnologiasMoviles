package com.example.manoslocales.data.local.entities.room

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "sellers")
data class SellerEntity(
    @PrimaryKey val userId: Int,
    val rating: Double,
    val businessName: String
)