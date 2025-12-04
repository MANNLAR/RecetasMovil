package com.plateup.app.domain.model

data class UserProfile(
    val userId: Long,
    val altura: Double?,
    val peso: Double?,
    val edad: Int?,
    val sexo: String?,
    val nivelActividad: String?,
    val alergias: String?,
    val preferenciasDieteticas: String?,
    val notasMedicas: String?
)
