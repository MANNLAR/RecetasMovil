package com.plateup.app.core.util

fun calcularImc(peso: Double?, altura: Double?): Pair<Double?, String> {
    if (peso == null || altura == null || altura == 0.0) return Pair(null, "Sin datos")
    val imc = peso / (altura * altura)
    val categoria = when {
        imc < 18.5 -> "Bajo peso"
        imc < 25 -> "Normal"
        imc < 30 -> "Sobrepeso"
        else -> "Obesidad"
    }
    return Pair(imc, categoria)
}
