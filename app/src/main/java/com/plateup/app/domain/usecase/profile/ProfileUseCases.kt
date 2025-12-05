package com.plateup.app.domain.usecase.profile

import com.plateup.app.data.repository.AuthRepository
import com.plateup.app.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

class ObserveProfile(private val repo: AuthRepository) {
    operator fun invoke(userId: Long): Flow<UserProfile?> = repo.observeProfile(userId)
}

class UpdateProfile(private val repo: AuthRepository) {
    operator fun invoke(profile: UserProfile) = repo.updateProfile(profile)
}
