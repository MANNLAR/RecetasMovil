package com.plateup.app.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _mensaje = MutableStateFlow("Bienvenido a PlateUP")
    val mensaje: StateFlow<String> = _mensaje
}
