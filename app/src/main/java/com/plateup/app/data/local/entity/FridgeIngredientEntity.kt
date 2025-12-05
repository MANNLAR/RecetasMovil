package com.plateup.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fridge_ingredients")
data class FridgeIngredientEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String
)
