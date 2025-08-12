#!/bin/bash

# Script para compilar y ejecutar el backend Spring Boot
# Versión REACT - Lady's Spa API

echo "🌸 Lady's Spa - Backend API Spring Boot"
echo "========================================"

# Cambiar al directorio del backend
cd "$(dirname "$0")"

echo "📁 Directorio actual: $(pwd)"

# Verificar que Maven está instalado
if ! command -v mvn &> /dev/null; then
    echo "❌ Error: Maven no está instalado o no está en el PATH"
    exit 1
fi

echo "✅ Maven encontrado"

# Limpiar y compilar
echo "🧹 Limpiando proyecto..."
mvn clean

echo "🔨 Compilando proyecto..."
mvn compile

if [ $? -eq 0 ]; then
    echo "✅ Compilación exitosa"
    echo ""
    echo "🚀 Iniciando servidor Spring Boot en puerto 8082..."
    echo "🌐 API disponible en: http://localhost:8082"
    echo "📡 Endpoints principales:"
    echo "   - POST /api/auth/login"
    echo "   - POST /api/auth/register"
    echo "   - GET  /api/reservas"
    echo "   - POST /api/reservas"
    echo "   - GET  /api/servicios"
    echo ""
    echo "⏹️  Para detener el servidor, presiona Ctrl+C"
    echo ""
    
    # Ejecutar la aplicación
    mvn spring-boot:run
else
    echo "❌ Error en la compilación"
    echo "🔍 Revisa los errores anteriores"
    exit 1
fi
