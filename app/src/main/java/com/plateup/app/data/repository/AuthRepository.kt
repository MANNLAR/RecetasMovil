package com.plateup.app.data.repository

import com.plateup.app.core.util.HashUtil
import com.plateup.app.data.local.dao.AuthDao
import com.plateup.app.data.mapper.toDomain
import com.plateup.app.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

class AuthRepository(private val authDao: AuthDao) {
    private var usuarioActual: User? = null

    suspend fun registrar(username: String, password: String, email: String? = null): Result<User> {
        val existente = authDao.obtenerPorUsuario(username)
        if (existente != null) {
            return Result.failure(IllegalArgumentException("El usuario ya existe"))
        }
        val nuevo = com.plateup.app.data.local.entity.UserEntity(
            username = username,
            email = email,
            hashedPassword = HashUtil.sha256(password),
            createdAt = Date().time
        )
        val id = authDao.registrar(nuevo)
        val usuario = nuevo.copy(id = id).toDomain()
        usuarioActual = usuario
        return Result.success(usuario)
    }

    suspend fun login(username: String, password: String): Result<User> {
        val user = authDao.obtenerPorUsuario(username) ?: return Result.failure(IllegalArgumentException("Usuario no encontrado"))
        if (user.hashedPassword != HashUtil.sha256(password)) {
            return Result.failure(IllegalArgumentException("Contraseña incorrecta"))
        }
        val domain = user.toDomain()
        usuarioActual = domain
        return Result.success(domain)
    }

    fun logout() {
        usuarioActual = null
    }

    fun observarUsuarios(): Flow<List<User>> = authDao.observarUsuarios().map { list -> list.map { it.toDomain() } }

    fun usuarioActual(): User? = usuarioActual
}
