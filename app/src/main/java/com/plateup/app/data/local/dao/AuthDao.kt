package com.plateup.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.plateup.app.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registrar(usuario: UserEntity): Long

    @Query("SELECT * FROM usuarios WHERE username = :username LIMIT 1")
    suspend fun obtenerPorUsuario(username: String): UserEntity?

    @Query("SELECT * FROM usuarios")
    fun observarUsuarios(): Flow<List<UserEntity>>
}
