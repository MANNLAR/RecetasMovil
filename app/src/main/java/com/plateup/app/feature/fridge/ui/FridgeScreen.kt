package com.plateup.app.feature.fridge.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.core.ui.PrimaryButton
import com.plateup.app.feature.fridge.viewmodel.FridgeViewModel

@Composable
fun FridgeScreen(viewModel: FridgeViewModel) {
    val ingrediente = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Mi refri", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(
            value = ingrediente.value,
            onValueChange = { ingrediente.value = it },
            label = { Text("Agregar ingrediente…") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        PrimaryButton(text = "Agregar") {
            viewModel.agregar(ingrediente.value)
            ingrediente.value = ""
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(viewModel.ingredientes.value) { item ->
                Text(text = "- ${'$'}item")
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        PrimaryButton(text = "Buscar recetas con estos ingredientes") { }
    }
}
