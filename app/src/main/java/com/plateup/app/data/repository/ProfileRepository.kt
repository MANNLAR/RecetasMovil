package com.plateup.app.data.repository

import com.plateup.app.data.local.dao.ProfileDao
import com.plateup.app.data.mapper.toDomain
import com.plateup.app.data.mapper.toEntity
import com.plateup.app.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProfileRepository(private val profileDao: ProfileDao) {
    suspend fun guardarPerfil(perfil: UserProfile) {
        profileDao.guardarPerfil(perfil.toEntity())
    }

    fun observarPerfil(userId: Long): Flow<UserProfile?> = profileDao.observarPerfil(userId).map { it?.toDomain() }
}
