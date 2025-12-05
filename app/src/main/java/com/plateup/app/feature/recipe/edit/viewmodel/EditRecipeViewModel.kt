package com.plateup.app.feature.recipe.edit.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditRecipeViewModel : ViewModel() {
    private val _titulo = MutableStateFlow("")
    val titulo: StateFlow<String> = _titulo.asStateFlow()

    fun actualizarTitulo(value: String) {
        _titulo.value = value
    }
}
