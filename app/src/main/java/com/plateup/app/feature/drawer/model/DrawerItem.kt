package com.plateup.app.feature.drawer.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.plateup.app.core.navigation.NavDestination

data class DrawerItem(
    val destino: NavDestination,
    val icono: ImageVector,
    val etiqueta: String
)
