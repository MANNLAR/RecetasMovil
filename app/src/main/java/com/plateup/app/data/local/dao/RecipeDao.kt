package com.plateup.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.plateup.app.data.local.entity.CommentEntity
import com.plateup.app.data.local.entity.FridgeIngredientEntity
import com.plateup.app.data.local.entity.IngredientEntity
import com.plateup.app.data.local.entity.RatingEntity
import com.plateup.app.data.local.entity.RecipeEntity
import com.plateup.app.data.local.entity.SavedRecipeEntity
import com.plateup.app.data.local.entity.StepEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity): Long

    @Update
    suspend fun updateRecipe(recipe: RecipeEntity)

    @Query("DELETE FROM recipes WHERE id = :id")
    suspend fun deleteRecipe(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(items: List<IngredientEntity>)

    @Query("DELETE FROM ingredients WHERE recipeId = :recipeId")
    suspend fun clearIngredients(recipeId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSteps(items: List<StepEntity>)

    @Query("DELETE FROM steps WHERE recipeId = :recipeId")
    suspend fun clearSteps(recipeId: Long)

    @Transaction
    @Query("SELECT * FROM recipes ORDER BY titulo")
    fun observeRecipes(): Flow<List<RecipeEntity>>

    @Transaction
    @Query("SELECT * FROM recipes WHERE id = :id")
    fun observeRecipe(id: Long): Flow<RecipeEntity?>

    @Query("SELECT * FROM ingredients WHERE recipeId = :recipeId")
    fun observeIngredients(recipeId: Long): Flow<List<IngredientEntity>>

    @Query("SELECT * FROM steps WHERE recipeId = :recipeId ORDER BY orden ASC")
    fun observeSteps(recipeId: Long): Flow<List<StepEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRating(rating: RatingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSaved(saved: SavedRecipeEntity)

    @Query("SELECT * FROM saved_recipes WHERE userId = :userId")
    fun observeSaved(userId: Long): Flow<List<SavedRecipeEntity>>

    @Query("DELETE FROM saved_recipes WHERE userId = :userId AND recipeId = :recipeId")
    suspend fun removeSaved(userId: Long, recipeId: Long)

    @Query(
        "SELECT r.* FROM recipes r " +
            "LEFT JOIN ingredients i ON r.id = i.recipeId " +
            "WHERE (LOWER(r.titulo) LIKE '%' || LOWER(:query) || '%' OR LOWER(i.nombre) LIKE '%' || LOWER(:query) || '%') " +
            "AND (:categoria IS NULL OR r.categoria = :categoria) " +
            "AND (:maxTiempo IS NULL OR r.tiempoMinutos <= :maxTiempo) " +
            "AND (:maxCosto IS NULL OR CAST(r.costo AS INTEGER) <= :maxCosto) " +
            "GROUP BY r.id"
    )
    fun searchRecipes(
        query: String,
        categoria: String?,
        maxTiempo: Int?,
        maxCosto: Int?
    ): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE id IN (:ids)")
    fun observeRecipesByIds(ids: List<Long>): Flow<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertFridgeIngredient(entity: FridgeIngredientEntity)

    @Query("DELETE FROM fridge_ingredients WHERE id = :id")
    suspend fun deleteFridgeIngredient(id: Long)

    @Query("SELECT * FROM fridge_ingredients")
    fun observeFridge(): Flow<List<FridgeIngredientEntity>>

    @Query(
        "SELECT r.* FROM recipes r JOIN ingredients i ON r.id = i.recipeId " +
            "WHERE LOWER(i.nombre) LIKE '%' || LOWER(:ingredient) || '%' GROUP BY r.id"
    )
    fun findByIngredient(ingredient: String): Flow<List<RecipeEntity>>
}
