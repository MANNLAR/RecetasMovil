package com.plateup.app.feature.botchef.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Mensaje(val autor: String, val contenido: String)

class BotChefViewModel : ViewModel() {
    private val _mensajes = MutableStateFlow<List<Mensaje>>(listOf(Mensaje("Bot", "¡Hola! Soy tu Bot Chef, cuéntame tus ingredientes.")))
    val mensajes: StateFlow<List<Mensaje>> = _mensajes.asStateFlow()

    fun enviar(texto: String) {
        if (texto.isBlank()) return
        val nuevos = _mensajes.value + Mensaje("Tú", texto)
        val sugerencia = Mensaje("Bot", "Recetas sugeridas con ${'$'}texto: ensalada fresca, pasta cremosa")
        _mensajes.value = nuevos + sugerencia
    }
}
