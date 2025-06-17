package com.example.manoslocales.network

import androidx.tracing.perfetto.handshake.protocol.Response
import retrofit2.Response
import retrofit2.http.GET
import com.example.manoslocales.data.Product
import com.example.manoslocales.data.Category

interface ApiService {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>

    @GET("categories")
    suspend fun getCategories(): Response<List<Category>>
}