package com.plateup.app.core.navigation

enum class NavDestination(val route: String, val label: String) {
    Inicio("inicio", "Inicio"),
    Recetas("recetas", "Recetas"),
    MisRecetas("mis_recetas", "Mis recetas"),
    Guardados("guardados", "Guardados"),
    Refri("refri", "Mi refri"),
    Planificador("planificador", "Planificador"),
    Comparador("comparador", "Comparador"),
    Recomendaciones("recomendaciones", "Recomendaciones"),
    BotChef("bot_chef", "Bot Chef"),
    Perfil("perfil", "Datos personales"),
    Auth("auth", "Autenticación")
}
