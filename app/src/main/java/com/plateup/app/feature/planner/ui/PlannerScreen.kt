package com.plateup.app.feature.planner.ui

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
import com.plateup.app.core.ui.ChipOpcion
import com.plateup.app.feature.planner.viewmodel.PlannerViewModel

@Composable
fun PlannerScreen(viewModel: PlannerViewModel) {
    val plan by viewModel.plan.collectAsState()
    val dias = remember { mutableStateOf("7") }
    val comidas = remember { mutableStateOf("3") }
    val enfoque = remember { mutableStateOf("Saludable") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        TextField(value = dias.value, onValueChange = { dias.value = it }, label = { Text("Días") }, modifier = Modifier.fillMaxWidth())
        TextField(value = comidas.value, onValueChange = { comidas.value = it }, label = { Text("Comidas por día") }, modifier = Modifier.fillMaxWidth())
        ChipOpcion(texto = "Rápido", seleccionado = enfoque.value == "Rápido", onClick = { enfoque.value = "Rápido" })
        ChipOpcion(texto = "Barato", seleccionado = enfoque.value == "Barato", onClick = { enfoque.value = "Barato" })
        ChipOpcion(texto = "Saludable", seleccionado = enfoque.value == "Saludable", onClick = { enfoque.value = "Saludable" })
        ChipOpcion(texto = "Alta proteína", seleccionado = enfoque.value == "Alta proteína", onClick = { enfoque.value = "Alta proteína" })
        Button(onClick = { viewModel.generar(dias.value.toIntOrNull() ?: 0, comidas.value.toIntOrNull() ?: 0, enfoque.value) }) {
            Text("Generar plan")
        }
        Text("Plan semanal: $plan")
    }
}
