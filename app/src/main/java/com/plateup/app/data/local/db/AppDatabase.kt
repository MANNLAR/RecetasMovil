package com.plateup.app.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.plateup.app.data.local.dao.AuthDao
import com.plateup.app.data.local.dao.RecipeDao
import com.plateup.app.data.local.entity.CommentEntity
import com.plateup.app.data.local.entity.IngredientEntity
import com.plateup.app.data.local.entity.RatingEntity
import com.plateup.app.data.local.entity.RecipeEntity
import com.plateup.app.data.local.entity.SavedRecipeEntity
import com.plateup.app.data.local.entity.StepEntity
import com.plateup.app.data.local.entity.UserEntity
import com.plateup.app.data.local.entity.UserProfileEntity
import com.plateup.app.core.util.PasswordHasher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        UserEntity::class,
        UserProfileEntity::class,
        RecipeEntity::class,
        IngredientEntity::class,
        StepEntity::class,
        RatingEntity::class,
        CommentEntity::class,
        SavedRecipeEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDao
    abstract fun recipeDao(): RecipeDao

    companion object {
        fun build(context: Context): AppDatabase {
            val db = Room.databaseBuilder(context, AppDatabase::class.java, "plateup.db")
                .fallbackToDestructiveMigration()
                .build()
            CoroutineScope(Dispatchers.IO).launch { prepopulate(db) }
            return db
        }

        private suspend fun prepopulate(database: AppDatabase) {
            val authDao = database.authDao()
            val recipeDao = database.recipeDao()
            val userId = authDao.insertUser(
                UserEntity(
                    username = "chef_local",
                    email = "chef@plateup.app",
                    hashedPassword = PasswordHasher.hash("demo1234")
                )
            )
            authDao.upsertProfile(
                UserProfileEntity(
                    userId = userId,
                    altura = 1.7,
                    peso = 70.0,
                    edad = 30,
                    sexo = "Masculino",
                    nivelActividad = "Activo",
                    alergias = "Nueces",
                    preferenciasDieteticas = "Sin lactosa",
                    notasMedicas = "Sin restricciones adicionales"
                )
            )

            val recetas = listOf(
                RecipeEntity(
                    titulo = "Ensalada fresca",
                    categoria = "Saludable",
                    tiempoMinutos = 15,
                    costo = "Bajo",
                    calificacion = 4.5,
                    imagenUrl = null
                ),
                RecipeEntity(
                    titulo = "Pasta cremosa",
                    categoria = "Rápido",
                    tiempoMinutos = 25,
                    costo = "Medio",
                    calificacion = 4.0,
                    imagenUrl = null
                ),
                RecipeEntity(
                    titulo = "Tacos de pollo",
                    categoria = "Alta proteína",
                    tiempoMinutos = 35,
                    costo = "Bajo",
                    calificacion = 4.8,
                    imagenUrl = null
                )
            )
            recetas.forEach { receta ->
                val recipeId = recipeDao.upsertRecipe(receta)
                recipeDao.insertIngredients(
                    listOf(
                        IngredientEntity(recipeId = recipeId, nombre = "Ingrediente A", cantidad = "1 taza"),
                        IngredientEntity(recipeId = recipeId, nombre = "Ingrediente B", cantidad = "2 cdas")
                    )
                )
                recipeDao.insertSteps(
                    listOf(
                        StepEntity(recipeId = recipeId, orden = 1, descripcion = "Preparar ingredientes"),
                        StepEntity(recipeId = recipeId, orden = 2, descripcion = "Cocinar y servir")
                    )
                )
                recipeDao.insertRating(RatingEntity(recipeId = recipeId, userId = userId, valor = 5))
                recipeDao.insertComment(
                    CommentEntity(
                        recipeId = recipeId,
                        userId = userId,
                        texto = "Receta deliciosa y fácil."
                    )
                )
            }
        }
    }
}
