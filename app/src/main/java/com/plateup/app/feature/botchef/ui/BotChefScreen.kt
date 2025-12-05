package com.plateup.app.feature.botchef.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.core.ui.PrimaryButton
import com.plateup.app.feature.botchef.viewmodel.BotChefViewModel
import com.plateup.app.feature.botchef.viewmodel.Mensaje

@Composable
fun BotChefScreen(viewModel: BotChefViewModel) {
    val mensaje = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Bot Chef", style = MaterialTheme.typography.titleLarge)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.mensajes.value) { msg ->
                Surface(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = msg.autor, style = MaterialTheme.typography.labelMedium)
                        Text(text = msg.contenido)
                    }
                }
            }
        }
        OutlinedTextField(
            value = mensaje.value,
            onValueChange = { mensaje.value = it },
            label = { Text("Escribe tu mensaje…") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        PrimaryButton(text = "Enviar") {
            viewModel.enviar(mensaje.value)
            mensaje.value = ""
        }
    }
}
