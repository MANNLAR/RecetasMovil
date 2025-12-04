package com.plateup.app.domain.model

data class Recipe(
    val id: Long,
    val titulo: String,
    val categoria: String,
    val tiempoMinutos: Int,
    val costo: String,
    val calificacion: Float,
    val imagenUrl: String?
)

data class Ingredient(
    val id: Long,
    val recetaId: Long,
    val nombre: String,
    val cantidad: String
)

data class Step(
    val id: Long,
    val recetaId: Long,
    val orden: Int,
    val descripcion: String
)

data class Comment(
    val id: Long,
    val recetaId: Long,
    val usuario: String,
    val comentario: String,
    val calificacion: Float,
    val fecha: Long
)

data class SavedRecipe(
    val id: Long,
    val userId: Long,
    val recetaId: Long,
    val fecha: Long
)

data class FridgeItem(
    val id: Long,
    val nombre: String,
    val cantidad: String?
)

data class ChatMessage(
    val id: Long,
    val fromUser: Boolean,
    val mensaje: String
)
