package com.plateup.app.domain.usecase.auth

import com.plateup.app.data.repository.AuthRepository

class LogoutUser(private val authRepository: AuthRepository) {
    operator fun invoke() {
        authRepository.logout()
    }
}
