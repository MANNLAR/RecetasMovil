package com.plateup.app.feature.profile.ui

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
import com.plateup.app.feature.profile.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel, userId: Long) {
    viewModel.cargar(userId)
    val perfil = viewModel.perfil.value
    val altura = remember { mutableStateOf(perfil?.altura?.toString() ?: "") }
    val peso = remember { mutableStateOf(perfil?.peso?.toString() ?: "") }
    val edad = remember { mutableStateOf(perfil?.edad?.toString() ?: "") }
    val sexo = remember { mutableStateOf(perfil?.sexo ?: "") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Datos personales", style = MaterialTheme.typography.titleLarge)
        Text(text = "TODO: foto o logo del usuario")
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = altura.value, onValueChange = { altura.value = it }, label = { Text("Altura (m)") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = peso.value, onValueChange = { peso.value = it }, label = { Text("Peso (kg)") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = edad.value, onValueChange = { edad.value = it }, label = { Text("Edad") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = sexo.value, onValueChange = { sexo.value = it }, label = { Text("Sexo") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Preferencias dietéticas")
        Row { AssistChip(onClick = {}, label = { Text("Sin lácteos") }); Spacer(modifier = Modifier.height(4.dp)); AssistChip(onClick = {}, label = { Text("Sin gluten") }) }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "${'$'}{viewModel.imcTexto.value}")
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(text = "Guardar perfil") {
            val perfilActualizado = com.plateup.app.domain.model.UserProfile(
                userId = userId,
                altura = altura.value.toDoubleOrNull(),
                peso = peso.value.toDoubleOrNull(),
                edad = edad.value.toIntOrNull(),
                sexo = sexo.value,
                nivelActividad = perfil?.nivelActividad,
                alergias = perfil?.alergias,
                preferenciasDieteticas = perfil?.preferenciasDieteticas,
                notasMedicas = perfil?.notasMedicas
            )
            viewModel.guardarPerfil(perfilActualizado)
        }
    }
}
