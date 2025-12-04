package com.plateup.app.domain.usecase.auth

import com.plateup.app.data.repository.AuthRepository
import com.plateup.app.domain.model.User

class RegisterUser(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String, email: String? = null): Result<User> {
        return authRepository.registrar(username, password, email)
    }
}
