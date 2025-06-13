# Sistema de Venta

Este proyecto es una aplicación web para la gestión de ventas, clientes y productos, desarrollada con el stack Spring Boot y Thymeleaf. Permite administrar clientes, productos y registrar ventas de manera sencilla y eficiente, con una interfaz moderna basada en TailwindCSS.

## Características principales

- **Gestión de Clientes:** Alta, edición, listado y eliminación de clientes.
- **Gestión de Productos:** Alta, edición, listado y eliminación de productos.
- **Registro de Ventas:** Permite registrar ventas asociando clientes y productos, con control de stock.
- **Interfaz Moderna:** Uso de TailwindCSS para un diseño atractivo, responsivo y oscuro.
- **Persistencia:** Uso de JPA/Hibernate para la gestión de datos en base de datos relacional.
- **MVC:** Arquitectura basada en controladores Spring, servicios y modelos.

## Tecnologías utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring MVC**
- **Spring Data JPA**
- **Thymeleaf**
- **TailwindCSS**
- **Lombok**
- **Base de datos relacional** (por ejemplo, MySQL o H2)

## Estructura del proyecto

- `/controller` - Controladores web para manejar las rutas y la lógica de negocio.
- `/model` - Entidades JPA para clientes, productos y ventas.
- `/service` - Servicios para la lógica de negocio y acceso a datos.
- `/templates` - Vistas Thymeleaf para la interfaz de usuario.
- `/static` - Recursos estáticos (CSS, imágenes, etc).

## Cómo ejecutar

1. Clona el repositorio.
2. Configura la base de datos en `application.properties`.
3. Ejecuta la aplicación con tu IDE o usando `mvn spring-boot:run`.
4. Accede a `http://localhost:8080/menu` en tu navegador.

---

Desarrollado como ejemplo de sistema de ventas con Java y Spring Boot.