package com.plateup.app.domain.model

import kotlin.math.pow

data class UserProfile(
    val userId: Long,
    val altura: Float?,
    val peso: Float?,
    val edad: Int?,
    val sexo: String?,
    val nivelActividad: String?,
    val alergias: List<String>,
    val preferenciasDieteticas: List<String>,
    val notasMedicas: String?
) {
    val imc: Float?
        get() = if (altura != null && peso != null && altura > 0f) {
            peso / (altura / 100f).pow(2)
        } else null

    val categoriaImc: String
        get() {
            val valor = imc ?: return "Sin datos"
            return when {
                valor < 18.5 -> "Bajo peso"
                valor < 25f -> "Normal"
                valor < 30f -> "Sobrepeso"
                else -> "Obesidad"
            }
        }
}
