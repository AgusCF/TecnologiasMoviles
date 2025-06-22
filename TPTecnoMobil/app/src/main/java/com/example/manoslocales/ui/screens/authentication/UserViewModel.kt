package com.example.manoslocales.ui.screens.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manoslocales.data.local.entities.User
import com.example.manoslocales.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    private val _loginError = MutableStateFlow<String?>(null)
    val loginError: StateFlow<String?> = _loginError


    private val _registeredUser = MutableStateFlow<User?>(null)
    val registeredUser: StateFlow<User?> = _registeredUser

    private val _emailAlreadyUsed = MutableStateFlow(false)
    val emailAlreadyUsed: StateFlow<Boolean> = _emailAlreadyUsed

    private val _loginErrorFlag = MutableStateFlow(false)
    val loginErrorFlag: StateFlow<Boolean> = _loginErrorFlag


    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val isValid = userRepository.validateUserCredentials(username, password)
                if (isValid) {
                    _isAuthenticated.value = true
                    val user = userRepository.getUserByUsername(username)
                    _currentUser.value = user
                } else {
                    _isAuthenticated.value = false
                    _loginError.value = "Nombre de usuario o contraseña incorrectos"
                    _currentUser.value = null
                }
            } catch (e: Exception) {
                _isAuthenticated.value = false
                _loginError.value = "Error al intentar iniciar sesión: ${e.localizedMessage}"
            }
        }
    }



    fun register(user: User) {
        viewModelScope.launch {
            val existingUser = userRepository.getUserByEmail(user.email)
            if (existingUser != null) {
                _emailAlreadyUsed.value = true
                _registeredUser.value = null
            } else {
                try {
                    val newUser = userRepository.saveUserWithHashedPassword(user)
                    _registeredUser.value = newUser
                    _emailAlreadyUsed.value = false
                } catch (e: Exception) {
                    // Manejo de errores si lo necesitas
                }
            }
        }
    }


    fun logout() {
        userRepository.clearSession()
        _isAuthenticated.value = false
        _currentUser.value = null
    }

    fun saveSession(token: String) {
        userRepository.saveAuthToken(token)
    }

    fun loadSession() {
        val token = userRepository.getAuthToken()
        _isAuthenticated.value = token != null
    }
    fun resetEmailAlreadyUsedFlag() {
        _emailAlreadyUsed.value = false
    }

    fun clearLoginError() {
        _loginError.value = null
    }
}