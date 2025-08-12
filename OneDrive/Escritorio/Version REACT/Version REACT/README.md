# Lady's Spa - React + Spring Boot Version

## Descripción
Sistema completo de gestión para spa de belleza desarrollado con React.js (frontend) y Spring Boot (backend), utilizando arquitectura REST API.

## 🚀 Características

### Frontend (React.js)
- **Interfaz moderna y responsiva** con diseño gradiente rosado/morado
- **Sistema de autenticación** con contexto global
- **Navegación por rutas** con React Router
- **Páginas principales**:
  - Inicio con hero section y información del spa
  - Login y registro de usuarios
  - Catálogo de servicios con integración API
  - Sistema de reservas con selección de fecha/hora
  - Panel de citas del usuario
- **Integración API** con Axios
- **Manejo de estados** con hooks y context

### Backend (Spring Boot)
- **API RESTful completa** con 16 endpoints
- **Base de datos MySQL** con JPA/Hibernate
- **Arquitectura MVC** bien estructurada
- **CORS configurado** para desarrollo
- **Controladores**:
  - AuthController (autenticación)
  - UsuarioController (gestión de usuarios)
  - ReservaController (gestión de reservas)
  - ServicioController (catálogo de servicios)
  - TestController (verificación de estado)

## 📋 Requisitos Previos

- **Node.js** (versión 14 o superior)
- **npm** (incluido con Node.js)
- **Java** (versión 11 o superior)
- **Maven** (versión 3.6 o superior)
- **MySQL** (versión 8.0 o superior)

## 🛠️ Instalación y Configuración

### 1. Configuración de la Base de Datos

```sql
-- Crear la base de datos
CREATE DATABASE ladyspa;

-- Usar la base de datos
USE ladyspa;

-- Crear tabla usuarios
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telefono VARCHAR(50),
    password VARCHAR(255) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla reservas
CREATE TABLE reservas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    servicio VARCHAR(255) NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    estado VARCHAR(50) DEFAULT 'Confirmada',
    observaciones TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Insertar datos de servicios (estos se manejan desde código)
-- Los servicios están hardcodeados en ServicioController.java
```

### 2. Configurar Backend (Spring Boot)

```bash
# Navegar al directorio del backend
cd "Version REACT/backend"

# Verificar que application.properties tenga la configuración correcta:
# spring.datasource.url=jdbc:mysql://localhost:3306/ladyspa
# spring.datasource.username=root
# spring.datasource.password=123456

# Ejecutar el backend
mvn spring-boot:run
```

El backend se ejecutará en: **http://localhost:8082**

### 3. Configurar Frontend (React)

```bash
# Navegar al directorio del frontend
cd "Version REACT/frontend"

# Instalar dependencias (si no están instaladas)
npm install

# Ejecutar el frontend
npm start
```

El frontend se ejecutará en: **http://localhost:3000**

## 🔄 URLs del Sistema

- **Frontend React**: http://localhost:3000
- **Backend API**: http://localhost:8082
- **API Test**: http://localhost:8082/api/test/ping
- **API Servicios**: http://localhost:8082/api/servicios

## 🚀 Fases de Desarrollo

### ✅ FASE 1: Estructura del Proyecto (COMPLETADA)
- [x] Estructura de carpetas backend/frontend
- [x] Configuración Spring Boot con Maven
- [x] Configuración React con package.json
- [x] Modelos JPA (Usuario, Reserva)
- [x] Repositorios Spring Data
- [x] Configuración de base de datos MySQL

### ✅ FASE 2: Backend APIs (COMPLETADA)
- [x] Servicios de negocio (UsuarioService, ReservaService)
- [x] Controladores REST con validaciones
- [x] Configuración CORS para React
- [x] DTOs y manejo de errores
- [x] Manejo global de excepciones

### ✅ FASE 3: Frontend React (COMPLETADA)
- [x] Componentes de interfaz con diseño moderno
- [x] Rutas y navegación con React Router
- [x] Integración completa con APIs
- [x] Estados y contexto de autenticación
- [x] Todas las páginas implementadas

### ✅ FASE 4: Testing y Postman (COMPLETADA)
- [x] Colección Postman con todos los endpoints
- [x] Documentación completa de API
- [x] Pruebas de integración frontend-backend
- [x] Validación completa del sistema

## 📁 Estructura del Proyecto

```
Version REACT/
├── backend/                          # Spring Boot API
│   ├── src/main/java/com/ladyspa/
│   │   ├── LadySpaApiApplication.java # Clase principal
│   │   ├── config/
│   │   │   └── CorsConfig.java       # Configuración CORS
│   │   ├── controller/               # Controladores REST
│   │   │   ├── AuthController.java
│   │   │   ├── ReservaController.java
│   │   │   ├── ServicioController.java
│   │   │   ├── TestController.java
│   │   │   └── UsuarioController.java
│   │   ├── model/                    # Entidades JPA
│   │   │   ├── Reserva.java
│   │   │   └── Usuario.java
│   │   ├── repository/               # Repositorios JPA
│   │   │   ├── ReservaRepository.java
│   │   │   └── UsuarioRepository.java
│   │   └── dto/                      # DTOs
│   │       ├── LoginRequest.java
│   │       └── RegisterRequest.java
│   ├── src/main/resources/
│   │   └── application.properties    # Configuración de BD
│   └── pom.xml                       # Dependencias Maven
├── frontend/                         # React App
│   ├── public/
│   │   ├── images/                   # Imágenes del spa
│   │   └── index.html
│   ├── src/
│   │   ├── components/
│   │   │   └── Navbar.js            # Componente de navegación
│   │   ├── context/
│   │   │   └── AuthContext.js       # Contexto de autenticación
│   │   ├── pages/
│   │   │   ├── Home.js              # Página de inicio
│   │   │   ├── Login.js             # Página de login
│   │   │   ├── Register.js          # Página de registro
│   │   │   ├── Services.js          # Catálogo de servicios
│   │   │   ├── BookAppointment.js   # Agendar cita
│   │   │   └── Appointments.js      # Mis citas
│   │   ├── styles/                  # Archivos CSS
│   │   ├── App.js                   # Componente principal
│   │   └── index.js                 # Punto de entrada
│   └── package.json                 # Dependencias npm
├── API_Documentation.md             # Documentación de API
└── Lady_Spa_API_Postman_Collection.json # Colección Postman
```

## 🧪 Testing con Postman

### Importar Colección
1. Abrir Postman
2. Click en "Import"
3. Seleccionar el archivo `Lady_Spa_API_Postman_Collection.json`
4. La colección incluye todos los endpoints organizados por categorías

### Flujo de Pruebas Recomendado

#### 1. Verificar Estado del Sistema
- **Test API**: GET `/api/test/ping`
- **Test BD**: GET `/api/test/db`

#### 2. Autenticación
- **Registrar Usuario**: POST `/api/auth/register`
- **Iniciar Sesión**: POST `/api/auth/login`

#### 3. Servicios
- **Ver Servicios**: GET `/api/servicios`

#### 4. Reservas
- **Crear Reserva**: POST `/api/reservas`
- **Ver Mis Reservas**: GET `/api/reservas/usuario/{id}`
- **Actualizar Reserva**: PUT `/api/reservas/{id}`

## 🎯 Funcionalidades del Frontend

### Navegación
- **Página de Inicio**: Hero section con información del spa
- **Autenticación**: Login y registro con validación
- **Servicios**: Catálogo con imágenes y precios
- **Agendar**: Formulario de reservas con validación de fechas
- **Mis Citas**: Panel de gestión de reservas del usuario

### Características Técnicas
- **Responsive Design**: Se adapta a móviles y desktop
- **Autenticación Persistente**: Estado guardado en localStorage
- **Validación de Formularios**: Control de errores en tiempo real
- **Loading States**: Indicadores de carga durante requests
- **Error Handling**: Manejo elegante de errores de API

## � Configuración Avanzada

### Variables de Entorno (Frontend)
Crear archivo `.env` en `/frontend`:
```
REACT_APP_API_URL=http://localhost:8082/api
```

### Configuración de Base de Datos (Backend)
Editar `application.properties`:
```properties
# Configuración de base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/ladyspa
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuración JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Puerto del servidor
server.port=8082
```

## 🚀 Despliegue

### Frontend (Build para Producción)
```bash
cd frontend
npm run build
```

### Backend (JAR para Producción)
```bash
cd backend
mvn clean package
java -jar target/ladyspa-api-1.0.0.jar
```

## 🔍 Troubleshooting

### Problemas Comunes

**1. Error de CORS**
- Verificar que CorsConfig.java permita el origen del frontend
- Frontend debe estar en http://localhost:3000

**2. Error de Conexión a BD**
- Verificar que MySQL esté ejecutándose
- Confirmar credenciales en application.properties
- Asegurarse de que la base de datos 'ladyspa' exista

**3. Error 404 en API**
- Verificar que el backend esté ejecutándose en puerto 8082
- Confirmar URLs de endpoints en el frontend

**4. Imágenes no cargan**
- Verificar que las imágenes estén en `/frontend/public/images/`
- Confirmar rutas en el código

## 📞 Servicios Disponibles

1. **Tratamiento de Cejas** - $25,000 (45 min)
2. **Manicure y Pedicure** - $35,000 (60 min)  
3. **Tratamiento de Pestañas** - $45,000 (75 min)

## 👥 Información del Spa

- **Nombre**: Lady's Spa
- **Teléfono**: +57 300 123 4567
- **Email**: info@ladysspa.com
- **Horarios**: Lunes a Sábado 9:00 AM - 6:00 PM

---

## ✅ Estado del Proyecto

- ✅ **Backend API**: Completamente funcional en puerto 8082
- ✅ **Frontend React**: Implementado y operativo en puerto 3000
- ✅ **Base de Datos**: Configurada y conectada
- ✅ **Documentación**: API y Postman listos
- ✅ **Testing**: Endpoints verificados y funcionales
- ✅ **Integración**: Frontend y Backend comunicándose correctamente

**¡El proyecto está listo para usar y probar!** 🎉
- **Spring Data JPA** - Acceso a datos
- **MySQL 8.0** - Base de datos (misma que versión JSP)
- **Maven** - Gestión de dependencias

### Frontend
- **React 18.2.0** - Librería de UI
- **React Router 6.3.0** - Navegación
- **Axios 0.27.2** - Cliente HTTP
- **CSS3** - Estilos (basados en versión JSP)

## 🗄️ Base de Datos

Utiliza la misma base de datos `ladyspa` creada para la versión JSP:
- **Usuarios**: Gestión de clientes
- **Reservas**: Sistema de citas

## 🚀 Comandos de Ejecución

### Backend (Puerto 8082)
```bash
cd backend
mvn spring-boot:run
```

### Frontend (Puerto 3000)
```bash
cd frontend
npm install
npm start
```

## 📡 APIs REST (IMPLEMENTADAS)

### 🔐 Autenticación
- `POST /api/auth/login` - Iniciar sesión
  ```json
  // Request
  { "email": "usuario@email.com", "password": "123456" }
  // Response
  { "success": true, "message": "Login exitoso", "data": { ...usuario } }
  ```

- `POST /api/auth/register` - Registrar usuario
  ```json
  // Request
  { "nombre": "Juan Pérez", "email": "juan@email.com", "telefono": "3001234567", "password": "123456" }
  // Response
  { "success": true, "message": "Usuario registrado exitosamente", "data": { ...usuario } }
  ```

- `GET /api/auth/verify-email/{email}` - Verificar disponibilidad de email

### 👥 Usuarios
- `GET /api/usuarios` - Listar todos los usuarios
- `GET /api/usuarios/{id}` - Obtener usuario por ID
- `GET /api/usuarios/email/{email}` - Obtener usuario por email

### 📅 Reservas
- `GET /api/reservas` - Listar todas las reservas (con datos de usuario)
- `POST /api/reservas` - Crear nueva reserva
  ```json
  // Request
  { "usuarioId": 1, "servicio": "Laminado de Cejas", "fecha": "2025-08-15", "hora": "10:00" }
  ```
- `GET /api/reservas/usuario/{id}` - Reservas por usuario
- `GET /api/reservas/{id}` - Obtener reserva específica
- `GET /api/reservas/fecha/{fecha}` - Reservas por fecha
- `GET /api/reservas/disponibilidad/{fecha}` - Horarios disponibles

### 🏢 Servicios
- `GET /api/servicios` - Listar servicios disponibles
- `GET /api/servicios/{id}` - Obtener servicio específico

### 📋 Características de las APIs
- ✅ **Validaciones completas** con Bean Validation
- ✅ **Manejo de errores** consistente con `ApiResponse<T>`
- ✅ **CORS configurado** para React (puerto 3000)
- ✅ **Códigos HTTP apropiados** (200, 201, 400, 404, 500)
- ✅ **Documentación JSON** estructurada
- ✅ **Prevención de conflictos** (horarios ocupados)

---

## 📝 Próximos Pasos

**¿Continuamos con la FASE 2: Backend APIs?**

1. Crear servicios de negocio
2. Implementar controladores REST
3. Configurar CORS para React
4. Probar APIs con Postman
