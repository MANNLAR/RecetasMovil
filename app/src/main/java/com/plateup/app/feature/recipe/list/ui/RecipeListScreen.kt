package com.plateup.app.feature.recipe.list.ui

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.domain.model.Recipe
import com.plateup.app.feature.recipe.list.viewmodel.RecipeListViewModel

@Composable
fun RecipeListScreen(viewModel: RecipeListViewModel, onRecipeSelected: (Long) -> Unit) {
    val recetas = viewModel.recetas.value
    val queryState = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Recetas", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(
            value = queryState.value,
            onValueChange = {
                queryState.value = it
                viewModel.actualizarBusqueda(it)
            },
            label = { Text("Buscar recetas…") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        FilterRow()
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(recetas) { receta ->
                RecipeItem(receta, onRecipeSelected)
            }
        }
    }
}

@Composable
private fun FilterRow() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        AssistChip(onClick = {}, label = { Text("Ingredientes deseados") })
        AssistChip(onClick = {}, label = { Text("Tiempo") })
        AssistChip(onClick = {}, label = { Text("Costo") })
        AssistChip(onClick = {}, label = { Text("Calificación mínima") })
    }
}

@Composable
private fun RecipeItem(receta: Recipe, onRecipeSelected: (Long) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onRecipeSelected(receta.id) }
    ) {
        Text(text = receta.titulo, style = MaterialTheme.typography.titleLarge)
        Text(text = "Categoría: ${'$'}{receta.categoria}")
        Text(text = "Tiempo: ${'$'}{receta.tiempoMinutos} minutos | Costo ${'$'}{receta.costo}")
        Text(text = "Calificación ${'$'}{receta.calificacion}")
        Text(text = "TODO: imagen o logo")
    }
}
