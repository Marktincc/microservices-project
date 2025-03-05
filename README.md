# Microservices Project

Este proyecto implementa una arquitectura de microservicios utilizando Spring Boot y Spring Cloud. Cada microservicio se encarga de una funcionalidad específica y se comunican a través de una puerta de enlace API Gateway.

## Estructura del Proyecto

```
microservices-project/
│── api-gateway/                   # Puerta de enlace con Spring Cloud Gateway
│── service-proveedores/           # Gestión de proveedores
│── service-productos/             # Gestión de productos
│── service-categorias/            # Gestión de categorías
│── service-usuarios/              # Gestión de usuarios y autenticación
│── service-ventas/                # Gestión de ventas
│── service-relaciones-ventas/     # Relaciones entre ventas y productos
│── service-reportes/              # Reportes y análisis
│── config-server/                 # Servidor de configuración centralizada
│── common-utils/                  # Clases y modelos compartidos
└── README.md                      # Documentación del proyecto
```

## Descripción de los Microservicios

### API Gateway
- Utiliza **Spring Cloud Gateway** para gestionar el enrutamiento de las solicitudes.
- Implementa autenticación y autorización.
- Balanceo de carga y manejo de fallos.

### Service Proveedores
- Gestión de proveedores del sistema.
- CRUD de proveedores.

### Service Productos
- Gestión de productos disponibles en el sistema.
- CRUD de productos.

### Service Categorías
- Gestión de categorías de productos.
- CRUD de categorías.

### Service Usuarios
- Administración de usuarios y autenticación.
- Utiliza **Spring Security** y **JWT**.
- Registro y manejo de roles.

### Service Ventas
- Gestión de ventas y transacciones.
- Registro y actualización de ventas.

### Service Relaciones Ventas
- Maneja las relaciones entre ventas y productos.
- Permite la asociación de productos a una venta.

### Service Reportes
- Generación de reportes y análisis de datos.
- Provee métricas sobre ventas, productos y usuarios.

### Config Server
- Servidor centralizado de configuración con **Spring Cloud Config**.
- Permite la administración dinámica de configuraciones.

### Common Utils
- Librería con clases y modelos compartidos entre microservicios.
- Facilita la reutilización de código y evita duplicaciones.


4. Acceder a los servicios a través de la API Gateway.

## Tecnologías Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Cloud (Gateway, Config Server)**
- **Spring Security y JWT**
- **Base de datos SQL/NoSQL (según implementación)**

## Contribuciones
Para contribuir a este proyecto, sigue estos pasos:
1. Realiza un fork del repositorio.
2. Crea una nueva rama (`feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz un commit.
4. Envía un pull request.

## Contacto
Para cualquier duda o sugerencia, contacta con el equipo de desarrollo.

