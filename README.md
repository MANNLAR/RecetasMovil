# RecetasMovil

Proyecto Android (Jetpack Compose + Material 3) para la app **PlateUP** totalmente en español.

## Gradle sin binarios versionados

No se incluye `gradle/wrapper/gradle-wrapper.jar` para evitar binarios en el repositorio. Los scripts `./gradlew` y `gradlew.bat` descargan automáticamente el wrapper desde la URL definida en `gradle/wrapper/gradle-wrapper.properties` y extraen el JAR antes de la primera ejecución. Solo necesitas contar con `curl` o `wget` (y `unzip` en Linux/macOS, PowerShell en Windows) disponibles en tu entorno.

### Importar en Android Studio
1. Abre el proyecto desde la carpeta raíz.
2. Android Studio ejecutará `gradlew` y descargará el wrapper si falta el JAR.
3. Sincroniza el proyecto y compila normalmente.
