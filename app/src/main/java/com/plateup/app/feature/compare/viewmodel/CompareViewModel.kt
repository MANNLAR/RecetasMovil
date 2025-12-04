package com.plateup.app.feature.compare.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class NutritionalComparison(
    val recetaA: String = "",
    val recetaB: String = "",
    val resumen: String = "Selecciona dos recetas para comparar"
)

class CompareViewModel : ViewModel() {
    private val _estado = MutableStateFlow(NutritionalComparison())
    val estado: StateFlow<NutritionalComparison> = _estado

    fun actualizar(recetaA: String, recetaB: String) {
        _estado.value = NutritionalComparison(
            recetaA = recetaA,
            recetaB = recetaB,
            resumen = "Comparativa rápida entre $recetaA y $recetaB"
        )
    }
}
