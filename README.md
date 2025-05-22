# 📘 Pokedex API

Una API REST construida con Java y Spring Boot que consume la [PokeAPI](https://pokeapi.co/) y expone información detallada y optimizada de los Pokémons.

> 🚀 Desplegada en AWS con integración continua a través de GitHub Actions y contenedores Docker.

---

## 🌍 URL pública

Accede a la API en producción:

**🔗 https://pokedex-api-docker-env-env.eba-8bsc8uns.us-east-1.elasticbeanstalk.com**

---

## ⚙️ Tecnologías utilizadas

| Tecnología          | Rol principal                                      |
|---------------------|----------------------------------------------------|
| **Java 17**         | Lenguaje base                                      |
| **Spring Boot 3.2** | Framework para construir la API                    |
| **Maven**           | Sistema de construcción y gestión de dependencias |
| **Feign Client**    | Cliente HTTP para consumir la PokeAPI             |
| **Caffeine**        | Cache en memoria para mejorar el rendimiento      |
| **Docker**          | Contenerización de la aplicación                   |
| **Amazon ECR**      | Registro de imágenes Docker                        |
| **Elastic Beanstalk** | Servicio de despliegue automatizado de AWS      |
| **S3**              | Almacenamiento de artefactos de despliegue         |
| **GitHub Actions**  | Automatización del pipeline CI/CD                 |
| **Swagger/OpenAPI** | Documentación interactiva de endpoints REST       |
| **JUnit 5 & Mockito** | Pruebas unitarias y de integración              |

---

## 🧱 Arquitectura del proyecto

El proyecto sigue el enfoque de **Clean Architecture**, separando claramente la lógica de negocio, infraestructura y detalles de entrega:

pokedex-api/
├── pokedex-domain         # Entidades y lógica de negocio pura
├── pokedex-application    # Casos de uso y servicios de aplicación
├── pokedex-infrastructure # Implementaciones de Feign Clients y configuración
├── pokedex-api-app        # Controllers REST y configuración de Spring Boot

### 🧩 Patrones de diseño aplicados

- **Clean Architecture** para desacoplar responsabilidades
- **Builder Pattern** para construir objetos de respuesta de forma flexible
- **Feign Client** como patrón declarativo para consumir APIs externas

---

## 🚀 Despliegue automatizado (CI/CD)

Cada push a `main` ejecuta lo siguiente:

1. Construye la imagen Docker y la sube a **Amazon ECR**
2. Genera el archivo `Dockerrun.aws.json`
3. Empaqueta y sube a **S3**
4. Despliega a **Elastic Beanstalk**
5. Espera a que la nueva versión se procese correctamente

---

## 🧪 Cómo correr el proyecto localmente

### 🔧 Requisitos

- Java 17
- Maven
- Docker (opcional, para ejecutar en contenedor)

### ▶️ Ejecutar sin Docker

```bash
# Clona el proyecto
git clone https://github.com/tu-usuario/pokedex-api.git
cd pokedex-api

# Construye todos los módulos
mvn clean install

# Corre la app principal
cd pokedex-api-app
mvn spring-boot:run

Accede a Swagger:
http://localhost:8080/swagger-ui.html

# Ejecuta todas las pruebas
mvn test


Para generar el .jar ejecutable:

mvn clean package -DskipTests

El archivo quedará ubicado en:

pokedex-api-app/target/pokedex-api-1.0.0.jar
