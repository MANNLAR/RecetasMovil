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

fun UserProfileEntity.toDomain() = UserProfile(
    userId = userId,
    altura = altura,
    peso = peso,
    edad = edad,
    sexo = sexo,
    nivelActividad = nivelActividad,
    alergias = alergias,
    preferenciasDieteticas = preferenciasDieteticas,
    notasMedicas = notasMedicas
)

fun UserProfile.toEntity() = UserProfileEntity(
    userId = userId,
    altura = altura,
    peso = peso,
    edad = edad,
    sexo = sexo,
    nivelActividad = nivelActividad,
    alergias = alergias,
    preferenciasDieteticas = preferenciasDieteticas,
    notasMedicas = notasMedicas
)
