package com.plateup.app.domain.usecase.recipe

import com.plateup.app.data.repository.RecipeRepository
import com.plateup.app.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

class ObservarRecetas(private val repository: RecipeRepository) {
    operator fun invoke(): Flow<List<Recipe>> = repository.observarRecetas()
}
