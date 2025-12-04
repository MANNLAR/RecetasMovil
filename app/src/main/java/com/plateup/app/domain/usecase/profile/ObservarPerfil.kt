package com.plateup.app.domain.usecase.profile

import com.plateup.app.data.repository.ProfileRepository
import com.plateup.app.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

class ObservarPerfil(private val repository: ProfileRepository) {
    operator fun invoke(userId: Long): Flow<UserProfile?> = repository.observarPerfil(userId)
}
