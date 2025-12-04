package com.plateup.app.feature.recommendations.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecommendationsViewModel : ViewModel() {
    private val _recomendado = MutableStateFlow("Pasta cremosa para hoy")
    val recomendado: StateFlow<String> = _recomendado.asStateFlow()
}
