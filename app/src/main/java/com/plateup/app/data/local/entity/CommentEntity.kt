package com.plateup.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comentarios")
data class CommentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val recetaId: Long,
    val usuario: String,
    val comentario: String,
    val calificacion: Float,
    val fecha: Long
)
