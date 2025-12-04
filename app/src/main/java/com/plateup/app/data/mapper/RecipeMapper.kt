package com.plateup.app.data.mapper

import com.plateup.app.data.local.entity.CommentEntity
import com.plateup.app.data.local.entity.FridgeItemEntity
import com.plateup.app.data.local.entity.IngredientEntity
import com.plateup.app.data.local.entity.RecipeEntity
import com.plateup.app.data.local.entity.SavedRecipeEntity
import com.plateup.app.data.local.entity.StepEntity
import com.plateup.app.domain.model.Comment
import com.plateup.app.domain.model.FridgeItem
import com.plateup.app.domain.model.Ingredient
import com.plateup.app.domain.model.Recipe
import com.plateup.app.domain.model.SavedRecipe
import com.plateup.app.domain.model.Step

fun RecipeEntity.toDomain() = Recipe(id, titulo, categoria, tiempoMinutos, costo, calificacion, imagenUrl)
fun IngredientEntity.toDomain() = Ingredient(id, recetaId, nombre, cantidad)
fun StepEntity.toDomain() = Step(id, recetaId, orden, descripcion)
fun CommentEntity.toDomain() = Comment(id, recetaId, usuario, comentario, calificacion, fecha)
fun SavedRecipeEntity.toDomain() = SavedRecipe(id, userId, recetaId, fecha)
fun FridgeItemEntity.toDomain() = FridgeItem(id, nombre, cantidad)

fun FridgeItem.toEntity() = FridgeItemEntity(id = id, nombre = nombre, cantidad = cantidad)
