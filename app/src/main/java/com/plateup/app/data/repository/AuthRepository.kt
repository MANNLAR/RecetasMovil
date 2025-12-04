package com.plateup.app.data.repository

import com.plateup.app.core.util.PasswordHasher
import com.plateup.app.data.local.dao.AuthDao
import com.plateup.app.data.mapper.toDomain
import com.plateup.app.data.mapper.toEntity
import com.plateup.app.domain.model.User
import com.plateup.app.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthRepository(private val authDao: AuthDao) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun observeUsers(): Flow<List<User>> = authDao.observeUsers().map { list -> list.map { it.toDomain() } }

    suspend fun register(username: String, password: String, email: String? = null): Result<User> {
        val existing = authDao.findByUsername(username)
        if (existing != null) return Result.failure(IllegalStateException("Usuario ya existe"))
        val id = authDao.insertUser(
            com.plateup.app.data.local.entity.UserEntity(
                username = username,
                email = email,
                hashedPassword = PasswordHasher.hash(password)
            )
        )
        val user = authDao.getUserById(id)?.toDomain()
        _currentUser.value = user
        return user?.let { Result.success(it) } ?: Result.failure(IllegalStateException("No se pudo crear usuario"))
    }

    suspend fun login(username: String, password: String): Result<User> {
        val user = authDao.findByUsername(username)
            ?: return Result.failure(IllegalArgumentException("Usuario no encontrado"))
        val hashed = PasswordHasher.hash(password)
        return if (hashed == user.hashedPassword) {
            val domain = user.toDomain()
            _currentUser.value = domain
            Result.success(domain)
        } else {
            Result.failure(IllegalArgumentException("Contraseña incorrecta"))
        }
    }

    fun logout() {
        _currentUser.value = null
    }

    fun observeProfile(userId: Long): Flow<UserProfile?> = authDao.observeProfile(userId).map { it?.toDomain() }

    fun updateProfile(profile: UserProfile) {
        scope.launch { authDao.upsertProfile(profile.toEntity()) }
    }
}
