package com.plateup.app.feature.recipe.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plateup.app.domain.model.Recipe
import com.plateup.app.domain.usecase.recipe.SearchRecipes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.Normalizer

data class RecipeFilters(
    val query: String = "",
    val ingredientesFavoritos: List<String> = emptyList(),
    val ingredientesExcluidos: List<String> = emptyList(),
    val maxTiempo: Int? = null,
    val maxCosto: Int? = null,
    val minCalificacion: Double? = null,
    val categoria: String = ""
)

class RecipeListViewModel(private val searchRecipes: SearchRecipes) : ViewModel() {
    private val _recetas = MutableStateFlow<List<Recipe>>(emptyList())
    val recetas: StateFlow<List<Recipe>> = _recetas.asStateFlow()

    private val _filtros = MutableStateFlow(RecipeFilters())

    init {
        viewModelScope.launch {
            _filtros
                .flatMapLatest { filtros ->
                    searchRecipes(
                        filtros.query,
                        filtros.categoria.takeIf { it.isNotBlank() },
                        filtros.maxTiempo,
                        filtros.maxCosto
                    ).map { lista -> aplicarFiltros(lista, filtros) }
                }
                .collect { filtradas ->
                    _recetas.value = filtradas
                }
        }
    }

    fun actualizarBusqueda(query: String) {
        _filtros.value = _filtros.value.copy(query = query)
    }

    fun actualizarIngredientesFavoritos(texto: String) {
        val lista = texto.split(',').map { it.trim() }.filter { it.isNotEmpty() }
        _filtros.value = _filtros.value.copy(ingredientesFavoritos = lista)
    }

    fun actualizarIngredientesExcluidos(texto: String) {
        val lista = texto.split(',').map { it.trim() }.filter { it.isNotEmpty() }
        _filtros.value = _filtros.value.copy(ingredientesExcluidos = lista)
    }

    fun actualizarMaxTiempo(valor: Int?) {
        _filtros.value = _filtros.value.copy(maxTiempo = valor)
    }

    fun actualizarMaxCosto(valor: Int?) {
        _filtros.value = _filtros.value.copy(maxCosto = valor)
    }

    fun actualizarMinCalificacion(valor: Double?) {
        _filtros.value = _filtros.value.copy(minCalificacion = valor)
    }

    private fun aplicarFiltros(recetas: List<Recipe>, filtros: RecipeFilters): List<Recipe> {
        return recetas.filter { receta ->
            val ingredientesNormalizados = receta.ingredientes.joinToString(" ") { normalizar(it.nombre) }
            val deseados = filtros.ingredientesFavoritos.all { ingredientesNormalizados.contains(normalizar(it)) }
            val excluidos = filtros.ingredientesExcluidos.none { ingredientesNormalizados.contains(normalizar(it)) }
            val tiempoOk = filtros.maxTiempo?.let { receta.tiempoMinutos <= it } ?: true
            val costoOk = filtros.maxCosto?.let { costoMax ->
                when (receta.costo.lowercase()) {
                    "bajo" -> 1 <= costoMax
                    "medio" -> 2 <= costoMax
                    "alto" -> 3 <= costoMax
                    else -> true
                }
            } ?: true
            val calificacionOk = filtros.minCalificacion?.let { receta.calificacion >= it } ?: true
            deseados && excluidos && tiempoOk && costoOk && calificacionOk
        }
    }

    private fun normalizar(texto: String): String {
        val normalized = Normalizer.normalize(texto, Normalizer.Form.NFD)
        return normalized.lowercase().replace("[^a-z0-9 ]".toRegex(), "")
    }
}
