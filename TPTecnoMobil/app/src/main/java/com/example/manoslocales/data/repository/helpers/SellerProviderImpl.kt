package com.example.manoslocales.data.repository.helpers

import com.example.manoslocales.data.repository.SellerRepository

class SellerProviderImpl(
    private val sellerRepository: SellerRepository
) : SellerProvider {
    override suspend fun getSellerByUserId(userId: Int) = sellerRepository.getSellerByUserId(userId)
}