package com.plateup.app.feature.myrecipes.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyRecipesViewModel : ViewModel() {
    private val _mensaje = MutableStateFlow("Crea y organiza tus recetas personales")
    val mensaje: StateFlow<String> = _mensaje
}
