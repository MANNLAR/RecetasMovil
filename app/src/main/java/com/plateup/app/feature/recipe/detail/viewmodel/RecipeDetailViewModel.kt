package com.plateup.app.feature.recipe.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plateup.app.domain.model.Ingredient
import com.plateup.app.domain.model.Recipe
import com.plateup.app.domain.model.Step
import com.plateup.app.domain.usecase.recipe.ObservarDetalleReceta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class RecipeDetailState(
    val receta: Recipe? = null,
    val ingredientes: List<Ingredient> = emptyList(),
    val pasos: List<Step> = emptyList()
)

class RecipeDetailViewModel(private val observarDetalleReceta: ObservarDetalleReceta) : ViewModel() {
    private val _estado = MutableStateFlow(RecipeDetailState())
    val estado: StateFlow<RecipeDetailState> = _estado

    fun cargar(recetaId: Long) {
        viewModelScope.launch {
            observarDetalleReceta(recetaId).collectLatest { triple ->
                _estado.value = RecipeDetailState(
                    receta = triple.first,
                    ingredientes = triple.second,
                    pasos = triple.third
                )
            }
        }
    }
}
