package com.plateup.app.feature.planner.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlannerViewModel : ViewModel() {
    private val _plan = MutableStateFlow("Plan semanal listo para personalizar")
    val plan: StateFlow<String> = _plan.asStateFlow()

    fun generar(dias: Int, comidas: Int, preferencia: String) {
        _plan.value = "${'$'}dias días, ${'$'}comidas comidas/día con enfoque ${'$'}preferencia"
    }
}
