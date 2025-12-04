package com.plateup.app.feature.myrecipes.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyRecipesViewModel : ViewModel() {
    private val _mensaje = MutableStateFlow("Crea tus propias recetas para verlas aquí")
    val mensaje: StateFlow<String> = _mensaje.asStateFlow()
}
