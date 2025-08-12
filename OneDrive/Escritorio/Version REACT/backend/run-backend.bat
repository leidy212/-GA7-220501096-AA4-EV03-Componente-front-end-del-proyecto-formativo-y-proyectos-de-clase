@echo off
REM Script para compilar y ejecutar el backend Spring Boot
REM Versión REACT - Lady's Spa API

echo 🌸 Lady's Spa - Backend API Spring Boot
echo ========================================

REM Cambiar al directorio del backend
cd /d "%~dp0"

echo 📁 Directorio actual: %cd%

REM Verificar que Maven está instalado
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo ❌ Error: Maven no está instalado o no está en el PATH
    pause
    exit /b 1
)

echo ✅ Maven encontrado

REM Limpiar y compilar
echo 🧹 Limpiando proyecto...
call mvn clean

echo 🔨 Compilando proyecto...
call mvn compile

if %errorlevel% equ 0 (
    echo ✅ Compilación exitosa
    echo.
    echo 🚀 Iniciando servidor Spring Boot en puerto 8082...
    echo 🌐 API disponible en: http://localhost:8082
    echo 📡 Endpoints principales:
    echo    - POST /api/auth/login
    echo    - POST /api/auth/register
    echo    - GET  /api/reservas
    echo    - POST /api/reservas
    echo    - GET  /api/servicios
    echo.
    echo ⏹️  Para detener el servidor, presiona Ctrl+C
    echo.
    
    REM Ejecutar la aplicación
    call mvn spring-boot:run
) else (
    echo ❌ Error en la compilación
    echo 🔍 Revisa los errores anteriores
    pause
    exit /b 1
)
