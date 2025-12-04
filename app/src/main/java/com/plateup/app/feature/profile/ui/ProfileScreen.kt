package com.plateup.app.feature.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.domain.model.UserProfile
import com.plateup.app.feature.profile.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val altura = remember { mutableStateOf("") }
    val peso = remember { mutableStateOf("") }
    val alergia = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("TODO: foto o avatar del usuario", style = MaterialTheme.typography.titleMedium)
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Datos personales", style = MaterialTheme.typography.titleLarge)
                TextField(value = altura.value, onValueChange = { altura.value = it }, label = { Text("Altura (cm)") })
                TextField(value = peso.value, onValueChange = { peso.value = it }, label = { Text("Peso (kg)") })
                TextField(value = alergia.value, onValueChange = { alergia.value = it }, label = { Text("Alergias (separadas por coma)") })
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    val perfil = UserProfile(
                        userId = 1,
                        altura = altura.value.toFloatOrNull(),
                        peso = peso.value.toFloatOrNull(),
                        edad = null,
                        sexo = null,
                        nivelActividad = null,
                        alergias = alergia.value.split(","),
                        preferenciasDieteticas = emptyList(),
                        notasMedicas = null
                    )
                    viewModel.guardar(perfil)
                }) { Text("Guardar perfil") }
            }
        }
    }
}
