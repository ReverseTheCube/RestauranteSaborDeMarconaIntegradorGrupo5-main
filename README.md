# Smart Marcona - El Sabor de Marcona 🚀
### Sistema Inteligente de Gestión de Pedidos y Analítica Comercial

Este proyecto consiste en el diseño e implementación de un ecosistema digital transaccional y analítico a medida para el restaurante **"El Sabor de Marcona"**. La plataforma automatiza el flujo operativo de la toma de pedidos, despacho a cocina, liquidación en caja y la gestión de pensiones corporativas (B2B), transformando los registros transaccionales en indicadores estadísticos predictivos sin la necesidad de implementar un módulo físico de inventarios.

---

## 🛠️ Tecnologías Utilizadas

El sistema está construido bajo una arquitectura monolítica modular utilizando componentes tecnológicos eficientes de código abierto:

* **Backend:** Java 17, Spring Boot 3.x, Spring Data JPA, Hibernate, Spring Security.
* **Base de Datos:** MySQL 8.0 (Estructura relacional normalizada).
* **Frontend:** HTML5, CSS3, JavaScript Nativo (Manejo de peticiones asíncronas mediante Fetch API).
* **Componente Analítico:** Chart.js (Librería para la renderización de gráficos estadísticos en tiempo real).

---

## 📂 Estructura del Proyecto

La organización del código fuente en el repositorio sigue las convenciones del patrón arquitectónico en capas del entorno Spring Boot:

```text
restaurantaplicacion/
├── src/
│   ├── main/
│   │   ├── java/com/restaurant/restaurantaplicacion/
│   │   │   ├── config/          # Configuraciones de seguridad del sistema
│   │   │   ├── controller/      # Controladores REST (Mapeo de Endpoints y APIs)
│   │   │   ├── dto/             # Data Transfer Objects (Payloads de entrada y salida)
│   │   │   ├── model/           # Entidades de persistencia JPA (Tablas MySQL)
│   │   │   ├── repository/      # Interfaces de acceso a datos (Spring Data JPA)
│   │   │   └── service/         # Lógica pura del negocio y servicios estadísticos
│   │   └── resources/
│   │       ├── static/          # Vistas Frontend (HTML, CSS y JS por roles)
│   │       └── application.properties # Archivo global de configuración del entorno
└── pom.xml                      # Archivo de configuración de dependencias de Maven
```

---

## 💻 Instrucciones de Instalación y Despliegue

Sigue estos tres pasos secuenciales para configurar y ejecutar el entorno de desarrollo local:

### 1. Configuración de la Base de Datos
Ingresa a tu gestor de base de datos relacional (MySQL Workbench, phpMyAdmin o terminal de comandos) y crea un esquema o base de datos vacía para almacenar las tablas del sistema:

```sql
CREATE DATABASE sabor_marcona_db;
```

### 2. Configuración de Variables en application.properties
Navega hasta la ruta `src/main/resources/application.properties` en tu editor de código y edita las siguientes líneas para sincronizar las credenciales de acceso a tu servidor MySQL local:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sabor_marcona_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=

# Propiedades de configuración de Hibernate y JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Compilación y Arranque del Servidor
Abre una terminal de comandos posicionada en la raíz del proyecto donde se encuentra el gestor de automatización de Maven y ejecuta la aplicación:

```bash
./mvnw spring-boot:run
```

Una vez que la consola muestre la inicialización exitosa de Tomcat, el servidor web local quedará escuchando peticiones en el puerto asignado (por defecto `http://localhost:8080`), permitiendo el acceso a las interfaces web.

---

## 👥 Desarrollador y Créditos Académicos

* **Autores:**
*    Andre Sebastian Quicaña Taboada
*    Jean Pier David Vega Choque
*    Carlos Giovanni Cáceres Ramos
*    Luciano Camavilca Cardenas 
* **Institución:** Universidad Tecnológica del Perú (UTP)
* **Sede:** Ica, Perú
* **Año:** 2026
