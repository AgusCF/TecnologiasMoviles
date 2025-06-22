package com.example.manoslocales.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.manoslocales.data.local.entities.room.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Query("SELECT * FROM products WHERE id = :id LIMIT 1")
    suspend fun getProductById(id: Int): ProductEntity?

    @Query("SELECT * FROM products WHERE ownerId = :ownerId")
    suspend fun getProductsByOwnerId(ownerId: Int): List<ProductEntity>

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteProductById(id: Int)

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()
}