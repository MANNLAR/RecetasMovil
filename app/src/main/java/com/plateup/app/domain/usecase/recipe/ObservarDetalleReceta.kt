package com.plateup.app.domain.usecase.recipe

import com.plateup.app.data.repository.RecipeRepository
import com.plateup.app.domain.model.Ingredient
import com.plateup.app.domain.model.Recipe
import com.plateup.app.domain.model.Step
import kotlinx.coroutines.flow.Flow

class ObservarDetalleReceta(private val repository: RecipeRepository) {
    operator fun invoke(recetaId: Long): Flow<Triple<Recipe?, List<Ingredient>, List<Step>>> =
        repository.observarDetalle(recetaId)
}
