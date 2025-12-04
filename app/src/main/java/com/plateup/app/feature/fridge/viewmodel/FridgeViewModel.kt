package com.plateup.app.feature.fridge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plateup.app.domain.model.FridgeItem
import com.plateup.app.domain.usecase.fridge.AgregarIngrediente
import com.plateup.app.domain.usecase.fridge.ObservarRefri
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class FridgeState(
    val items: List<FridgeItem> = emptyList(),
    val mensaje: String? = null
)

class FridgeViewModel(
    private val agregarIngrediente: AgregarIngrediente,
    private val observarRefri: ObservarRefri
) : ViewModel() {
    private val _estado = MutableStateFlow(FridgeState())
    val estado: StateFlow<FridgeState> = _estado

    init {
        viewModelScope.launch {
            observarRefri().collectLatest { lista ->
                _estado.value = _estado.value.copy(items = lista)
            }
        }
    }

    fun agregar(nombre: String) {
        viewModelScope.launch {
            agregarIngrediente(nombre, null)
            _estado.value = _estado.value.copy(mensaje = "Ingrediente agregado")
        }
    }
}
