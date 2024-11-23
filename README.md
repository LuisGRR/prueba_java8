

# Prueba Técnica - Java 8 con Spring Boot

Este proyecto es una solución a la prueba técnica que consiste en desarrollar un aplicativo utilizando **Java 8** y **Spring Boot MVC**. El aplicativo permite gestionar tareas a través de una interfaz web y está diseñado siguiendo las mejores prácticas de desarrollo.

## Funcionalidades

1. **Pantalla de Inicio**: Una pantalla de bienvenida con un botón para registrar una nueva tarea.
2. **Alta de Tareas**: Permite registrar tareas con los siguientes campos:
   - Nombre
   - Descripción
   - Fecha de inicio
3. **Listado de Tareas**: Muestra todas las tareas registradas.

---

## Requisitos

### Tecnologías utilizadas
- **Java 8**
- **Spring Boot**
- **Maven**: Para la gestión de dependencias.
- **MyBatis**: Para el mapeo objeto-relacional (ORM).
- **PostgreSQL**: Base de datos relacional.
- **Bootstrap**: Para el diseño de la interfaz de usuario.

### Herramientas necesarias
- **Docker y Docker Compose**: Para levantar la base de datos PostgreSQL.
- **Java Development Kit (JDK 8)**.
- **Maven**.

---

## Configuración del Proyecto

### Paso 1: Clonar el repositorio

```bash
git clone https://github.com/LuisGRR/prueba_java8.git
cd prueba-java8
```

### Paso 2: Configuración de la base de datos con Docker

Este proyecto incluye un archivo `docker-compose.yml` para levantar un contenedor con PostgreSQL. 

1. Navega al directorio raíz del proyecto.
2. Ejecuta el siguiente comando:

```bash
docker-compose up -d
```

Esto creará un contenedor con:
- **PostgreSQL** en el puerto `5432`.
- Usuario: `root`
- Contraseña: `root`
- Base de datos: `taskdb`

### Paso 3: Configuración de la aplicación

Asegúrate de que el archivo `application.properties` está configurado correctamente para conectar con la base de datos. Ejemplo:

```properties
spring.application.name=prueba-java8
server.port=8090

spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=root
spring.datasource.password=root

mybatis.mapper-locations=classpath:mybatis-mapper/*.xml

spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.mode=HTML
spring.thymeleaf.encoding=UTF-8
```

### Paso 4: Crear la tabla tasks

Ejecutar la query que esta dentro del archivo `TaskDB.sql` para crear la tabla de la aplicasiòn 

### Paso 5: Compilar y ejecutar el proyecto

La aplicación estará disponible en [http://localhost:8090/tasks/](http://localhost:8090/tasks/).

---

## Estructura del Proyecto

```plaintext
prueba-java8/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com.prueba_tecnica.prueba_java8/
│   │   │   │   ├── controller/       # Controladores MVC
│   │   │   │   ├── DTO/              # DTO
│   │   │   │   ├── service/          # Lógica de negocio
│   │   │   │   ├── model/            # Modelos de datos
│   │   │   │   ├── mapper/           # MyBatis mappers
│   │   │   │   └── exception/        # Excepciones personalizadas
│   │   ├── resources/
│   │   │   ├── mybatis-mapper         # Mapper de las consultas SQL
│   │   │   ├── templates/             # Vistas HTML (Thymeleaf)
│   │   │   ├── application.properties # Configuración
├── docker-compose.yml                 # Configuración de Docker para PostgreSQL
├── TaskDB.sql                         # Query de la tabla tasks
└── pom.xml                            # Gestión de dependencias de Maven
```

---

## Pruebas

### Pruebas Unitarias e Integración

El proyecto incluye pruebas para validar la funcionalidad de los servicios y la persistencia de datos.

1. Ejecuta las pruebas con el siguiente comando:

```bash
mvn test
```

2. Las pruebas de integración se aseguran de que las operaciones CRUD funcionen correctamente.

---

## Uso de la Aplicación

1. Abre [http://localhost:8090/tasks/](http://localhost:8090/tasks/).
2. Navega por las siguientes secciones:
   - **Inicio**: Página principal con un botón para registrar una nueva tarea.
   - **Registrar Tarea**: Completa el formulario con los datos de la tarea.
   - **Listado de Tareas**: Visualiza todas las tareas registradas.

---

## Autor

Desarrollado por Luis Gerardo Rivera Rivera.
