package com.plateup.app.data.mapper

import com.plateup.app.data.local.entity.IngredientEntity
import com.plateup.app.data.local.entity.RecipeEntity
import com.plateup.app.data.local.entity.StepEntity
import com.plateup.app.domain.model.Ingredient
import com.plateup.app.domain.model.Recipe
import com.plateup.app.domain.model.Step

fun RecipeEntity.toDomain(
    ingredients: List<IngredientEntity> = emptyList(),
    steps: List<StepEntity> = emptyList()
) = Recipe(
    id = id,
    titulo = titulo,
    categoria = categoria,
    tiempoMinutos = tiempoMinutos,
    costo = costo,
    calificacion = calificacion,
    imagenUrl = imagenUrl,
    ingredientes = ingredients.map { it.toDomain() },
    pasos = steps.map { it.toDomain() }
)

fun IngredientEntity.toDomain() = Ingredient(nombre = nombre, cantidad = cantidad)
fun StepEntity.toDomain() = Step(orden = orden, descripcion = descripcion)
