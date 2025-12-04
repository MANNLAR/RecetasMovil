package com.plateup.app.feature.recipe.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plateup.app.domain.model.Recipe
import com.plateup.app.domain.usecase.recipe.ObservarRecetas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class RecipeListState(
    val recetas: List<Recipe> = emptyList(),
    val consulta: String = "",
    val filtroCategoria: String? = null,
    val filtroCosto: String? = null,
    val filtroTiempo: Int? = null
)

class RecipeListViewModel(private val observarRecetas: ObservarRecetas) : ViewModel() {
    private val _estado = MutableStateFlow(RecipeListState())
    val estado: StateFlow<RecipeListState> = _estado

    init {
        viewModelScope.launch {
            observarRecetas().collectLatest { recetas ->
                _estado.value = _estado.value.copy(recetas = recetas)
            }
        }
    }

    fun actualizarConsulta(valor: String) {
        _estado.value = _estado.value.copy(consulta = valor)
    }

    fun filtrarCategoria(categoria: String?) {
        _estado.value = _estado.value.copy(filtroCategoria = categoria)
    }
}
