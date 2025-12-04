package com.plateup.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.plateup.app.data.local.entity.CommentEntity
import com.plateup.app.data.local.entity.IngredientEntity
import com.plateup.app.data.local.entity.RecipeEntity
import com.plateup.app.data.local.entity.SavedRecipeEntity
import com.plateup.app.data.local.entity.StepEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarReceta(receta: RecipeEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarIngredientes(ingredientes: List<IngredientEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarPasos(pasos: List<StepEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarComentario(comentario: CommentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarGuardado(guardado: SavedRecipeEntity)

    @Query("DELETE FROM guardados WHERE userId = :userId AND recetaId = :recetaId")
    suspend fun eliminarGuardado(userId: Long, recetaId: Long)

    @Query("SELECT * FROM recetas")
    fun observarRecetas(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recetas WHERE id = :recetaId")
    fun observarReceta(recetaId: Long): Flow<RecipeEntity?>

    @Query("SELECT * FROM ingredientes WHERE recetaId = :recetaId")
    fun observarIngredientes(recetaId: Long): Flow<List<IngredientEntity>>

    @Query("SELECT * FROM pasos WHERE recetaId = :recetaId ORDER BY orden ASC")
    fun observarPasos(recetaId: Long): Flow<List<StepEntity>>

    @Query("SELECT * FROM comentarios WHERE recetaId = :recetaId ORDER BY fecha DESC")
    fun observarComentarios(recetaId: Long): Flow<List<CommentEntity>>

    @Query("SELECT * FROM guardados WHERE userId = :userId")
    fun observarGuardados(userId: Long): Flow<List<SavedRecipeEntity>>
}
