package com.plateup.app.data.repository

import com.plateup.app.data.local.dao.FridgeDao
import com.plateup.app.data.mapper.toDomain
import com.plateup.app.data.mapper.toEntity
import com.plateup.app.domain.model.FridgeItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FridgeRepository(private val fridgeDao: FridgeDao) {
    suspend fun agregar(nombre: String, cantidad: String?): FridgeItem {
        val entity = FridgeItem(id = 0, nombre = nombre, cantidad = cantidad).toEntity()
        fridgeDao.guardar(entity)
        return entity.copy().toDomain()
    }

    suspend fun eliminar(item: FridgeItem) {
        fridgeDao.eliminar(item.toEntity())
    }

    fun observar(): Flow<List<FridgeItem>> = fridgeDao.observarTodo().map { it.map { item -> item.toDomain() } }
}
