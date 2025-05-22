# Pokedex API

Una API REST construida con Java y Spring Boot que consume la [PokeAPI](https://pokeapi.co/) y expone información detallada y optimizada de los Pokémons.

> Desplegada en AWS con integración continua a través de GitHub Actions y contenedores Docker.

---

## URL pública

Accede a la API en producción:

**http://pokedex-api-docker-env-env.eba-8bsc8uns.us-east-1.elasticbeanstalk.com/swagger-ui/index.html**

---

## Tecnologías utilizadas

| Tecnología            | Rol principal                                      |
|----------------------|----------------------------------------------------|
| **Java 17**          | Lenguaje base                                      |
| **Spring Boot 3.2**  | Framework para construir la API                    |
| **Maven**            | Sistema de construcción y gestión de dependencias |
| **Feign Client**     | Cliente HTTP para consumir la PokeAPI             |
| **Caffeine**         | Cache en memoria para mejorar el rendimiento      |
| **Docker**           | Contenerización de la aplicación                   |
| **Amazon ECR**       | Registro de imágenes Docker                        |
| **Elastic Beanstalk**| Servicio de despliegue automatizado de AWS        |
| **S3**               | Almacenamiento de artefactos de despliegue        |
| **GitHub Actions**   | Automatización del pipeline CI/CD                 |
| **Swagger/OpenAPI**  | Documentación interactiva de endpoints REST       |
| **JUnit 5 & Mockito**| Pruebas unitarias y de integración                 |

---

## Arquitectura del proyecto

El proyecto sigue el enfoque de **Clean Architecture**, separando claramente la lógica de negocio, infraestructura y detalles de entrega:

- `pokedex-domain`: Contiene las **entidades centrales** y **contratos (interfaces)** independientes del framework.
- `pokedex-application`: Define los **casos de uso** y orquesta la lógica de aplicación.
- `pokedex-infrastructure`: Implementa integraciones externas como **Feign Clients**, acceso a datos, configuración, etc.
- `pokedex-api-app`: Punto de entrada principal. Expone los **endpoints REST**, configura Spring Boot y ejecuta la aplicación.

### Patrones de diseño aplicados

- **Clean Architecture** para desacoplar responsabilidades
- **Builder Pattern** para construir objetos de respuesta de forma flexible
- **Feign Client** como patrón declarativo para consumir APIs externas

---

## Despliegue Automatizado en AWS Elastic Beanstalk

El proyecto se despliega automáticamente a **Elastic Beanstalk** usando una imagen Docker personalizada y un workflow de GitHub Actions.

### Herramientas utilizadas

- **AWS Elastic Beanstalk**: Orquestador del despliegue (plataforma Docker).
- **Amazon ECR**: Almacena la imagen Docker generada.
- **Amazon S3**: Guarda los paquetes `.zip` con el archivo `Dockerrun.aws.json`.
- **GitHub Actions**: Automatiza el proceso CI/CD completo.

---

### Estructura del archivo `Dockerrun.aws.json`

```json
{
  "AWSEBDockerrunVersion": "1",
  "Image": {
    "Name": "319844024891.dkr.ecr.us-east-1.amazonaws.com/pokedex-api:latest",
    "Update": "true"
  },
  "Ports": [
    {
      "ContainerPort": "8080"
    }
  ],
  "Logging": "/var/log/nginx"
}
```

## Despliegue automatizado (CI/CD)

Cada push a `main` ejecuta lo siguiente:

1. Construye la imagen Docker y la sube a **Amazon ECR**
2. Genera el archivo `Dockerrun.aws.json`
3. Empaqueta y sube a **S3**
4. Despliega a **Elastic Beanstalk**
5. Espera a que la nueva versión se procese correctamente
6. Toda esta configuración se puede encontrar en la ruta .github/workflows/deploy.yml del proyecto

---

Consideraciones

- Se utiliza la plataforma Docker on 64bit Amazon Linux 2 en Beanstalk.
- El workflow requiere secretos configurados en GitHub.
- AWS_ACCESS_KEY_ID.
- AWS_SECRET_ACCESS_KEY.
- El repositorio ECR y el bucket S3 deben estar previamente creados.


## Cómo correr el proyecto localmente

### Requisitos

- Java 17
- Maven
- Docker (opcional, para ejecutar en contenedor)

### Ejecutar sin Docker

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
