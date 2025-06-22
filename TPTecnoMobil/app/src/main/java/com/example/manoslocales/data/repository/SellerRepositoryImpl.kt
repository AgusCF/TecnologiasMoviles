package com.example.manoslocales.data.repository

import com.example.manoslocales.data.local.dao.SellerDao
import com.example.manoslocales.data.local.dao.UserDao
import com.example.manoslocales.data.mapper.toDomain
import com.example.manoslocales.data.mapper.toEntity
import com.example.manoslocales.data.local.entities.Seller
import com.example.manoslocales.data.local.entities.User


class SellerRepositoryImpl(
    private val sellerDao: SellerDao,
    private val userDao: UserDao
) : SellerRepository {

    override suspend fun getSellerByUserId(userId: Int): Seller? {
        val sellerEntity = sellerDao.getSellerByUserId(userId) ?: return null
        val userEntity = userDao.getUserById(userId) ?: return null
        return sellerEntity.toDomain(userEntity.toDomain())
    }

    override suspend fun insertSeller(seller: Seller) {
        sellerDao.insertSeller(seller.toEntity())
    }

    override suspend fun deleteSellerByUserId(userId: Int) {
        sellerDao.deleteSellerByUserId(userId)
    }

    override suspend fun deleteAllSellers() {
        sellerDao.deleteAllSellers()
    }

    override suspend fun registerSeller(user: User){

    }
}