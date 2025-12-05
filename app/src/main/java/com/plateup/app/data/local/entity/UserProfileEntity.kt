package com.plateup.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class UserProfileEntity(
    @PrimaryKey val userId: Long,
    val altura: Double?,
    val peso: Double?,
    val edad: Int?,
    val sexo: String?,
    val nivelActividad: String?,
    val alergias: String?,
    val preferenciasDieteticas: String?,
    val notasMedicas: String?
)
