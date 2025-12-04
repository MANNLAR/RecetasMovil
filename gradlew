#!/usr/bin/env sh
set -e
DIR="$(cd "$(dirname "$0")" && pwd)"
WRAPPER_JAR="$DIR/gradle/wrapper/gradle-wrapper.jar"
if [ -f "$WRAPPER_JAR" ]; then
  exec "$DIR/gradle/wrapper/gradle-wrapper" "$@"
fi
if command -v gradle >/dev/null 2>&1; then
  echo "Usando Gradle del sistema porque el wrapper no está disponible." >&2
  exec gradle "$@"
fi
echo "Gradle wrapper JAR no encontrado y no hay Gradle instalado." >&2
exit 1
