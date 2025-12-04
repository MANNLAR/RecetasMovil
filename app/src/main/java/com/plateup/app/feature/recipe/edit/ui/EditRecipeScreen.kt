package com.plateup.app.feature.recipe.edit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.feature.recipe.edit.viewmodel.EditRecipeViewModel

@Composable
fun EditRecipeScreen(viewModel: EditRecipeViewModel) {
    val estado by viewModel.estado.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = "TODO: colocar imagen de receta", style = MaterialTheme.typography.titleMedium)
        TextField(value = estado.titulo, onValueChange = viewModel::actualizarTitulo, label = { Text("Título de la receta") }, modifier = Modifier.fillMaxWidth())
        TextField(value = estado.categoria, onValueChange = viewModel::actualizarCategoria, label = { Text("Categoría") }, modifier = Modifier.fillMaxWidth())
        TextField(value = estado.tiempo.toString(), onValueChange = { viewModel.actualizarTiempo(it.toIntOrNull() ?: 0) }, label = { Text("Tiempo de preparación") })
        TextField(value = estado.costo, onValueChange = viewModel::actualizarCosto, label = { Text("Costo") })
        TextField(value = estado.descripcion, onValueChange = viewModel::actualizarDescripcion, label = { Text("Descripción") })
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { }) { Text("Guardar cambios") }
    }
}
