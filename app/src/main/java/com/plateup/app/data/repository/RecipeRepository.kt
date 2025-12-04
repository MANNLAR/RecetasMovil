package com.plateup.app.data.repository

import com.plateup.app.data.local.dao.RecipeDao
import com.plateup.app.data.mapper.toDomain
import com.plateup.app.domain.model.Comment
import com.plateup.app.domain.model.Ingredient
import com.plateup.app.domain.model.Recipe
import com.plateup.app.domain.model.SavedRecipe
import com.plateup.app.domain.model.Step
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class RecipeRepository(private val recipeDao: RecipeDao) {
    fun observarRecetas(): Flow<List<Recipe>> = recipeDao.observarRecetas().map { it.map { receta -> receta.toDomain() } }

    fun observarDetalle(recetaId: Long): Flow<Triple<Recipe?, List<Ingredient>, List<Step>>> = combine(
        recipeDao.observarReceta(recetaId),
        recipeDao.observarIngredientes(recetaId),
        recipeDao.observarPasos(recetaId)
    ) { receta, ingredientes, pasos ->
        Triple(receta?.toDomain(), ingredientes.map { it.toDomain() }, pasos.map { it.toDomain() })
    }

    fun observarComentarios(recetaId: Long): Flow<List<Comment>> = recipeDao.observarComentarios(recetaId).map { list -> list.map { it.toDomain() } }

    fun observarGuardados(userId: Long): Flow<List<SavedRecipe>> = recipeDao.observarGuardados(userId).map { list -> list.map { it.toDomain() } }
}
