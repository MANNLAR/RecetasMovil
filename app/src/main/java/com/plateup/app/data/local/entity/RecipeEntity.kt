package com.plateup.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recetas")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val titulo: String,
    val categoria: String,
    val tiempoMinutos: Int,
    val costo: String,
    val calificacion: Float,
    val imagenUrl: String?
)
