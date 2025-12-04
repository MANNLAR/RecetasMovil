package com.plateup.app.feature.recommendations.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.feature.recommendations.viewmodel.RecommendationsViewModel

@Composable
fun RecommendationsScreen(viewModel: RecommendationsViewModel) {
    val recomendado by viewModel.recomendado.collectAsState()
    val sugerencia by viewModel.sugerencia.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Card { Text("Recomendado para ti: $recomendado", modifier = Modifier.padding(12.dp)) }
        Card { Text("Sugerencia del día: $sugerencia", modifier = Modifier.padding(12.dp)) }
    }
}
