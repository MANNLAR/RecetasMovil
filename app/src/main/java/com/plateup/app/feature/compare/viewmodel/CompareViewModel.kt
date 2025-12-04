package com.plateup.app.feature.compare.viewmodel

import androidx.lifecycle.ViewModel
import com.plateup.app.domain.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CompareViewModel : ViewModel() {
    private val _resumen = MutableStateFlow("Selecciona dos recetas para comparar")
    val resumen: StateFlow<String> = _resumen.asStateFlow()

    fun comparar(a: Recipe?, b: Recipe?) {
        _resumen.value = if (a != null && b != null) {
            "${'$'}{a.titulo} vs ${'$'}{b.titulo}: compara calorías, proteína, grasas y carbohidratos"
        } else {
            "Selecciona ambas recetas"
        }
    }
}
