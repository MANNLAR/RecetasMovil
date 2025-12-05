package com.plateup.app.feature.recipe.list.ui

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.plateup.app.domain.model.Recipe
import com.plateup.app.feature.recipe.list.viewmodel.RecipeListViewModel

@Composable
fun RecipeListScreen(viewModel: RecipeListViewModel, onRecipeSelected: (Long) -> Unit) {
    val recetas = viewModel.recetas.value
    val queryState = remember { mutableStateOf("") }
    val ingredientesFavoritos = remember { mutableStateOf("") }
    val ingredientesExcluidos = remember { mutableStateOf("") }
    val ratingMin = remember { mutableStateOf(3f) }
    val tiempoMax = remember { mutableStateOf(90f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                        MaterialTheme.colorScheme.background
                    )
                )
            )
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Recetas", style = MaterialTheme.typography.headlineMedium)
        Text(
            text = "Encuentra opciones por ingredientes, tiempo y presupuesto",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = queryState.value,
            onValueChange = {
                queryState.value = it
                viewModel.actualizarBusqueda(it)
            },
            label = { Text("Buscar recetas…") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(focusedIndicatorColor = MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.height(8.dp))
        FilterCard(
            ingredientesFavoritos.value,
            ingredientesExcluidos.value,
            ratingMin.value,
            tiempoMax.value,
            onFavoritosChange = {
                ingredientesFavoritos.value = it
                viewModel.actualizarIngredientesFavoritos(it)
            },
            onExcluidosChange = {
                ingredientesExcluidos.value = it
                viewModel.actualizarIngredientesExcluidos(it)
            },
            onRatingChange = {
                ratingMin.value = it
                viewModel.actualizarMinCalificacion(it.toDouble())
            },
            onTiempoChange = {
                tiempoMax.value = it
                viewModel.actualizarMaxTiempo(it.toInt())
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(recetas) { receta ->
                RecipeItem(receta, onRecipeSelected)
            }
        }
    }
}

@Composable
private fun FilterCard(
    ingredientesFavoritos: String,
    ingredientesExcluidos: String,
    ratingMin: Float,
    tiempoMax: Float,
    onFavoritosChange: (String) -> Unit,
    onExcluidosChange: (String) -> Unit,
    onRatingChange: (Float) -> Unit,
    onTiempoChange: (Float) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(Icons.Filled.FilterAlt, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Text(text = "Buscador avanzado", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = ingredientesFavoritos,
                onValueChange = onFavoritosChange,
                label = { Text("Ingredientes deseados (separar por coma)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = ingredientesExcluidos,
                onValueChange = onExcluidosChange,
                label = { Text("Ingredientes excluidos") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Calificación mínima: ${ratingMin.toInt()} ★", style = MaterialTheme.typography.bodyMedium)
            Slider(
                value = ratingMin,
                onValueChange = onRatingChange,
                valueRange = 0f..5f,
                colors = SliderDefaults.colors(thumbColor = MaterialTheme.colorScheme.primary)
            )
            Text(text = "Tiempo máximo: ${tiempoMax.toInt()} minutos", style = MaterialTheme.typography.bodyMedium)
            Slider(
                value = tiempoMax,
                onValueChange = onTiempoChange,
                valueRange = 10f..180f,
                colors = SliderDefaults.colors(activeTrackColor = MaterialTheme.colorScheme.secondary)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AssistChip(onClick = { onTiempoChange(30f) }, label = { Text("Rápido") })
                AssistChip(onClick = { onRatingChange(4f) }, label = { Text("Muy valorado") })
                AssistChip(onClick = { onExcluidosChange("gluten") }, label = { Text("Sin gluten") })
            }
        }
    }
}

@Composable
private fun RecipeItem(receta: Recipe, onRecipeSelected: (Long) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRecipeSelected(receta.id) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Column {
                    Text(text = receta.titulo, style = MaterialTheme.typography.titleMedium)
                    Text(text = "${receta.categoria} | ${receta.costo}", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                InfoBadge(text = "${receta.tiempoMinutos} min", icon = Icons.Filled.Timer)
                InfoBadge(text = "${receta.calificacion} ★", icon = Icons.Filled.CheckCircle)
                InfoBadge(text = "${receta.ingredientes.size} ingredientes")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Ingredientes clave: ${receta.ingredientes.take(3).joinToString { it.nombre }}")
            Text(text = "TODO: miniatura inspiradora", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        }
    }
}

@Composable
private fun InfoBadge(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector? = null) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        icon?.let { Icon(it, contentDescription = null, tint = MaterialTheme.colorScheme.secondary) }
        Text(text = text, style = MaterialTheme.typography.bodySmall)
    }
}
