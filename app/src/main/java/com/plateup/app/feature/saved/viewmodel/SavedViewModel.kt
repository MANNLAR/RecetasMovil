package com.plateup.app.feature.saved.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SavedViewModel : ViewModel() {
    private val _mensaje = MutableStateFlow("Aún no tienes recetas guardadas")
    val mensaje: StateFlow<String> = _mensaje.asStateFlow()
}
