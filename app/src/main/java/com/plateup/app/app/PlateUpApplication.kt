package com.plateup.app.app

import android.app.Application
import com.plateup.app.data.local.db.PlateUpDatabase
import com.plateup.app.data.repository.AuthRepository
import com.plateup.app.data.repository.FridgeRepository
import com.plateup.app.data.repository.ProfileRepository
import com.plateup.app.data.repository.RecipeRepository
import com.plateup.app.domain.usecase.auth.GetCurrentUser
import com.plateup.app.domain.usecase.auth.LoginUser
import com.plateup.app.domain.usecase.auth.LogoutUser
import com.plateup.app.domain.usecase.auth.RegisterUser
import com.plateup.app.domain.usecase.fridge.AgregarIngrediente
import com.plateup.app.domain.usecase.fridge.ObservarRefri
import com.plateup.app.domain.usecase.profile.GuardarPerfil
import com.plateup.app.domain.usecase.profile.ObservarPerfil
import com.plateup.app.domain.usecase.recipe.ObservarDetalleReceta
import com.plateup.app.domain.usecase.recipe.ObservarRecetas

class PlateUpApplication : Application() {
    lateinit var authRepository: AuthRepository
    lateinit var profileRepository: ProfileRepository
    lateinit var recipeRepository: RecipeRepository
    lateinit var fridgeRepository: FridgeRepository

    lateinit var registerUser: RegisterUser
    lateinit var loginUser: LoginUser
    lateinit var logoutUser: LogoutUser
    lateinit var getCurrentUser: GetCurrentUser
    lateinit var guardarPerfil: GuardarPerfil
    lateinit var observarPerfil: ObservarPerfil
    lateinit var observarRecetas: ObservarRecetas
    lateinit var observarDetalleReceta: ObservarDetalleReceta
    lateinit var agregarIngrediente: AgregarIngrediente
    lateinit var observarRefri: ObservarRefri

    override fun onCreate() {
        super.onCreate()
        val db = PlateUpDatabase.obtenerInstancia(this)
        authRepository = AuthRepository(db.authDao())
        profileRepository = ProfileRepository(db.profileDao())
        recipeRepository = RecipeRepository(db.recipeDao())
        fridgeRepository = FridgeRepository(db.fridgeDao())

        registerUser = RegisterUser(authRepository)
        loginUser = LoginUser(authRepository)
        logoutUser = LogoutUser(authRepository)
        getCurrentUser = GetCurrentUser(authRepository)
        guardarPerfil = GuardarPerfil(profileRepository)
        observarPerfil = ObservarPerfil(profileRepository)
        observarRecetas = ObservarRecetas(recipeRepository)
        observarDetalleReceta = ObservarDetalleReceta(recipeRepository)
        agregarIngrediente = AgregarIngrediente(fridgeRepository)
        observarRefri = ObservarRefri(fridgeRepository)
    }
}
