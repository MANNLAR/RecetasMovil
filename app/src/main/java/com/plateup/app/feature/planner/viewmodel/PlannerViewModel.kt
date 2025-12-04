package com.plateup.app.feature.planner.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlannerViewModel : ViewModel() {
    private val _plan = MutableStateFlow("Configura días y comidas para generar tu plan semanal")
    val plan: StateFlow<String> = _plan

    fun generar(dias: Int, comidas: Int, enfoque: String) {
        _plan.value = "Plan de $dias días con $comidas comidas por día. Enfoque: $enfoque"
    }
}
