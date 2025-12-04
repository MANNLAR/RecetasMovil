package com.plateup.app.feature.recipe.list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.core.ui.ChipOpcion
import com.plateup.app.domain.model.Recipe
import com.plateup.app.feature.recipe.list.viewmodel.RecipeListViewModel

@Composable
fun RecipeListScreen(viewModel: RecipeListViewModel, onSeleccion: (Long) -> Unit) {
    val estado by viewModel.estado.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = estado.consulta,
            onValueChange = { viewModel.actualizarConsulta(it) },
            label = { Text("Buscar recetas…") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ChipOpcion("Rápido", estado.filtroCategoria == "Rápido", { viewModel.filtrarCategoria("Rápido") })
            ChipOpcion("Saludable", estado.filtroCategoria == "Saludable", { viewModel.filtrarCategoria("Saludable") })
            ChipOpcion("Económico", estado.filtroCategoria == "Económico", { viewModel.filtrarCategoria("Económico") })
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(estado.recetas.filter { it.titulo.contains(estado.consulta, ignoreCase = true) }) { receta ->
                RecetaItem(receta = receta, onClick = { onSeleccion(receta.id) })
            }
        }
    }
}

@Composable
private fun RecetaItem(receta: Recipe, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = receta.titulo, style = MaterialTheme.typography.titleMedium)
            Text(text = "Categoría: ${receta.categoria}")
            Text(text = "Tiempo: ${receta.tiempoMinutos} minutos")
            Text(text = "Costo: ${receta.costo}")
            Text(text = "Calificación: ${receta.calificacion}")
            Text(text = "TODO: imagen o logo de receta", color = MaterialTheme.colorScheme.primary)
        }
    }
}
