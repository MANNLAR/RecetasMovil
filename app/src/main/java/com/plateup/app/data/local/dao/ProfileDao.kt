package com.plateup.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.plateup.app.data.local.entity.UserProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarPerfil(perfil: UserProfileEntity)

    @Query("SELECT * FROM perfiles WHERE userId = :userId LIMIT 1")
    fun observarPerfil(userId: Long): Flow<UserProfileEntity?>
}
