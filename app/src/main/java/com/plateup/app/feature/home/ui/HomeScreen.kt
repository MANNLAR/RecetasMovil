package com.plateup.app.feature.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.core.ui.EncabezadoSeccion
import com.plateup.app.feature.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val consulta = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "TODO: banner destacado", style = MaterialTheme.typography.titleLarge)
        TextField(
            value = consulta.value,
            onValueChange = { consulta.value = it },
            label = { Text("Buscar recetas…") },
            modifier = Modifier.fillMaxWidth()
        )
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                EncabezadoSeccion(titulo = "Categorías")
                Text("Saludable · Rápido · Vegetariano · Alto en proteína")
            }
        }
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                EncabezadoSeccion(titulo = "Destacados")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Prueba nuestras recetas recomendadas en español")
            }
        }
    }
}
