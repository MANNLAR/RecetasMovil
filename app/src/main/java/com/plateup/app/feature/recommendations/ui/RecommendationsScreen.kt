package com.plateup.app.feature.recommendations.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.feature.recommendations.viewmodel.RecommendationsViewModel

@Composable
fun RecommendedScreen(viewModel: RecommendationsViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Recomendaciones", style = MaterialTheme.typography.titleLarge)
        Text(text = "Recomendado para ti")
        Text(text = viewModel.recomendado.value)
        Text(text = "Sugerencia del día")
        Text(text = "Prueba la ensalada fresca con ingredientes verdes")
    }
}
