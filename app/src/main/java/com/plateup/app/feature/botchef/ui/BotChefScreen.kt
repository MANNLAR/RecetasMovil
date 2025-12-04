package com.plateup.app.feature.botchef.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.feature.botchef.viewmodel.BotChefViewModel

@Composable
fun BotChefScreen(viewModel: BotChefViewModel) {
    val mensajes by viewModel.mensajes.collectAsState()
    val entrada = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(mensajes) { msg ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = if (msg.fromUser) "Tú: ${msg.mensaje}" else "Bot: ${msg.mensaje}",
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = entrada.value,
                onValueChange = { entrada.value = it },
                label = { Text("Escribe tu mensaje…") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = { viewModel.enviar(entrada.value); entrada.value = "" }) {
                Text("Enviar")
            }
        }
    }
}
