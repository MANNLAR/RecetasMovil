package com.plateup.app.feature.recipe.detail.ui

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.plateup.app.domain.model.Recipe
import com.plateup.app.feature.recipe.detail.viewmodel.RecipeDetailViewModel

@Composable
fun RecipeDetailScreen(viewModel: RecipeDetailViewModel, recipeId: Long) {
    viewModel.cargar(recipeId)
    val receta = viewModel.receta.value
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    val context = LocalContext.current
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

    Column(
        modifier = Modifier
            .fillMaxSize()
<<<<<<< Updated upstream
<<<<<<< Updated upstream
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.background
                    )
                )
            )
            .verticalScroll(rememberScrollState())
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
            .padding(16.dp)
    ) {
        Text(
            text = receta?.titulo ?: "Detalle de receta",
<<<<<<< Updated upstream
<<<<<<< Updated upstream
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(text = "${receta?.categoria ?: ""} • ${receta?.tiempoMinutos ?: 0} min", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "TODO: imagen vibrante o logo de PlateUP", color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    InfoChip(text = "${receta?.tiempoMinutos ?: 0} min", icon = Icons.Filled.Timer)
                    InfoChip(text = "${receta?.calificacion ?: 0.0} ★")
                    InfoChip(text = "Costo: ${receta?.costo ?: ""}")
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        SectionCard("Ingredientes") {
            receta?.ingredientes?.forEach { Text("• ${it.cantidad} ${it.nombre}") }
        }
        SectionCard("Preparación") {
            receta?.pasos?.forEach { Text("Paso ${it.orden}: ${it.descripcion}") }
        }
        SectionCard("Información nutricional") {
            Text("Calorías: ${receta?.calorias ?: 0} kcal")
            Text("Proteínas: ${receta?.proteinas ?: 0} g")
            Text("Grasas: ${receta?.grasas ?: 0} g")
            Text("Carbohidratos: ${receta?.carbohidratos ?: 0} g")
        }
        SectionCard("Comentarios") {
            Text("TODO: lista de comentarios en español")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { /* TODO Guardar en favoritos */ }) {
                Icon(Icons.Filled.Bookmark, contentDescription = null)
                Spacer(modifier = Modifier.width(6.dp))
                Text("Guardar")
            }
            Button(onClick = {
                receta?.let {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "Prepara ${it.titulo} en PlateUP: ${it.ingredientes.joinToString { ing -> ing.nombre }}")
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Compartir receta"))
                }
            }) {
                Icon(Icons.Filled.IosShare, contentDescription = null)
                Spacer(modifier = Modifier.width(6.dp))
                Text("Compartir")
            }
        }
    }
}

@Composable
private fun SectionCard(titulo: String, contenido: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = titulo, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            contenido()
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
private fun InfoChip(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector? = null) {
    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        icon?.let { Icon(it, contentDescription = null, tint = MaterialTheme.colorScheme.primary) }
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
=======
=======
>>>>>>> Stashed changes
            style = MaterialTheme.typography.titleLarge
        )

        Text(text = "Categoría: ${receta?.categoria ?: ""}")

        Text(
            text = "Ingredientes",
            style = MaterialTheme.typography.headlineMedium
        )

        receta?.ingredientes?.forEach { ingrediente ->
            Text("- ${ingrediente.cantidad} ${ingrediente.nombre}")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Preparación",
            style = MaterialTheme.typography.headlineMedium
        )

        receta?.pasos?.forEach { paso ->
            Text("Paso ${paso.orden}: ${paso.descripcion}")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Información nutricional: TODO")

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Comentarios")
        Text(text = "Botones de acción")

        Button(onClick = { /* TODO Guardar */ }) {
            Text("Guardar")
        }

        Button(onClick = { /* TODO Compartir */ }) {
            Text("Compartir")
        }
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    }
}
