@ECHO OFF
SET DIR=%~dp0
SET WRAPPER_JAR=%DIR%gradle\wrapper\gradle-wrapper.jar
IF NOT EXIST "%WRAPPER_JAR%" (
  ECHO Gradle wrapper JAR no encontrado. Asegura la configuración del proyecto.
  EXIT /B 1
)
"%DIR%gradle\wrapper\gradle-wrapper" %*
