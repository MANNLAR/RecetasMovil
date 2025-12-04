package com.plateup.app.feature.myrecipes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.feature.myrecipes.viewmodel.MyRecipesViewModel

@Composable
fun MyRecipesScreen(viewModel: MyRecipesViewModel) {
    val mensaje by viewModel.mensaje.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Mis recetas")
        Text(mensaje)
    }
}
