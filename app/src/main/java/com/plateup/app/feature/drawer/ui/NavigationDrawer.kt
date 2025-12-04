package com.plateup.app.feature.drawer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlaylistAddCheck
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plateup.app.core.navigation.NavDestination
import com.plateup.app.feature.drawer.model.DrawerItem
import kotlinx.coroutines.launch

@Composable
fun DrawerContainer(
    drawerState: DrawerState,
    destinoActual: NavDestination,
    onItemClick: (NavDestination) -> Unit,
    contenido: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val items = listOf(
        DrawerItem(NavDestination.Inicio, Icons.Default.Home, "Inicio"),
        DrawerItem(NavDestination.Recetas, Icons.Default.MenuBook, "Recetas"),
        DrawerItem(NavDestination.MisRecetas, Icons.Default.PlaylistAddCheck, "Mis recetas"),
        DrawerItem(NavDestination.Guardados, Icons.Default.Bookmark, "Guardados"),
        DrawerItem(NavDestination.Refri, Icons.Default.Restaurant, "Mi refri"),
        DrawerItem(NavDestination.Planificador, Icons.Default.List, "Planificador"),
        DrawerItem(NavDestination.Comparador, Icons.Default.Settings, "Comparador"),
        DrawerItem(NavDestination.Recomendaciones, Icons.Default.Star, "Recomendaciones"),
        DrawerItem(NavDestination.BotChef, Icons.Default.Chat, "Bot Chef"),
        DrawerItem(NavDestination.Perfil, Icons.Default.AccountCircle, "Datos personales"),
        DrawerItem(NavDestination.Auth, Icons.Default.Close, "Cerrar sesión")
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Aquí va logo de PlateUP",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    Text(text = "Menú principal", style = MaterialTheme.typography.labelMedium)
                    items.forEach { item ->
                        NavigationDrawerItem(
                            label = { Text(item.etiqueta) },
                            selected = destinoActual == item.destino,
                            onClick = {
                                scope.launch { drawerState.close() }
                                onItemClick(item.destino)
                            },
                            icon = { Icon(item.icono, contentDescription = item.etiqueta) },
                            colors = NavigationDrawerItemDefaults.colors()
                        )
                    }
                }
            }
        }
    ) {
        contenido()
    }
}
