package com.plateup.app.domain.usecase.recipe

import com.plateup.app.data.repository.RecipeRepository
import com.plateup.app.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

class ObserveRecipes(private val repo: RecipeRepository) {
    operator fun invoke(): Flow<List<Recipe>> = repo.observeRecipes()
}

class ObserveRecipe(private val repo: RecipeRepository) {
    operator fun invoke(id: Long): Flow<Recipe?> = repo.observeRecipe(id)
}

class ToggleSavedRecipe(private val repo: RecipeRepository) {
    suspend operator fun invoke(userId: Long, recipeId: Long, isSaved: Boolean) = repo.toggleSaved(userId, recipeId, isSaved)
}
