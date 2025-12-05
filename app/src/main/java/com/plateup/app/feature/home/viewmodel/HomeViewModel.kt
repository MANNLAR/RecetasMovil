package com.plateup.app.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plateup.app.domain.model.Recipe
import com.plateup.app.domain.usecase.recipe.ObserveRecipes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val observeRecipes: ObserveRecipes) : ViewModel() {
    private val _recetas = MutableStateFlow<List<Recipe>>(emptyList())
    val recetas: StateFlow<List<Recipe>> = _recetas.asStateFlow()

    init {
        viewModelScope.launch {
            observeRecipes().collect { _recetas.value = it }
        }
    }
}
