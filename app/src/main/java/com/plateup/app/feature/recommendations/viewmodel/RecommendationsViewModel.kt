package com.plateup.app.feature.recommendations.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecommendationsViewModel : ViewModel() {
    private val _recomendado = MutableStateFlow("Tazón de avena con frutas")
    val recomendado: StateFlow<String> = _recomendado

    private val _sugerencia = MutableStateFlow("Prueba algo ligero hoy")
    val sugerencia: StateFlow<String> = _sugerencia
}
