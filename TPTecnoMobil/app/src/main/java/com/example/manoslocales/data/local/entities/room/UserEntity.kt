package com.example.manoslocales.data.local.entities.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val surname: String,
    val dateOfBirth: String,
    val username: String,
    val email: String,
    val password: String,
    val city: String,
    val image: Int
)