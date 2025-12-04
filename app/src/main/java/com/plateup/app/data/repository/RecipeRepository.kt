package com.plateup.app.data.repository

import com.plateup.app.data.local.dao.RecipeDao
import com.plateup.app.data.local.entity.FridgeIngredientEntity
import com.plateup.app.data.local.entity.IngredientEntity
import com.plateup.app.data.local.entity.RecipeEntity
import com.plateup.app.data.local.entity.StepEntity
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

    suspend fun createOrUpdate(recipe: Recipe, ingredients: List<IngredientEntity>, steps: List<StepEntity>) {
        val recipeId = if (recipe.id == 0L) {
            recipeDao.insertRecipe(
                RecipeEntity(
                    id = 0,
                    titulo = recipe.titulo,
                    categoria = recipe.categoria,
                    tiempoMinutos = recipe.tiempoMinutos,
                    costo = recipe.costo,
                    calificacion = recipe.calificacion,
                    imagenUrl = recipe.imagenUrl,
                    creadoPor = recipe.creadoPor,
                    calorias = recipe.calorias,
                    proteinas = recipe.proteinas,
                    grasas = recipe.grasas,
                    carbohidratos = recipe.carbohidratos
                )
            )
        } else {
            recipeDao.updateRecipe(
                RecipeEntity(
                    id = recipe.id,
                    titulo = recipe.titulo,
                    categoria = recipe.categoria,
                    tiempoMinutos = recipe.tiempoMinutos,
                    costo = recipe.costo,
                    calificacion = recipe.calificacion,
                    imagenUrl = recipe.imagenUrl,
                    creadoPor = recipe.creadoPor,
                    calorias = recipe.calorias,
                    proteinas = recipe.proteinas,
                    grasas = recipe.grasas,
                    carbohidratos = recipe.carbohidratos
                )
            )
            recipe.id
        }
        recipeDao.clearIngredients(recipeId)
        recipeDao.clearSteps(recipeId)
        recipeDao.insertIngredients(ingredients.map { it.copy(recipeId = recipeId) })
        recipeDao.insertSteps(steps.map { it.copy(recipeId = recipeId) })
    }

    suspend fun delete(id: Long) = recipeDao.deleteRecipe(id)

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

    fun search(
        query: String,
        categoria: String?,
        maxTiempo: Int?,
        maxCosto: Int?
    ): Flow<List<Recipe>> = recipeDao.searchRecipes(query, categoria, maxTiempo, maxCosto)
        .flatMapLatest { recipes ->
            if (recipes.isEmpty()) return@flatMapLatest kotlinx.coroutines.flow.flowOf(emptyList())
            combine(recipes.map { recipe ->
                combine(
                    recipeDao.observeIngredients(recipe.id),
                    recipeDao.observeSteps(recipe.id)
                ) { ingredients, steps -> recipe.toDomain(ingredients, steps) }
            }) { it.toList() }
        }

    fun savedForUser(userId: Long): Flow<List<Recipe>> = recipeDao.observeSaved(userId).flatMapLatest { saved ->
        val ids = saved.map { it.recipeId }
        if (ids.isEmpty()) return@flatMapLatest kotlinx.coroutines.flow.flowOf(emptyList())
        recipeDao.observeRecipesByIds(ids).flatMapLatest { recipes ->
            combine(recipes.map { recipe ->
                combine(recipeDao.observeIngredients(recipe.id), recipeDao.observeSteps(recipe.id)) { ing, st ->
                    recipe.toDomain(ing, st)
                }
            }) { it.toList() }
        }
    }

    suspend fun addFridgeIngredient(nombre: String) {
        recipeDao.upsertFridgeIngredient(FridgeIngredientEntity(nombre = nombre))
    }

    suspend fun removeFridgeIngredient(id: Long) = recipeDao.deleteFridgeIngredient(id)

    fun observeFridge(): Flow<List<FridgeIngredientEntity>> = recipeDao.observeFridge()

    fun suggestionsFromFridge(nombre: String): Flow<List<Recipe>> = recipeDao.findByIngredient(nombre)
        .flatMapLatest { recipes ->
            if (recipes.isEmpty()) return@flatMapLatest kotlinx.coroutines.flow.flowOf(emptyList())
            combine(recipes.map { recipe ->
                combine(recipeDao.observeIngredients(recipe.id), recipeDao.observeSteps(recipe.id)) { ing, st ->
                    recipe.toDomain(ing, st)
                }
            }) { it.toList() }
        }
}
