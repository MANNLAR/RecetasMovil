package com.plateup.app.feature.planner.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.core.ui.PrimaryButton
import com.plateup.app.feature.planner.viewmodel.PlannerViewModel

@Composable
fun MealPlannerScreen(viewModel: PlannerViewModel) {
    val dias = remember { mutableStateOf("7") }
    val comidas = remember { mutableStateOf("3") }
    val etiqueta = remember { mutableStateOf("Saludable") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Planificador", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(value = dias.value, onValueChange = { dias.value = it }, label = { Text("Días") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = comidas.value, onValueChange = { comidas.value = it }, label = { Text("Comidas por día") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
            AssistChip(onClick = { etiqueta.value = "Rápido" }, label = { Text("Rápido") })
            AssistChip(onClick = { etiqueta.value = "Barato" }, label = { Text("Barato") })
            AssistChip(onClick = { etiqueta.value = "Saludable" }, label = { Text("Saludable") })
            AssistChip(onClick = { etiqueta.value = "Alta proteína" }, label = { Text("Alta proteína") })
        }
        Spacer(modifier = Modifier.height(12.dp))
        PrimaryButton(text = "Generar plan") {
            viewModel.generar(dias.value.toIntOrNull() ?: 7, comidas.value.toIntOrNull() ?: 3, etiqueta.value)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = viewModel.plan.value)
    }
}
