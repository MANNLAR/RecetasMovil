package com.plateup.app.feature.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plateup.app.core.util.calcularImc
import com.plateup.app.domain.model.UserProfile
import com.plateup.app.domain.usecase.profile.ObserveProfile
import com.plateup.app.domain.usecase.profile.UpdateProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val observeProfile: ObserveProfile,
    private val updateProfile: UpdateProfile
) : ViewModel() {

    private val _perfil = MutableStateFlow<UserProfile?>(null)
    val perfil: StateFlow<UserProfile?> = _perfil.asStateFlow()

    private val _imcTexto = MutableStateFlow("Sin datos")
    val imcTexto: StateFlow<String> = _imcTexto.asStateFlow()

    fun cargar(userId: Long) {
        viewModelScope.launch {
            observeProfile(userId).collect { perfil ->
                _perfil.value = perfil
                val (valor, categoria) = calcularImc(perfil?.peso, perfil?.altura)
                _imcTexto.value = valor?.let { "IMC ${'$'}{String.format("%.1f", it)} - ${'$'}categoria" } ?: "Sin datos"
            }
        }
    }

    fun guardarPerfil(perfil: UserProfile) {
        updateProfile(perfil)
    }
}
