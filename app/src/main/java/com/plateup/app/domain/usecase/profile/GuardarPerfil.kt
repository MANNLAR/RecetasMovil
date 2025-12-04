package com.plateup.app.domain.usecase.profile

import com.plateup.app.data.repository.ProfileRepository
import com.plateup.app.domain.model.UserProfile

class GuardarPerfil(private val repository: ProfileRepository) {
    suspend operator fun invoke(perfil: UserProfile) = repository.guardarPerfil(perfil)
}
