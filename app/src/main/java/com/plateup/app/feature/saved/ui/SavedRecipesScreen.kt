package com.plateup.app.feature.saved.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.feature.saved.viewmodel.SavedViewModel

@Composable
fun SavedRecipesScreen(viewModel: SavedViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Guardados", style = MaterialTheme.typography.titleLarge)
        Text(text = viewModel.mensaje.value)
    }
}
