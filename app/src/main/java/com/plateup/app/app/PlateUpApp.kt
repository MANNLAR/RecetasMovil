package com.plateup.app.app

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plateup.app.core.navigation.NavRoutes
import com.plateup.app.feature.auth.ui.LoginScreen
import com.plateup.app.feature.auth.ui.RegisterScreen
import com.plateup.app.feature.auth.viewmodel.AuthViewModel
import com.plateup.app.feature.botchef.ui.BotChefScreen
import com.plateup.app.feature.botchef.viewmodel.BotChefViewModel
import com.plateup.app.feature.compare.ui.CompareRecipesScreen
import com.plateup.app.feature.compare.viewmodel.CompareViewModel
import com.plateup.app.feature.drawer.model.DrawerItem
import com.plateup.app.feature.drawer.ui.PlateUpDrawer
import com.plateup.app.feature.drawer.ui.defaultDrawerItems
import com.plateup.app.feature.fridge.ui.FridgeScreen
import com.plateup.app.feature.fridge.viewmodel.FridgeViewModel
import com.plateup.app.feature.home.ui.HomeScreen
import com.plateup.app.feature.home.viewmodel.HomeViewModel
import com.plateup.app.feature.myrecipes.ui.MyRecipesScreen
import com.plateup.app.feature.myrecipes.viewmodel.MyRecipesViewModel
import com.plateup.app.feature.planner.ui.MealPlannerScreen
import com.plateup.app.feature.planner.viewmodel.PlannerViewModel
import com.plateup.app.feature.profile.ui.ProfileScreen
import com.plateup.app.feature.profile.viewmodel.ProfileViewModel
import com.plateup.app.feature.recipe.detail.ui.RecipeDetailScreen
import com.plateup.app.feature.recipe.detail.viewmodel.RecipeDetailViewModel
import com.plateup.app.feature.recipe.edit.ui.EditRecipeScreen
import com.plateup.app.feature.recipe.edit.viewmodel.EditRecipeViewModel
import com.plateup.app.feature.recipe.list.ui.RecipeListScreen
import com.plateup.app.feature.recipe.list.viewmodel.RecipeListViewModel
import com.plateup.app.feature.recommendations.ui.RecommendedScreen
import com.plateup.app.feature.recommendations.viewmodel.RecommendationsViewModel
import com.plateup.app.feature.saved.ui.SavedRecipesScreen
import com.plateup.app.feature.saved.viewmodel.SavedViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlateUpApp(
    authViewModel: AuthViewModel,
    homeViewModel: HomeViewModel,
    recipeListViewModel: RecipeListViewModel,
    recipeDetailViewModel: RecipeDetailViewModel,
    editRecipeViewModel: EditRecipeViewModel,
    savedViewModel: SavedViewModel,
    myRecipesViewModel: MyRecipesViewModel,
    profileViewModel: ProfileViewModel,
    fridgeViewModel: FridgeViewModel,
    compareViewModel: CompareViewModel,
    plannerViewModel: PlannerViewModel,
    recommendationsViewModel: RecommendationsViewModel,
    botChefViewModel: BotChefViewModel,
    currentUserId: Long?
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val drawerItems = defaultDrawerItems()

    PlateUpDrawer(
        drawerState = drawerState,
        items = drawerItems,
        selectedRoute = navController.currentBackStackEntry?.destination?.route,
        onItemSelected = { route ->
            if (route == "logout") {
                authViewModel.cerrarSesion()
                navController.navigate(NavRoutes.Login.route) { popUpTo(0) }
            } else {
                navController.navigate(route)
            }
        }
    ) {
        Scaffold { padding ->
            NavHost(
                navController = navController,
                startDestination = NavRoutes.Login.route,
                modifier = Modifier
            ) {
                composable(NavRoutes.Login.route) {
                    LoginScreen(viewModel = authViewModel, onCreateAccount = {
                        navController.navigate(NavRoutes.Register.route)
                    }) {
                        navController.navigate(NavRoutes.Home.route) { popUpTo(NavRoutes.Login.route) { inclusive = true } }
                    }
                }
                composable(NavRoutes.Register.route) {
                    RegisterScreen(viewModel = authViewModel) {
                        navController.navigate(NavRoutes.Login.route)
                    }
                }
                composable(NavRoutes.Home.route) {
                    HomeScreen(viewModel = homeViewModel) {
                        navController.navigate(NavRoutes.Recipes.route)
                    }
                }
                composable(NavRoutes.Recipes.route) {
                    RecipeListScreen(viewModel = recipeListViewModel) { id ->
                        navController.navigate("recipe_detail/${'$'}id")
                    }
                }
                composable(NavRoutes.MyRecipes.route) { MyRecipesScreen(viewModel = myRecipesViewModel) }
                composable(NavRoutes.Saved.route) { SavedRecipesScreen(viewModel = savedViewModel) }
                composable(NavRoutes.Fridge.route) { FridgeScreen(viewModel = fridgeViewModel) }
                composable(NavRoutes.Planner.route) { MealPlannerScreen(viewModel = plannerViewModel) }
                composable(NavRoutes.Compare.route) { CompareRecipesScreen(viewModel = compareViewModel) }
                composable(NavRoutes.Recommendations.route) { RecommendedScreen(viewModel = recommendationsViewModel) }
                composable(NavRoutes.BotChef.route) { BotChefScreen(viewModel = botChefViewModel) }
                composable(NavRoutes.Profile.route) {
                    ProfileScreen(viewModel = profileViewModel, userId = currentUserId ?: 1L)
                }
                composable(NavRoutes.RecipeDetail.route) { backStackEntry ->
                    val idArg = backStackEntry.arguments?.getString("id")?.toLongOrNull() ?: 0L
                    RecipeDetailScreen(viewModel = recipeDetailViewModel, recipeId = idArg)
                }
                composable(NavRoutes.RecipeEdit.route) { EditRecipeScreen(viewModel = editRecipeViewModel) }
            }
        }
    }
}
