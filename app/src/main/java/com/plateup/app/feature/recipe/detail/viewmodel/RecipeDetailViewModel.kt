package com.plateup.app.feature.recipe.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plateup.app.domain.model.Recipe
import com.plateup.app.domain.usecase.recipe.ObserveRecipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeDetailViewModel(private val observeRecipe: ObserveRecipe) : ViewModel() {
    private val _receta = MutableStateFlow<Recipe?>(null)
    val receta: StateFlow<Recipe?> = _receta.asStateFlow()

    fun cargar(id: Long) {
        viewModelScope.launch {
            observeRecipe(id).collect { _receta.value = it }
        }
    }
}
