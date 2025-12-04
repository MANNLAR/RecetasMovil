package com.plateup.app.feature.drawer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.plateup.app.R
import com.plateup.app.feature.drawer.model.DrawerItem
import kotlinx.coroutines.launch

@Composable
fun PlateUpDrawer(
    drawerState: DrawerState,
    items: List<DrawerItem>,
    selectedRoute: String?,
    onItemSelected: (String) -> Unit,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp)
            ) {
                DrawerHeader()
                items.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.label) },
                        selected = selectedRoute == item.route,
                        onClick = {
                            scope.launch { drawerState.close() }
                            onItemSelected(item.route)
                        },
                        icon = { PlaceholderIcon() },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }
        }
    ) {
        content()
    }
}

@Composable
private fun DrawerHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Logo PlateUP",
            modifier = Modifier
                .height(96.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Aquí va logo de PlateUP",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun PlaceholderIcon() {
    androidx.compose.material3.Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = null
    )
}

fun defaultDrawerItems(): List<DrawerItem> = listOf(
    DrawerItem(icon = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground), label = "Inicio", route = "home"),
    DrawerItem(icon = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground), label = "Recetas", route = "recipes"),
    DrawerItem(icon = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground), label = "Mis recetas", route = "my_recipes"),
    DrawerItem(icon = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground), label = "Guardados", route = "saved"),
    DrawerItem(icon = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground), label = "Mi refri", route = "fridge"),
    DrawerItem(icon = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground), label = "Planificador", route = "planner"),
    DrawerItem(icon = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground), label = "Comparador", route = "compare"),
    DrawerItem(icon = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground), label = "Recomendaciones", route = "recommendations"),
    DrawerItem(icon = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground), label = "Bot Chef", route = "bot_chef"),
    DrawerItem(icon = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground), label = "Datos personales", route = "profile"),
    DrawerItem(icon = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground), label = "Cerrar sesión", route = "logout")
)
