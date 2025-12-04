package com.plateup.app.feature.compare.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.feature.compare.viewmodel.CompareViewModel

@Composable
fun CompareScreen(viewModel: CompareViewModel) {
    val estado by viewModel.estado.collectAsState()
    val a = remember { mutableStateOf("") }
    val b = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        TextField(value = a.value, onValueChange = { a.value = it }, label = { Text("Receta A") }, modifier = Modifier.fillMaxWidth())
        TextField(value = b.value, onValueChange = { b.value = it }, label = { Text("Receta B") }, modifier = Modifier.fillMaxWidth())
        Button(onClick = { viewModel.actualizar(a.value, b.value) }) { Text("Comparar") }
        Text("Resumen nutricional: ${estado.resumen}")
    }
}
