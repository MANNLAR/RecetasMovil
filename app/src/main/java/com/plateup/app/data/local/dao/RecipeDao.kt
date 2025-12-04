package com.plateup.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.plateup.app.data.local.entity.CommentEntity
import com.plateup.app.data.local.entity.IngredientEntity
import com.plateup.app.data.local.entity.RatingEntity
import com.plateup.app.data.local.entity.RecipeEntity
import com.plateup.app.data.local.entity.SavedRecipeEntity
import com.plateup.app.data.local.entity.StepEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRecipe(recipe: RecipeEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(items: List<IngredientEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSteps(items: List<StepEntity>)

    @Transaction
    @Query("SELECT * FROM recipes")
    fun observeRecipes(): Flow<List<RecipeEntity>>

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
}
