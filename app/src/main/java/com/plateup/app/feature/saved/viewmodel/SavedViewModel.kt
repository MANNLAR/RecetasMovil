package com.plateup.app.feature.saved.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SavedViewModel : ViewModel() {
    private val _mensaje = MutableStateFlow("Tus recetas guardadas aparecerán aquí")
    val mensaje: StateFlow<String> = _mensaje
}
