package com.plateup.app.domain.usecase.auth

import com.plateup.app.data.repository.AuthRepository
import com.plateup.app.domain.model.User
import kotlinx.coroutines.flow.StateFlow

class RegisterUser(private val repo: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<User> = repo.register(username, password)
}

class LoginUser(private val repo: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<User> = repo.login(username, password)
}

class LogoutUser(private val repo: AuthRepository) {
    operator fun invoke() = repo.logout()
}

class GetCurrentUser(private val repo: AuthRepository) {
    operator fun invoke(): StateFlow<User?> = repo.currentUser
}
