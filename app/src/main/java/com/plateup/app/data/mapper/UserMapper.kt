package com.plateup.app.data.mapper

import com.plateup.app.data.local.entity.UserEntity
import com.plateup.app.data.local.entity.UserProfileEntity
import com.plateup.app.domain.model.User
import com.plateup.app.domain.model.UserProfile

fun UserEntity.toDomain() = User(
    id = id,
    username = username,
    email = email,
    createdAt = createdAt
)

fun UserProfileEntity.toDomain(): UserProfile = UserProfile(
    userId = userId,
    altura = altura,
    peso = peso,
    edad = edad,
    sexo = sexo,
    nivelActividad = nivelActividad,
    alergias = alergias?.split(",")?.filter { it.isNotBlank() } ?: emptyList(),
    preferenciasDieteticas = preferenciasDieteticas?.split(",")?.filter { it.isNotBlank() } ?: emptyList(),
    notasMedicas = notasMedicas
)

fun UserProfile.toEntity(): UserProfileEntity = UserProfileEntity(
    userId = userId,
    altura = altura,
    peso = peso,
    edad = edad,
    sexo = sexo,
    nivelActividad = nivelActividad,
    alergias = alergias.joinToString(separator = ","),
    preferenciasDieteticas = preferenciasDieteticas.joinToString(separator = ","),
    notasMedicas = notasMedicas
)
