package com.plateup.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val titulo: String,
    val categoria: String,
    val tiempoMinutos: Int,
    val costo: String,
    val calificacion: Double,
    val imagenUrl: String?,
    val creadoPor: Long,
    val calorias: Int = 0,
    val proteinas: Int = 0,
    val grasas: Int = 0,
    val carbohidratos: Int = 0
)
