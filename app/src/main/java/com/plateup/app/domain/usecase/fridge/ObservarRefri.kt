package com.plateup.app.domain.usecase.fridge

import com.plateup.app.data.repository.FridgeRepository
import com.plateup.app.domain.model.FridgeItem
import kotlinx.coroutines.flow.Flow

class ObservarRefri(private val repository: FridgeRepository) {
    operator fun invoke(): Flow<List<FridgeItem>> = repository.observar()
}
