package com.plateup.app.feature.recipe.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.feature.recipe.detail.viewmodel.RecipeDetailViewModel

@Composable
fun RecipeDetailScreen(viewModel: RecipeDetailViewModel, recipeId: Long) {
    viewModel.cargar(recipeId)
    val receta = viewModel.receta.value
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = receta?.titulo ?: "Detalle de receta", style = MaterialTheme.typography.titleLarge)
        Text(text = "Categoría: ${'$'}{receta?.categoria ?: ""}")
        Text(text = "Ingredientes", style = MaterialTheme.typography.headlineMedium)
        receta?.ingredientes?.forEach { Text("- ${'$'}{it.cantidad} ${'$'}{it.nombre}") }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Preparación", style = MaterialTheme.typography.headlineMedium)
        receta?.pasos?.forEach { Text("Paso ${'$'}{it.orden}: ${'$'}{it.descripcion}") }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Información nutricional: TODO")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Comentarios")
        Text(text = "Botones de acción")
        Button(onClick = { /* TODO Guardar */ }) { Text("Guardar") }
        Button(onClick = { /* TODO Compartir */ }) { Text("Compartir") }
    }
}
