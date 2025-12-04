package com.plateup.app.feature.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.domain.model.Recipe
import com.plateup.app.feature.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel, onNavigateRecetas: () -> Unit) {
    val recetas = viewModel.recetas.value
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Inicio", style = MaterialTheme.typography.titleLarge)
        Text(text = "Destacados para ti", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow {
            items(recetas) { receta ->
                HomeRecipeCard(receta)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Explora todas las recetas",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onNavigateRecetas() },
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun HomeRecipeCard(receta: Recipe) {
    Card(
        modifier = Modifier
            .padding(end = 12.dp)
            .fillMaxWidth(0.8f),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = receta.titulo, style = MaterialTheme.typography.titleLarge)
            Text(text = "Categoría: ${'$'}{receta.categoria}")
            Text(text = "Tiempo: ${'$'}{receta.tiempoMinutos} minutos")
            Text(text = "Costo: ${'$'}{receta.costo}")
        }
    }
}
