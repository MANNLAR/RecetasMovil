package com.plateup.app.domain.usecase.auth

import com.plateup.app.data.repository.AuthRepository
import com.plateup.app.domain.model.User

class LoginUser(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<User> {
        return authRepository.login(username, password)
    }
}
