# Lady's Spa API Documentation

## Base URL
`http://localhost:8082/api`

## Endpoints

### Authentication Endpoints

#### POST /auth/register
**Descripción**: Registrar un nuevo usuario
**URL**: `http://localhost:8082/api/auth/register`
**Método**: POST
**Headers**: 
- Content-Type: application/json

**Body (JSON)**:
```json
{
    "nombre": "María García",
    "email": "maria@gmail.com",
    "telefono": "123456789",
    "password": "password123"
}
```

**Respuesta exitosa (201)**:
```json
{
    "id": 1,
    "nombre": "María García",
    "email": "maria@gmail.com",
    "telefono": "123456789"
}
```

#### POST /auth/login
**Descripción**: Iniciar sesión
**URL**: `http://localhost:8082/api/auth/login`
**Método**: POST
**Headers**: 
- Content-Type: application/json

**Body (JSON)**:
```json
{
    "email": "maria@gmail.com",
    "password": "password123"
}
```

**Respuesta exitosa (200)**:
```json
{
    "id": 1,
    "nombre": "María García",
    "email": "maria@gmail.com",
    "telefono": "123456789"
}
```

### Usuario Endpoints

#### GET /usuarios
**Descripción**: Obtener todos los usuarios
**URL**: `http://localhost:8082/api/usuarios`
**Método**: GET

#### GET /usuarios/{id}
**Descripción**: Obtener usuario por ID
**URL**: `http://localhost:8082/api/usuarios/1`
**Método**: GET

#### PUT /usuarios/{id}
**Descripción**: Actualizar usuario
**URL**: `http://localhost:8082/api/usuarios/1`
**Método**: PUT
**Headers**: 
- Content-Type: application/json

**Body (JSON)**:
```json
{
    "nombre": "María García Actualizada",
    "email": "maria.nueva@gmail.com",
    "telefono": "987654321"
}
```

#### DELETE /usuarios/{id}
**Descripción**: Eliminar usuario
**URL**: `http://localhost:8082/api/usuarios/1`
**Método**: DELETE

### Reserva Endpoints

#### GET /reservas
**Descripción**: Obtener todas las reservas
**URL**: `http://localhost:8082/api/reservas`
**Método**: GET

#### GET /reservas/{id}
**Descripción**: Obtener reserva por ID
**URL**: `http://localhost:8082/api/reservas/1`
**Método**: GET

#### GET /reservas/usuario/{usuarioId}
**Descripción**: Obtener reservas de un usuario específico
**URL**: `http://localhost:8082/api/reservas/usuario/1`
**Método**: GET

#### POST /reservas
**Descripción**: Crear nueva reserva
**URL**: `http://localhost:8082/api/reservas`
**Método**: POST
**Headers**: 
- Content-Type: application/json

**Body (JSON)**:
```json
{
    "usuarioId": 1,
    "servicio": "Manicure y Pedicure",
    "fecha": "2025-08-15",
    "hora": "14:30",
    "observaciones": "Primera vez en el spa"
}
```

#### PUT /reservas/{id}
**Descripción**: Actualizar reserva
**URL**: `http://localhost:8082/api/reservas/1`
**Método**: PUT
**Headers**: 
- Content-Type: application/json

**Body (JSON)**:
```json
{
    "servicio": "Tratamiento de Pestañas",
    "fecha": "2025-08-16",
    "hora": "15:00",
    "observaciones": "Cambio de horario"
}
```

#### DELETE /reservas/{id}
**Descripción**: Eliminar reserva
**URL**: `http://localhost:8082/api/reservas/1`
**Método**: DELETE

### Servicio Endpoints

#### GET /servicios
**Descripción**: Obtener todos los servicios disponibles
**URL**: `http://localhost:8082/api/servicios`
**Método**: GET

**Respuesta esperada**:
```json
[
    {
        "id": 1,
        "nombre": "Tratamiento de Cejas",
        "descripcion": "Diseño y depilación profesional de cejas",
        "precio": 25000.0,
        "duracion": 45,
        "imagen": "/images/cejas.jpeg"
    },
    {
        "id": 2,
        "nombre": "Manicure y Pedicure",
        "descripcion": "Cuidado completo de manos y pies",
        "precio": 35000.0,
        "duracion": 60,
        "imagen": "/images/Manicure y pedicure.jpeg"
    },
    {
        "id": 3,
        "nombre": "Tratamiento de Pestañas",
        "descripcion": "Extensiones y lifting de pestañas",
        "precio": 45000.0,
        "duracion": 75,
        "imagen": "/images/pestañas.jpeg"
    }
]
```

### Test Endpoints

#### GET /test/ping
**Descripción**: Verificar que la API está funcionando
**URL**: `http://localhost:8082/api/test/ping`
**Método**: GET

**Respuesta esperada**:
```json
{
    "message": "¡API de Lady's Spa funcionando correctamente!",
    "timestamp": "2025-08-10T20:23:00.000+00:00",
    "status": "OK"
}
```

#### GET /test/db
**Descripción**: Verificar conexión a la base de datos
**URL**: `http://localhost:8082/api/test/db`
**Método**: GET

**Respuesta esperada**:
```json
{
    "message": "Conexión a la base de datos exitosa",
    "usuarios": 0,
    "reservas": 0
}
```

## Configuración de CORS
La API está configurada para aceptar requests desde:
- http://localhost:3000 (React Frontend)
- http://localhost:3001

## Códigos de Estado HTTP
- **200**: OK - Operación exitosa
- **201**: Created - Recurso creado exitosamente
- **400**: Bad Request - Datos inválidos
- **404**: Not Found - Recurso no encontrado
- **500**: Internal Server Error - Error del servidor

## Ejemplos de Uso

### 1. Flujo completo de registro y reserva:
1. **Registrar usuario**: POST /auth/register
2. **Iniciar sesión**: POST /auth/login
3. **Ver servicios**: GET /servicios
4. **Crear reserva**: POST /reservas
5. **Ver mis reservas**: GET /reservas/usuario/{usuarioId}

### 2. Gestión de reservas:
1. **Ver todas las reservas**: GET /reservas
2. **Actualizar reserva**: PUT /reservas/{id}
3. **Cancelar reserva**: DELETE /reservas/{id}

## Notas Importantes
- Todos los endpoints que reciben datos utilizan JSON
- Las fechas deben estar en formato YYYY-MM-DD
- Las horas deben estar en formato HH:mm
- Los precios están en pesos colombianos
- La duración de servicios está en minutos
