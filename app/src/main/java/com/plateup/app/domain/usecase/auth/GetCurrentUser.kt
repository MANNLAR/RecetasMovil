package com.plateup.app.domain.usecase.auth

import com.plateup.app.data.repository.AuthRepository
import com.plateup.app.domain.model.User

class GetCurrentUser(private val authRepository: AuthRepository) {
    operator fun invoke(): User? = authRepository.usuarioActual()
}
