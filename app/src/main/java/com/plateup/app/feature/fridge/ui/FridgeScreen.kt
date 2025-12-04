package com.plateup.app.feature.fridge.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.feature.fridge.viewmodel.FridgeViewModel

@Composable
fun FridgeScreen(viewModel: FridgeViewModel, onBuscarRecetas: () -> Unit = {}) {
    val estado by viewModel.estado.collectAsState()
    val nuevoIngrediente = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        TextField(
            value = nuevoIngrediente.value,
            onValueChange = { nuevoIngrediente.value = it },
            label = { Text("Agregar ingrediente…") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { viewModel.agregar(nuevoIngrediente.value); nuevoIngrediente.value = "" }) { Text("Agregar") }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(estado.items) { item ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(item.nombre)
                    Text(item.cantidad ?: "")
                }
            }
        }
        Button(onClick = onBuscarRecetas) { Text("Buscar recetas con estos ingredientes") }
    }
}
