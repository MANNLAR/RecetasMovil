package com.plateup.app.feature.recipe.edit.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class EditRecipeState(
    val titulo: String = "",
    val categoria: String = "",
    val tiempo: Int = 0,
    val costo: String = "",
    val descripcion: String = ""
)

class EditRecipeViewModel : ViewModel() {
    private val _estado = MutableStateFlow(EditRecipeState())
    val estado: StateFlow<EditRecipeState> = _estado

    fun actualizarTitulo(valor: String) { _estado.value = _estado.value.copy(titulo = valor) }
    fun actualizarCategoria(valor: String) { _estado.value = _estado.value.copy(categoria = valor) }
    fun actualizarTiempo(valor: Int) { _estado.value = _estado.value.copy(tiempo = valor) }
    fun actualizarCosto(valor: String) { _estado.value = _estado.value.copy(costo = valor) }
    fun actualizarDescripcion(valor: String) { _estado.value = _estado.value.copy(descripcion = valor) }
}
