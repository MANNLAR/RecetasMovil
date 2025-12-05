package com.plateup.app.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.plateup.app.core.theme.PlateUpTheme
import com.plateup.app.core.util.SessionManager
import com.plateup.app.data.local.db.AppDatabase
import com.plateup.app.data.repository.AuthRepository
import com.plateup.app.data.repository.RecipeRepository
import com.plateup.app.domain.usecase.auth.GetCurrentUser
import com.plateup.app.domain.usecase.auth.LoginUser
import com.plateup.app.domain.usecase.auth.LogoutUser
import com.plateup.app.domain.usecase.auth.RegisterUser
import com.plateup.app.domain.usecase.profile.ObserveProfile
import com.plateup.app.domain.usecase.profile.UpdateProfile
import com.plateup.app.domain.usecase.recipe.ObserveRecipe
import com.plateup.app.domain.usecase.recipe.ObserveRecipes
import com.plateup.app.domain.usecase.recipe.SearchRecipes
import com.plateup.app.feature.auth.viewmodel.AuthViewModel
import com.plateup.app.feature.botchef.viewmodel.BotChefViewModel
import com.plateup.app.feature.compare.viewmodel.CompareViewModel
import com.plateup.app.feature.fridge.viewmodel.FridgeViewModel
import com.plateup.app.feature.home.viewmodel.HomeViewModel
import com.plateup.app.feature.myrecipes.viewmodel.MyRecipesViewModel
import com.plateup.app.feature.planner.viewmodel.PlannerViewModel
import com.plateup.app.feature.profile.viewmodel.ProfileViewModel
import com.plateup.app.feature.recipe.detail.viewmodel.RecipeDetailViewModel
import com.plateup.app.feature.recipe.edit.viewmodel.EditRecipeViewModel
import com.plateup.app.feature.recipe.list.viewmodel.RecipeListViewModel
import com.plateup.app.feature.recommendations.viewmodel.RecommendationsViewModel
import com.plateup.app.feature.saved.viewmodel.SavedViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlateUpTheme {
                val database = remember { AppDatabase.build(applicationContext) }
                val sessionManager = remember { SessionManager(applicationContext) }
                val authRepository = remember { AuthRepository(database.authDao(), sessionManager) }
                val recipeRepository = remember { RecipeRepository(database.recipeDao()) }

                val authViewModel = remember {
                    AuthViewModel(
                        registerUser = RegisterUser(authRepository),
                        loginUser = LoginUser(authRepository),
                        logoutUser = LogoutUser(authRepository),
                        getCurrentUser = GetCurrentUser(authRepository)
                    )
                }
                val homeViewModel = remember { HomeViewModel(ObserveRecipes(recipeRepository)) }
                val recipeListViewModel = remember {
                    RecipeListViewModel(SearchRecipes(recipeRepository))
                }
                val recipeDetailViewModel = remember { RecipeDetailViewModel(ObserveRecipe(recipeRepository)) }
                val editRecipeViewModel = remember { EditRecipeViewModel() }
                val savedViewModel = remember { SavedViewModel() }
                val myRecipesViewModel = remember { MyRecipesViewModel() }
                val profileViewModel = remember {
                    ProfileViewModel(
                        observeProfile = ObserveProfile(authRepository),
                        updateProfile = UpdateProfile(authRepository)
                    )
                }
                val fridgeViewModel = remember { FridgeViewModel() }
                val compareViewModel = remember { CompareViewModel() }
                val plannerViewModel = remember { PlannerViewModel() }
                val recommendationsViewModel = remember { RecommendationsViewModel() }
                val botChefViewModel = remember { BotChefViewModel() }

                // 👇 Observar el StateFlow dentro de la composición
                val currentUser by authViewModel.usuarioActual.collectAsState()

                PlateUpApp(
                    authViewModel = authViewModel,
                    homeViewModel = homeViewModel,
                    recipeListViewModel = recipeListViewModel,
                    recipeDetailViewModel = recipeDetailViewModel,
                    editRecipeViewModel = editRecipeViewModel,
                    savedViewModel = savedViewModel,
                    myRecipesViewModel = myRecipesViewModel,
                    profileViewModel = profileViewModel,
                    fridgeViewModel = fridgeViewModel,
                    compareViewModel = compareViewModel,
                    plannerViewModel = plannerViewModel,
                    recommendationsViewModel = recommendationsViewModel,
                    botChefViewModel = botChefViewModel,
                    currentUserId = currentUser?.id
                )
            }
        }
    }
}
