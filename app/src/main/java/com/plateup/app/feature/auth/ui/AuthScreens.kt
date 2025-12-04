package com.plateup.app.feature.auth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.core.ui.BotonPrimario
import com.plateup.app.core.ui.BotonSecundario
import com.plateup.app.feature.auth.viewmodel.AuthViewModel

@Composable
fun LoginScreen(viewModel: AuthViewModel, onRegistro: () -> Unit, onLoginExitoso: () -> Unit) {
    val estado = viewModel.estado
    val usuario = remember { mutableStateOf("") }
    val contrasena = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "TODO: logo PlateUP", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(24.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(text = "Iniciar sesión", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    value = usuario.value,
                    onValueChange = { usuario.value = it },
                    label = { Text("Usuario") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = contrasena.value,
                    onValueChange = { contrasena.value = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth()
                )
                BotonPrimario(texto = "Iniciar sesión") {
                    viewModel.login(usuario.value, contrasena.value)
                    if (viewModel.estado.value.error == null) onLoginExitoso()
                }
                BotonSecundario(texto = "Crear cuenta") { onRegistro() }
                estado.value.error?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
            }
        }
    }
}

@Composable
fun RegisterScreen(viewModel: AuthViewModel, onRegistroExitoso: () -> Unit) {
    val usuario = remember { mutableStateOf("") }
    val contrasena = remember { mutableStateOf("") }
    val repetir = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "TODO: logo PlateUP", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(24.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(text = "Crear cuenta", style = MaterialTheme.typography.titleMedium)
                OutlinedTextField(
                    value = usuario.value,
                    onValueChange = { usuario.value = it },
                    label = { Text("Usuario") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = contrasena.value,
                    onValueChange = { contrasena.value = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = repetir.value,
                    onValueChange = { repetir.value = it },
                    label = { Text("Repetir contraseña") },
                    modifier = Modifier.fillMaxWidth()
                )
                BotonPrimario(texto = "Registrarse") {
                    if (contrasena.value == repetir.value) {
                        viewModel.registrar(usuario.value, contrasena.value)
                        onRegistroExitoso()
                    }
                }
                viewModel.estado.value.error?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
            }
        }
    }
}
