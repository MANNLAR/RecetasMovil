package com.plateup.app.data.repository

import com.plateup.app.data.local.dao.RecipeDao
import com.plateup.app.data.mapper.toDomain
import com.plateup.app.domain.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class RecipeRepository(private val recipeDao: RecipeDao) {
    fun observeRecipes(): Flow<List<Recipe>> = recipeDao.observeRecipes().flatMapLatest { recipes ->
        if (recipes.isEmpty()) return@flatMapLatest kotlinx.coroutines.flow.flowOf(emptyList())
        combine(recipes.map { recipe ->
            combine(
                recipeDao.observeIngredients(recipe.id),
                recipeDao.observeSteps(recipe.id)
            ) { ingredients, steps ->
                recipe.toDomain(ingredients, steps)
            }
        }) { it.toList() }
    }

    fun observeRecipe(id: Long): Flow<Recipe?> = recipeDao.observeRecipe(id).flatMapLatest { recipe ->
        if (recipe == null) return@flatMapLatest kotlinx.coroutines.flow.flowOf(null)
        combine(
            recipeDao.observeIngredients(recipe.id),
            recipeDao.observeSteps(recipe.id)
        ) { ingredients, steps ->
            recipe.toDomain(ingredients, steps)
        }
    }

    suspend fun toggleSaved(userId: Long, recipeId: Long, isSaved: Boolean) {
        if (isSaved) {
            recipeDao.removeSaved(userId, recipeId)
        } else {
            recipeDao.insertSaved(
                com.plateup.app.data.local.entity.SavedRecipeEntity(
                    userId = userId,
                    recipeId = recipeId
                )
            )
        }
    }
}
