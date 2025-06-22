package com.example.manoslocales.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.manoslocales.data.local.entities.room.SellerEntity

@Dao
interface SellerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeller(seller: SellerEntity)

    @Query("SELECT * FROM sellers WHERE userId = :userId LIMIT 1")
    suspend fun getSellerByUserId(userId: Int): SellerEntity?

    @Query("DELETE FROM sellers WHERE userId = :userId")
    suspend fun deleteSellerByUserId(userId: Int)

    @Query("DELETE FROM sellers")
    suspend fun deleteAllSellers()
}