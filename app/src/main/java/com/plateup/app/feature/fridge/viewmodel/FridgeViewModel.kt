package com.plateup.app.feature.fridge.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FridgeViewModel : ViewModel() {
    private val _ingredientes = MutableStateFlow<List<String>>(emptyList())
    val ingredientes: StateFlow<List<String>> = _ingredientes.asStateFlow()

    fun agregar(ingrediente: String) {
        if (ingrediente.isNotBlank()) {
            _ingredientes.value = _ingredientes.value + ingrediente
        }
    }
}
