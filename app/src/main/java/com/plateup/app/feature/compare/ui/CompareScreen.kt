package com.plateup.app.feature.compare.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.feature.compare.viewmodel.CompareViewModel

@Composable
fun CompareRecipesScreen(viewModel: CompareViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Comparador", style = MaterialTheme.typography.titleLarge)
        Text(text = "Selecciona Receta A y Receta B")
        Text(text = viewModel.resumen.value)
        Text(text = "Calorías, proteínas, grasas y carbohidratos")
    }
}
