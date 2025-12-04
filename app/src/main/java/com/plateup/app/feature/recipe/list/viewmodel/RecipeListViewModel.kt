package com.plateup.app.feature.recipe.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plateup.app.domain.model.Recipe
import com.plateup.app.domain.usecase.recipe.ObserveRecipes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeListViewModel(private val observeRecipes: ObserveRecipes) : ViewModel() {
    private val _busqueda = MutableStateFlow("")
    private val _recetas = MutableStateFlow<List<Recipe>>(emptyList())
    val recetas: StateFlow<List<Recipe>> = _recetas.asStateFlow()

    init {
        viewModelScope.launch {
            observeRecipes().collect { lista ->
                _recetas.value = lista
            }
        }
    }

    fun actualizarBusqueda(query: String) {
        _busqueda.value = query
        _recetas.value = _recetas.value.filter { it.titulo.contains(query, ignoreCase = true) }
    }
}
