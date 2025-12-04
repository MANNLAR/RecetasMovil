package com.plateup.app.feature.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plateup.app.domain.model.UserProfile
import com.plateup.app.domain.usecase.profile.GuardarPerfil
import com.plateup.app.domain.usecase.profile.ObservarPerfil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class ProfileState(
    val perfil: UserProfile? = null,
    val mensaje: String? = null
)

class ProfileViewModel(
    private val userId: Long,
    private val guardarPerfil: GuardarPerfil,
    private val observarPerfil: ObservarPerfil
) : ViewModel() {
    private val _estado = MutableStateFlow(ProfileState())
    val estado: StateFlow<ProfileState> = _estado

    init {
        viewModelScope.launch {
            observarPerfil(userId).collectLatest { perfil ->
                _estado.value = _estado.value.copy(perfil = perfil)
            }
        }
    }

    fun guardar(perfil: UserProfile) {
        viewModelScope.launch {
            guardarPerfil(perfil)
            _estado.value = _estado.value.copy(mensaje = "Perfil guardado")
        }
    }
}
