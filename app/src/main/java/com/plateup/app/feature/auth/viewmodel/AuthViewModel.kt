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
import kotlinx.coroutines.launch

data class AuthUiState(
    val usuarioActual: User? = null,
    val cargando: Boolean = false,
    val error: String? = null,
    val usuariosRegistrados: List<User> = emptyList()
)

class AuthViewModel(
    private val registerUser: RegisterUser,
    private val loginUser: LoginUser,
    private val logoutUser: LogoutUser,
    private val getCurrentUser: GetCurrentUser
) : ViewModel() {

    private val _estado = MutableStateFlow(AuthUiState(usuarioActual = getCurrentUser()))
    val estado: StateFlow<AuthUiState> = _estado

    fun registrar(username: String, password: String) {
        viewModelScope.launch {
            _estado.value = _estado.value.copy(cargando = true, error = null)
            val resultado = registerUser(username, password)
            _estado.value = if (resultado.isSuccess) {
                _estado.value.copy(usuarioActual = resultado.getOrNull(), cargando = false)
            } else {
                _estado.value.copy(error = resultado.exceptionOrNull()?.message, cargando = false)
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _estado.value = _estado.value.copy(cargando = true, error = null)
            val resultado = loginUser(username, password)
            _estado.value = if (resultado.isSuccess) {
                _estado.value.copy(usuarioActual = resultado.getOrNull(), cargando = false)
            } else {
                _estado.value.copy(error = resultado.exceptionOrNull()?.message, cargando = false)
            }
        }
    }

    fun logout() {
        logoutUser()
        _estado.value = _estado.value.copy(usuarioActual = null)
    }
}
