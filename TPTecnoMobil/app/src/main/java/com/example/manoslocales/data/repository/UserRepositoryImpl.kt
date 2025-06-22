package com.example.manoslocales.data.repository

import android.content.SharedPreferences
import com.example.manoslocales.data.local.dao.UserDao
import com.example.manoslocales.data.local.entities.User
import com.example.manoslocales.data.mapper.toDomain
import com.example.manoslocales.data.mapper.toEntity
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val prefs: SharedPreferences
) : UserRepository {

    private val AUTH_TOKEN_KEY = "AUTH_TOKEN"
    private val IS_LOGGED_IN_KEY = "IS_LOGGED_IN"

    override suspend fun getUserByUsername(username: String): User? {
        val entity = userDao.getUserByUsername(username) ?: return null
        return entity.toDomain()
    }

    override suspend fun getUserById(id: Int): User? {
        val entity = userDao.getUserById(id) ?: return null
        return entity.toDomain()
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUserById(user.id)
    }

    override fun getAuthToken(): String? {
        return prefs.getString(AUTH_TOKEN_KEY, null)
    }

    override fun saveAuthToken(token: String) {
        prefs.edit()
            .putString(AUTH_TOKEN_KEY, token)
            .putBoolean(IS_LOGGED_IN_KEY, true)
            .apply()
    }

    override fun clearSession() {
        prefs.edit()
            .remove(AUTH_TOKEN_KEY)
            .putBoolean(IS_LOGGED_IN_KEY, false)
            .apply()
    }

    override suspend fun getUserByEmail(email: String): User? {
        val entity = userDao.getUserByEmail(email) ?: return null
        return entity.toDomain()
    }

    override suspend fun saveUserWithHashedPassword(user: User): User {
        val hashedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
        val userToSave = user.copy(password = hashedPassword)
        userDao.insertUser(userToSave.toEntity())
        return user
    }

    override suspend fun validateUserCredentials(username: String, plainPassword: String): Boolean {
        val userEntity = userDao.getUserByUsername(username) ?: return false
        return BCrypt.checkpw(plainPassword, userEntity.password)
    }
}