package com.plateup.app.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.plateup.app.data.local.dao.AuthDao
import com.plateup.app.data.local.dao.FridgeDao
import com.plateup.app.data.local.dao.ProfileDao
import com.plateup.app.data.local.dao.RecipeDao
import com.plateup.app.data.local.entity.CommentEntity
import com.plateup.app.data.local.entity.FridgeItemEntity
import com.plateup.app.data.local.entity.IngredientEntity
import com.plateup.app.data.local.entity.RecipeEntity
import com.plateup.app.data.local.entity.SavedRecipeEntity
import com.plateup.app.data.local.entity.StepEntity
import com.plateup.app.data.local.entity.UserEntity
import com.plateup.app.data.local.entity.UserProfileEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

@Database(
    entities = [
        UserEntity::class,
        UserProfileEntity::class,
        RecipeEntity::class,
        IngredientEntity::class,
        StepEntity::class,
        CommentEntity::class,
        SavedRecipeEntity::class,
        FridgeItemEntity::class
    ],
    version = 1
)
abstract class PlateUpDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDao
    abstract fun profileDao(): ProfileDao
    abstract fun recipeDao(): RecipeDao
    abstract fun fridgeDao(): FridgeDao

    companion object {
        @Volatile
        private var INSTANCE: PlateUpDatabase? = null

        fun obtenerInstancia(context: Context): PlateUpDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    PlateUpDatabase::class.java,
                    "plateup.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            obtenerInstancia(context).preCargarRecetas()
                        }
                    }
                }).build().also { INSTANCE = it }
            }
        }
    }

    private suspend fun preCargarRecetas() {
        val recetaDao = recipeDao()
        val baseRecetas = listOf(
            RecipeEntity(
                titulo = "Ensalada fresca",
                categoria = "Saludable",
                tiempoMinutos = 10,
                costo = "Bajo",
                calificacion = 4.5f,
                imagenUrl = null
            ),
            RecipeEntity(
                titulo = "Pasta cremosa",
                categoria = "Rápido",
                tiempoMinutos = 25,
                costo = "Medio",
                calificacion = 4.2f,
                imagenUrl = null
            ),
            RecipeEntity(
                titulo = "Tacos de pollo",
                categoria = "Económico",
                tiempoMinutos = 30,
                costo = "Bajo",
                calificacion = 4.8f,
                imagenUrl = null
            )
        )

        baseRecetas.forEach { receta ->
            val id = recetaDao.guardarReceta(receta)
            recetaDao.guardarIngredientes(
                listOf(
                    IngredientEntity(recetaId = id, nombre = "Ingrediente base", cantidad = "Al gusto"),
                    IngredientEntity(recetaId = id, nombre = "Complemento", cantidad = "1 taza")
                )
            )
            recetaDao.guardarPasos(
                listOf(
                    StepEntity(recetaId = id, orden = 1, descripcion = "Preparar ingredientes"),
                    StepEntity(recetaId = id, orden = 2, descripcion = "Cocinar y servir")
                )
            )
        }
    }
}
