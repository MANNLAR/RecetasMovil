package com.plateup.app.feature.botchef.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plateup.app.domain.model.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BotChefViewModel : ViewModel() {
    private val _mensajes = MutableStateFlow(listOf(ChatMessage(1, false, "Hola, soy tu Bot Chef. Cuéntame qué ingredientes tienes")))
    val mensajes: StateFlow<List<ChatMessage>> = _mensajes

    fun enviar(mensaje: String) {
        val actual = _mensajes.value.toMutableList()
        val idNuevo = (actual.maxOfOrNull { it.id } ?: 0) + 1
        actual.add(ChatMessage(idNuevo, true, mensaje))
        actual.add(ChatMessage(idNuevo + 1, false, "Sugerencia: prueba una ensalada con $mensaje"))
        _mensajes.value = actual
    }
}
