package com.plateup.app.core.navigation

sealed class NavRoutes(val route: String) {
    data object Splash : NavRoutes("splash")
    data object Login : NavRoutes("login")
    data object Register : NavRoutes("register")
    data object Home : NavRoutes("home")
    data object Recipes : NavRoutes("recipes")
    data object MyRecipes : NavRoutes("my_recipes")
    data object Saved : NavRoutes("saved")
    data object Fridge : NavRoutes("fridge")
    data object Planner : NavRoutes("planner")
    data object Compare : NavRoutes("compare")
    data object Recommendations : NavRoutes("recommendations")
    data object BotChef : NavRoutes("bot_chef")
    data object Profile : NavRoutes("profile")
    data object RecipeDetail : NavRoutes("recipe_detail/{id}")
    data object RecipeEdit : NavRoutes("recipe_edit?recipeId={recipeId}")
}
