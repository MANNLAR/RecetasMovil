package com.plateup.app.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plateup.app.core.navigation.NavDestination
import com.plateup.app.core.theme.PlateUpTheme
import com.plateup.app.feature.auth.ui.LoginScreen
import com.plateup.app.feature.auth.ui.RegisterScreen
import com.plateup.app.feature.auth.viewmodel.AuthViewModel
import com.plateup.app.feature.botchef.ui.BotChefScreen
import com.plateup.app.feature.botchef.viewmodel.BotChefViewModel
import com.plateup.app.feature.compare.ui.CompareScreen
import com.plateup.app.feature.compare.viewmodel.CompareViewModel
import com.plateup.app.feature.drawer.ui.DrawerContainer
import com.plateup.app.feature.fridge.ui.FridgeScreen
import com.plateup.app.feature.fridge.viewmodel.FridgeViewModel
import com.plateup.app.feature.home.ui.HomeScreen
import com.plateup.app.feature.home.viewmodel.HomeViewModel
import com.plateup.app.feature.myrecipes.ui.MyRecipesScreen
import com.plateup.app.feature.myrecipes.viewmodel.MyRecipesViewModel
import com.plateup.app.feature.planner.ui.PlannerScreen
import com.plateup.app.feature.planner.viewmodel.PlannerViewModel
import com.plateup.app.feature.profile.ui.ProfileScreen
import com.plateup.app.feature.profile.viewmodel.ProfileViewModel
import com.plateup.app.feature.recipe.detail.ui.RecipeDetailScreen
import com.plateup.app.feature.recipe.detail.viewmodel.RecipeDetailViewModel
import com.plateup.app.feature.recipe.edit.ui.EditRecipeScreen
import com.plateup.app.feature.recipe.edit.viewmodel.EditRecipeViewModel
import com.plateup.app.feature.recipe.list.ui.RecipeListScreen
import com.plateup.app.feature.recipe.list.viewmodel.RecipeListViewModel
import com.plateup.app.feature.recommendations.ui.RecommendationsScreen
import com.plateup.app.feature.recommendations.viewmodel.RecommendationsViewModel
import com.plateup.app.feature.saved.ui.SavedScreen
import com.plateup.app.feature.saved.viewmodel.SavedViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlateUpApp(app: PlateUpApplication) {
    PlateUpTheme {
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        val authViewModel = remember { AuthViewModel(app.registerUser, app.loginUser, app.logoutUser, app.getCurrentUser) }
        val homeViewModel = remember { HomeViewModel() }
        val recipeListViewModel = remember { RecipeListViewModel(app.observarRecetas) }
        val recipeDetailViewModel = remember { RecipeDetailViewModel(app.observarDetalleReceta) }
        val editRecipeViewModel = remember { EditRecipeViewModel() }
        val savedViewModel = remember { SavedViewModel() }
        val myRecipesViewModel = remember { MyRecipesViewModel() }
        val profileViewModel = remember { ProfileViewModel(userId = 1, guardarPerfil = app.guardarPerfil, observarPerfil = app.observarPerfil) }
        val fridgeViewModel = remember { FridgeViewModel(app.agregarIngrediente, app.observarRefri) }
        val compareViewModel = remember { CompareViewModel() }
        val plannerViewModel = remember { PlannerViewModel() }
        val recommendationsViewModel = remember { RecommendationsViewModel() }
        val botChefViewModel = remember { BotChefViewModel() }

        DrawerContainer(
            drawerState = drawerState,
            destinoActual = NavDestination.Inicio,
            onItemClick = { destino ->
                if (destino == NavDestination.Auth) {
                    authViewModel.logout()
                    navController.navigate(NavDestination.Auth.route)
                } else {
                    navController.navigate(destino.route)
                }
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { androidx.compose.material3.Text("PlateUP") },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menú")
                            }
                        }
                    )
                }
            ) { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = NavDestination.Auth.route,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    composable(NavDestination.Auth.route) {
                        if (authViewModel.estado.value.usuarioActual == null) {
                            LoginScreen(
                                viewModel = authViewModel,
                                onRegistro = { navController.navigate("registro") },
                                onLoginExitoso = { navController.navigate(NavDestination.Inicio.route) }
                            )
                        } else {
                            navController.navigate(NavDestination.Inicio.route)
                        }
                    }
                    composable("registro") {
                        RegisterScreen(viewModel = authViewModel) {
                            navController.navigate(NavDestination.Inicio.route)
                        }
                    }
                    composable(NavDestination.Inicio.route) { HomeScreen(homeViewModel) }
                    composable(NavDestination.Recetas.route) { RecipeListScreen(recipeListViewModel) { id ->
                        recipeDetailViewModel.cargar(id)
                        navController.navigate("detalle/$id")
                    } }
                    composable("detalle/{id}") { RecipeDetailScreen(recipeDetailViewModel) }
                    composable("editar") { EditRecipeScreen(editRecipeViewModel) }
                    composable(NavDestination.Guardados.route) { SavedScreen(savedViewModel) }
                    composable(NavDestination.MisRecetas.route) { MyRecipesScreen(myRecipesViewModel) }
                    composable(NavDestination.Perfil.route) { ProfileScreen(profileViewModel) }
                    composable(NavDestination.Refri.route) { FridgeScreen(fridgeViewModel) }
                    composable(NavDestination.Comparador.route) { CompareScreen(compareViewModel) }
                    composable(NavDestination.Planificador.route) { PlannerScreen(plannerViewModel) }
                    composable(NavDestination.Recomendaciones.route) { RecommendationsScreen(recommendationsViewModel) }
                    composable(NavDestination.BotChef.route) { BotChefScreen(botChefViewModel) }
                }
            }
        }
    }
}
