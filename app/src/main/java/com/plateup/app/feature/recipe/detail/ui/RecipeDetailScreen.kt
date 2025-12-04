package com.plateup.app.feature.recipe.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.feature.recipe.detail.viewmodel.RecipeDetailViewModel

@Composable
fun RecipeDetailScreen(viewModel: RecipeDetailViewModel) {
    val estado by viewModel.estado.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = estado.receta?.titulo ?: "Cargando…", style = MaterialTheme.typography.titleLarge)
        Text(text = "Categoría: ${estado.receta?.categoria ?: ""}")
        Text(text = "Tiempo: ${estado.receta?.tiempoMinutos ?: 0} minutos")
        Text(text = "Costo: ${estado.receta?.costo ?: ""}")
        Text(text = "Calificación: ${estado.receta?.calificacion ?: 0f}")
        Text(text = "TODO: imagen principal de la receta")

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Ingredientes", style = MaterialTheme.typography.titleMedium)
                LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    items(estado.ingredientes) { ing ->
                        Text("• ${ing.nombre} - ${ing.cantidad}")
                    }
                }
            }
        }
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Preparación", style = MaterialTheme.typography.titleMedium)
                estado.pasos.forEach { paso ->
                    Text("Paso ${paso.orden}: ${paso.descripcion}")
                }
            }
        }
        Button(onClick = { }) { Text("Guardar") }
        Button(onClick = { }) { Text("Compartir") }
    }
}
