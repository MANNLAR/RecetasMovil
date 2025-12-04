package com.plateup.app.feature.drawer.model

import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerItem(
    val icon: ImageVector,
    val label: String,
    val route: String
)
