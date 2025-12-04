package com.plateup.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.plateup.app.data.local.entity.FridgeItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FridgeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardar(item: FridgeItemEntity)

    @Delete
    suspend fun eliminar(item: FridgeItemEntity)

    @Query("SELECT * FROM refri_items")
    fun observarTodo(): Flow<List<FridgeItemEntity>>
}
