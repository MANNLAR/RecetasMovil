package com.plateup.app.feature.recipe.edit.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.core.ui.PrimaryButton
import com.plateup.app.feature.recipe.edit.viewmodel.EditRecipeViewModel

@Composable
fun EditRecipeScreen(viewModel: EditRecipeViewModel) {
    val titulo = viewModel.titulo.value
    val descripcion = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Crear receta", style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
        Text(text = "TODO: espacio para imagen")
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = titulo,
            onValueChange = { viewModel.actualizarTitulo(it) },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = descripcion.value,
            onValueChange = { descripcion.value = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(text = "Guardar receta") { /* TODO persistir */ }
    }
}
