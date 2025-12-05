package com.plateup.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val recipeId: Long,
    val userId: Long,
    val texto: String,
    val createdAt: Long = System.currentTimeMillis()
)
