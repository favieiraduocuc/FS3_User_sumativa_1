# Microservice-Users

Usuarios (auth básica y gestión de usuarios)

## Estructura
- `src/main/java/com/Empresa/labs/`:
  - `MicroserviceUsersApplication.java` (Clase principal)
  - `WebConfig.java` (CORS abierto por defecto)
  - **Pega aquí** tus paquetes existentes: `controller/`, `dto/`, `entity/`, `mapper/`, `repository/`, `service/`, `security/`, `exceptions/` según corresponda.
- `src/main/resources/application.properties`:
  - Puerto `8081` y `context-path=/api`
  - Agrega tu conexión a Oracle/MySQL (comentada de ejemplo).

## Cómo usar (rápido)
1. Pega **tus clases actuales** en los paquetes correspondientes **sin cambiar paquetes**.
2. Ajusta `application.properties` con tus credenciales de base de datos.
3. Compila y levanta:
   ```bash
   mvn clean package -DskipTests
   java -jar target/Microservice-Users-1.0.0.jar
   ```
   o en desarrollo:
   ```bash
   mvn -q -DskipTests spring-boot:run
   ```

## Endpoints (ejemplos)
- Este proyecto no define endpoints por sí mismo. Una vez pegues tus `controller`, quedarán disponibles bajo `http://localhost:8081/api/...`.

## Notas
- Si tus entidades de Resultados referencian `Usuario` por `@ManyToOne`, **duplica** la entidad `Usuario` en el micro de Lab-Exam para que JPA pueda resolver la FK (mismo paquete/clase), o migra a DTOs luego.
- Si usas Basic Auth con `UserDetailsService` que lee la tabla `USUARIO`, puedes copiar tu `security/` intacta a cada micro.
