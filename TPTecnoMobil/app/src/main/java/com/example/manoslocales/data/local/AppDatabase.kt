package com.example.manoslocales.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.manoslocales.data.local.dao.ProductDao
import com.example.manoslocales.data.local.dao.SellerDao
import com.example.manoslocales.data.local.dao.UserDao
import com.example.manoslocales.data.local.entities.room.ProductEntity
import com.example.manoslocales.data.local.entities.room.SellerEntity
import com.example.manoslocales.data.local.entities.room.UserEntity

@Database(
    entities = [UserEntity::class, ProductEntity::class, SellerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun sellerDao(): SellerDao
}