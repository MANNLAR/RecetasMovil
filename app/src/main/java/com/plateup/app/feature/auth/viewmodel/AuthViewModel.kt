package com.plateup.app.feature.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plateup.app.domain.model.User
import com.plateup.app.domain.usecase.auth.GetCurrentUser
import com.plateup.app.domain.usecase.auth.LoginUser
import com.plateup.app.domain.usecase.auth.LogoutUser
import com.plateup.app.domain.usecase.auth.RegisterUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val registerUser: RegisterUser,
    private val loginUser: LoginUser,
    private val logoutUser: LogoutUser,
    getCurrentUser: GetCurrentUser
) : ViewModel() {

    private val _mensaje = MutableStateFlow<String?>(null)
    val mensaje: StateFlow<String?> = _mensaje.asStateFlow()

    val usuarioActual: StateFlow<User?> = getCurrentUser()

    fun registrar(username: String, password: String, confirmar: String) {
        if (password != confirmar) {
            _mensaje.value = "Las contraseñas no coinciden"
            return
        }
        viewModelScope.launch {
            val result = registerUser(username, password)
            _mensaje.value = result.fold(
                onSuccess = { "Bienvenido ${'$'}{it.username}" },
                onFailure = { it.message ?: "No se pudo registrar" }
            )
        }
    }

    fun iniciarSesion(username: String, password: String) {
        viewModelScope.launch {
            val result = loginUser(username, password)
            _mensaje.value = result.fold(
                onSuccess = { "Hola ${'$'}{it.username}" },
                onFailure = { it.message ?: "Error de inicio" }
            )
        }
    }

    fun cerrarSesion() {
        logoutUser()
        _mensaje.value = "Sesión cerrada"
    }
}
