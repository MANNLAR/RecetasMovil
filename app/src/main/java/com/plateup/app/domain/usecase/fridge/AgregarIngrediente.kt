package com.plateup.app.domain.usecase.fridge

import com.plateup.app.data.repository.FridgeRepository
import com.plateup.app.domain.model.FridgeItem

class AgregarIngrediente(private val repository: FridgeRepository) {
    suspend operator fun invoke(nombre: String, cantidad: String?): FridgeItem = repository.agregar(nombre, cantidad)
}
