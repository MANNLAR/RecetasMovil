package com.plateup.app.domain.model

data class Recipe(
    val id: Long,
    val titulo: String,
    val categoria: String,
    val tiempoMinutos: Int,
    val costo: String,
    val calificacion: Double,
    val imagenUrl: String?,
    val ingredientes: List<Ingredient> = emptyList(),
    val pasos: List<Step> = emptyList()
)

data class Ingredient(
    val nombre: String,
    val cantidad: String
)

data class Step(
    val orden: Int,
    val descripcion: String
)
