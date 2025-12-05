pluginManagement {
    repositories {
        // 👇 ESTOS SON CLAVE PARA ENCONTRAR com.android.application
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

// Cambia el nombre si tu proyecto se llama distinto
rootProject.name = "RecetasMovil"

include(":app")
