package com.example.manoslocales.data.repository

import com.example.manoslocales.data.local.entities.User

interface UserRepository {
    suspend fun getUserByUsername(username: String): User?
    suspend fun getUserById(id: Int): User?
    suspend fun deleteUser(user: User)
    suspend fun saveUserWithHashedPassword(user: User): User
    suspend fun validateUserCredentials(username: String, plainPassword: String): Boolean
    fun saveAuthToken(token: String)
    fun getAuthToken(): String?
    fun clearSession()
    suspend fun getUserByEmail(email: String): User?
}