# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/pragma/loanrequest/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/loanrequest/application/service/LoanRequestService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/LoanRequestJpaAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/ExternalSystemAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanrequest/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanrequest/infrastructure/tracing/TracingService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanrequest/infrastructure/config/SwaggerConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/LoanRequestJpaAdapter.java` — `LoanRequestRepository.save`: Se invoca `save` sobre `LoanRequestRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/LoanRequestJpaAdapter.java` — `LoanRequestRepository.findById`: Se invoca `findById` sobre `LoanRequestRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `TracingRecord.operationType`: Se invoca `operationType` sobre `TracingRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `TracingRecord.entityId`: Se invoca `entityId` sobre `TracingRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `TracingRecord.customerId`: Se invoca `customerId` sobre `TracingRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `TracingRecord.amount`: Se invoca `amount` sobre `TracingRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `TracingRecord.metadata`: Se invoca `metadata` sobre `TracingRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `TracingService.record`: Se invoca `record` sobre `TracingService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `TracingRecord.status`: Se invoca `status` sobre `TracingRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestController.java` — `LoanRequestDTO.getCustomerId`: Se invoca `getCustomerId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestController.java` — `LoanRequestDTO.getAmount`: Se invoca `getAmount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestController.java` — `LoanRequestMapper.toDTO`: Se invoca `toDTO` sobre `LoanRequestMapper`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanrequest/infrastructure/tracing/TracingService.java` — `TraceRecord.toMap`: Se invoca `toMap` sobre `TraceRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.setCustomerId`: Se invoca `setCustomerId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.setAmount`: Se invoca `setAmount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.setTermMonths`: Se invoca `setTermMonths` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.setInterestRate`: Se invoca `setInterestRate` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.setProductType`: Se invoca `setProductType` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.setChannel`: Se invoca `setChannel` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.setIdempotencyKey`: Se invoca `setIdempotencyKey` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.getCustomerId`: Se invoca `getCustomerId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.getAmount`: Se invoca `getAmount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.getTermMonths`: Se invoca `getTermMonths` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.getInterestRate`: Se invoca `getInterestRate` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.getProductType`: Se invoca `getProductType` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.getChannel`: Se invoca `getChannel` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
Chapter Backend, Especialidad Desarrollador, Tecnolog�a Java, Advanced � Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programaci�n principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

### Reto
- Tema: Experiencia en Frameworks
- Seniority: advanced-l2
- Tipo: practical
- Título: Implementación de un Servicio de Gestión de Solicitudes
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Recepción y Persistencia de Solicitudes — objetivo: Implementar la funcionalidad básica para recibir y persistir solicitudes de préstamos con garantías de idempotencia. — entregable (NO resolver): Servicio que recibe y persiste solicitudes de préstamos con garantías de idempotencia.
- Fase 2: Validación de Solicitudes — objetivo: Implementar la validación de las solicitudes recibidas antes de persistirlas en la base de datos. — entregable (NO resolver): Servicio que valida las solicitudes recibidas antes de persistirlas en la base de datos.
- Fase 3: Trazabilidad de Operaciones — objetivo: Implementar la trazabilidad de las operaciones realizadas por el servicio. — entregable (NO resolver): Servicio que genera y persiste registros de trazabilidad para cada operación realizada.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>loanrequest</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>loanrequest</name>
    <description>Servicio de gestión de solicitudes de préstamos</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
        <springdoc.version>2.9.1</springdoc.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- SpringDoc OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc.version}</version>
        </dependency>

        <!-- Databases -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/loanrequest/LoanRequestApplication.java ===
package com.pragma.loanrequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties
public class LoanRequestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanRequestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CircuitBreakerConfig customCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(5)
                .build();
    }

    @Bean
    public TimeLimiterConfig customTimeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(2))
                .build();
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
server:
  port: 8080
  servlet:
    context-path: /api/loans

spring:
  application:
    name: loan-request-service
  datasource:
    url: jdbc:postgresql://localhost:5432/loan_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 20
      connection-timeout: 30000
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect
  h2:
    console:
      enabled: true
      path: /h2-console

resilience4j:
  circuitbreaker:
    configs:
      default:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
        recordExceptions:
          - org.springframework.web.client.HttpServerErrorException
          - java.io.IOException
          - java.util.concurrent.TimeoutException
          - org.springframework.web.client.ResourceAccessException
    instances:
      externalSystem:
        baseConfig: default
  timelimiter:
    configs:
      default:
        timeoutDuration: 2s
    instances:
      externalSystem:
        baseConfig: default

management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    health:
      show-details: always

logging:
  level:
    root: INFO
    com.pragma.loanrequest: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE

// === ARCHIVO: src/main/java/com/pragma/loanrequest/domain/model/LoanRequest.java ===
package com.pragma.loanrequest.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class LoanRequest {
    private final UUID id;
    private final String customerId;
    private final BigDecimal amount;
    private final Integer termMonths;
    private final BigDecimal interestRate;
    private final LocalDate requestDate;
    private final String status;
    private final String idempotencyKey;
    private final String productType;
    private final String channel;

    public LoanRequest(UUID id, String customerId, BigDecimal amount, Integer termMonths,
                      BigDecimal interestRate, LocalDate requestDate, String status,
                      String idempotencyKey, String productType, String channel) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.termMonths = termMonths;
        this.interestRate = interestRate;
        this.requestDate = requestDate;
        this.status = status;
        this.idempotencyKey = idempotencyKey;
        this.productType = productType;
        this.channel = channel;
        validate();
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public String getStatus() {
        return status;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getProductType() {
        return productType;
    }

    public String getChannel() {
        return channel;
    }

    private void validate() {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo o vacío");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        if (termMonths == null || termMonths <= 0) {
            throw new IllegalArgumentException("El plazo en meses debe ser mayor a cero");
        }
        if (interestRate == null || interestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("La tasa de interés no puede ser negativa");
        }
        if (requestDate == null || requestDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de solicitud no puede ser nula o futura");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("El estado no puede ser nulo o vacío");
        }
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("La clave de idempotencia no puede ser nula o vacía");
        }
        if (productType == null || productType.isBlank()) {
            throw new IllegalArgumentException("El tipo de producto no puede ser nulo o vacío");
        }
        if (channel == null || channel.isBlank()) {
            throw new IllegalArgumentException("El canal no puede ser nulo o vacío");
        }
    }

    public LoanRequest withStatus(String newStatus) {
        return new LoanRequest(
            this.id,
            this.customerId,
            this.amount,
            this.termMonths,
            this.interestRate,
            this.requestDate,
            newStatus,
            this.idempotencyKey,
            this.productType,
            this.channel
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/domain/port/in/LoanRequestUseCase.java ===
package com.pragma.loanrequest.domain.port.in;

import com.pragma.loanrequest.domain.model.LoanRequest;
import java.util.UUID;

public interface LoanRequestUseCase {
    /**
     * Crea una nueva solicitud de préstamo con garantía de idempotencia.
     * @param loanRequest La solicitud de préstamo a crear
     * @return La solicitud de préstamo creada
     * @throws IllegalArgumentException Si la solicitud es inválida
     * @throws IllegalStateException Si ya existe una solicitud con la misma clave de idempotencia
     */
    LoanRequest createLoanRequest(LoanRequest loanRequest);

    /**
     * Obtiene una solicitud de préstamo por su ID.
     * @param id El ID de la solicitud
     * @return La solicitud de préstamo encontrada
     * @throws java.util.NoSuchElementException Si no se encuentra la solicitud
     */
    LoanRequest getLoanRequestById(UUID id);

    /**
     * Valida una solicitud de préstamo antes de procesarla.
     * @param loanRequest La solicitud a validar
     * @throws IllegalArgumentException Si la solicitud no pasa las validaciones de negocio
     */
    void validateLoanRequest(LoanRequest loanRequest);
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/domain/port/out/LoanRequestPersistencePort.java ===
package com.pragma.loanrequest.domain.port.out;

import com.pragma.loanrequest.domain.model.LoanRequest;
import java.util.Optional;
import java.util.UUID;

public interface LoanRequestPersistencePort {
    /**
     * Guarda una solicitud de préstamo en el sistema de persistencia.
     * @param loanRequest La solicitud a guardar
     * @return La solicitud guardada
     */
    LoanRequest save(LoanRequest loanRequest);

    /**
     * Busca una solicitud de préstamo por su ID.
     * @param id El ID de la solicitud
     * @return Optional con la solicitud si existe, vacío en caso contrario
     */
    Optional<LoanRequest> findById(UUID id);

    /**
     * Verifica si ya existe una solicitud con la misma clave de idempotencia.
     * @param idempotencyKey La clave de idempotencia
     * @return true si existe una solicitud con esa clave, false en caso contrario
     */
    boolean existsByIdempotencyKey(String idempotencyKey);

    /**
     * Busca una solicitud por su clave de idempotencia.
     * @param idempotencyKey La clave de idempotencia
     * @return Optional con la solicitud si existe, vacío en caso contrario
     */
    Optional<LoanRequest> findByIdempotencyKey(String idempotencyKey);
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/domain/port/out/ExternalSystemPort.java ===
package com.pragma.loanrequest.domain.port.out;

import com.pragma.loanrequest.domain.model.LoanRequest;
import java.util.UUID;

public interface ExternalSystemPort {
    
    boolean checkRiskBureau(String customerId, LoanRequest loanRequest);
    
    boolean checkBankingCore(String customerId, LoanRequest loanRequest);
    
    RiskBureauResponse getRiskAssessment(String customerId);
    
    BankingCoreResponse getBankingProfile(String customerId);
    
    record RiskBureauResponse(
        String customerId,
        String riskScore,
        String riskLevel,
        boolean hasOutstandingDebts,
        int creditHistoryYears
    ) {}
    
    record BankingCoreResponse(
        String customerId,
        BigDecimal accountBalance,
        String accountStatus,
        int monthlyIncome,
        boolean hasActiveLoans
    ) {
        public BankingCoreResponse {
            if (accountBalance == null) {
                accountBalance = BigDecimal.ZERO;
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/domain/port/out/TracingPort.java ===
package com.pragma.loanrequest.domain.port.out;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TracingPort {
    
    void recordOperation(TracingRecord record);
    
    List<TracingRecord> getOperationsByLoanRequestId(UUID loanRequestId);
    
    List<TracingRecord> getOperationsByCustomerId(String customerId);
    
    TracingRecord getOperationById(UUID operationId);
    
    record TracingRecord(
        UUID id,
        UUID loanRequestId,
        String customerId,
        String operationType,
        String operationDescription,
        LocalDateTime timestamp,
        String status,
        String errorMessage,
        long durationMs
    ) {
        public TracingRecord {
            if (id == null) {
                id = UUID.randomUUID();
            }
            if (timestamp == null) {
                timestamp = LocalDateTime.now();
            }
            if (status == null) {
                status = "SUCCESS";
            }
            if (durationMs < 0) {
                durationMs = 0;
            }
        }
        
        public static TracingRecord createSuccess(UUID loanRequestId, String customerId,
                String operationType, String operationDescription, long durationMs) {
            return new TracingRecord(null, loanRequestId, customerId, operationType,
                    operationDescription, null, "SUCCESS", null, durationMs);
        }
        
        public static TracingRecord createError(UUID loanRequestId, String customerId,
                String operationType, String operationDescription, String errorMessage, long durationMs) {
            return new TracingRecord(null, loanRequestId, customerId, operationType,
                    operationDescription, null, "ERROR", errorMessage, durationMs);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/application/dto/LoanRequestDTO.java ===
package com.pragma.loanrequest.application.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LoanRequestDTO(
    UUID id,
    
    @NotBlank(message = "El ID del cliente es obligatorio")
    @Size(min = 5, max = 50, message = "El ID del cliente debe tener entre 5 y 50 caracteres")
    String customerId,
    
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "100.00", message = "El monto mínimo es 100")
    @DecimalMax(value = "1000000.00", message = "El monto máximo es 1,000,000")
    BigDecimal amount,
    
    @NotNull(message = "El plazo en meses es obligatorio")
    @Min(value = 1, message = "El plazo mínimo es 1 mes")
    @Max(value = 360, message = "El plazo máximo es 360 meses")
    Integer termMonths,
    
    @NotNull(message = "La tasa de interés es obligatoria")
    @DecimalMin(value = "0.01", message = "La tasa mínima es 0.01%")
    @DecimalMax(value = "99.99", message = "La tasa máxima es 99.99%")
    BigDecimal interestRate,
    
    LocalDate requestDate,
    
    @NotBlank(message = "La clave de idempotencia es obligatoria")
    @Size(max = 100, message = "La clave de idempotencia no puede exceder 100 caracteres")
    String idempotencyKey,
    
    @NotBlank(message = "El tipo de producto es obligatorio")
    @Size(max = 50, message = "El tipo de producto no puede exceder 50 caracteres")
    String productType,
    
    @NotBlank(message = "El canal es obligatorio")
    @Size(max = 50, message = "El canal no puede exceder 50 caracteres")
    String channel,
    
    String status
) {
    public LoanRequestDTO {
        if (requestDate == null) {
            requestDate = LocalDate.now();
        }
        if (status == null || status.isBlank()) {
            status = "PENDING";
        }
    }
    
    public static LoanRequestDTO createNew(String customerId, BigDecimal amount, 
            Integer termMonths, BigDecimal interestRate, String idempotencyKey,
            String productType, String channel) {
        return new LoanRequestDTO(
            null,
            customerId,
            amount,
            termMonths,
            interestRate,
            LocalDate.now(),
            idempotencyKey,
            productType,
            channel,
            "PENDING"
        );
    }
    
    public boolean isValidForProcessing() {
        return customerId != null && !customerId.isBlank()
            && amount != null && amount.compareTo(BigDecimal.ZERO) > 0
            && termMonths != null && termMonths > 0
            && interestRate != null && interestRate.compareTo(BigDecimal.ZERO) > 0
            && idempotencyKey != null && !idempotencyKey.isBlank()
            && productType != null && !productType.isBlank()
            && channel != null && !channel.isBlank();
    }
    
    public String toLogString() {
        return String.format("LoanRequestDTO[id=%s, customerId=%s, amount=%s, termMonths=%s, " +
                "productType=%s, channel=%s, status=%s]", 
            id != null ? id.toString().substring(0, 8) : "null",
            customerId,
            amount,
            termMonths,
            productType,
            channel,
            status);
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/infrastructure/persistence/repository/LoanRequestRepository.java ===
package com.pragma.loanrequest.infrastructure.persistence.repository;

import com.pragma.loanrequest.infrastructure.persistence.entity.LoanRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequestEntity, UUID> {

    boolean existsByIdempotencyKey(String idempotencyKey);

    Optional<LoanRequestEntity> findByIdempotencyKey(String idempotencyKey);

    @Query("SELECT lr FROM LoanRequestEntity lr WHERE lr.customerId = :customerId AND lr.status = :status")
    Optional<LoanRequestEntity> findByCustomerIdAndStatus(
            @Param("customerId") String customerId,
            @Param("status") String status
    );

    @Query("SELECT COUNT(lr) FROM LoanRequestEntity lr WHERE lr.customerId = :customerId AND lr.status IN ('APPROVED', 'ACTIVE')")
    Long countActiveLoansByCustomerId(@Param("customerId") String customerId);
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/application/service/LoanRequestService.java ===
package com.pragma.loanrequest.application.service;

import com.pragma.loanrequest.domain.model.LoanRequest;
import com.pragma.loanrequest.domain.port.in.LoanRequestUseCase;
import com.pragma.loanrequest.domain.port.out.LoanRequestPersistencePort;
import com.pragma.loanrequest.domain.port.out.TracingPort;
import com.pragma.loanrequest.infrastructure.exception.LoanRequestException;
import com.pragma.loanrequest.infrastructure.persistence.mapper.LoanRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanRequestService implements LoanRequestUseCase {

    private static final Logger log = LoggerFactory.getLogger(LoanRequestService.class);

    private final LoanRequestPersistencePort persistencePort;
    private final LoanRequestMapper loanRequestMapper;
    private final TracingPort tracingPort;

    public LoanRequestService(
            LoanRequestPersistencePort persistencePort,
            LoanRequestMapper loanRequestMapper,
            TracingPort tracingPort
    ) {
        this.persistencePort = persistencePort;
        this.loanRequestMapper = loanRequestMapper;
        this.tracingPort = tracingPort;
    }

    @Override
    @Transactional
    public LoanRequest createLoanRequest(LoanRequest loanRequest) {
        String traceId = UUID.randomUUID().toString();
        tracingPort.recordOperation("CREATE_LOAN_REQUEST", traceId, "START", loanRequest.getCustomerId());

        try {
            validateLoanRequest(loanRequest);

            if (persistencePort.existsByIdempotencyKey(loanRequest.getIdempotencyKey())) {
                log.warn("Duplicate loan request detected for idempotency key: {}", loanRequest.getIdempotencyKey());
                Optional<LoanRequest> existingRequest = persistencePort.findByIdempotencyKey(
                        loanRequest.getIdempotencyKey()
                );
                tracingPort.recordOperation("CREATE_LOAN_REQUEST", traceId, "DUPLICATE", loanRequest.getCustomerId());
                return existingRequest.orElseThrow(() -> new LoanRequestException("Idempotency key conflict"));
            }

            LoanRequest savedRequest = persistencePort.save(loanRequest);
            log.info("Loan request created successfully: id={}, customerId={}, traceId={}",
                    savedRequest.getId(), savedRequest.getCustomerId(), traceId);

            tracingPort.recordOperation("CREATE_LOAN_REQUEST", traceId, "SUCCESS", loanRequest.getCustomerId());
            return savedRequest;

        } catch (LoanRequestException e) {
            tracingPort.recordOperation("CREATE_LOAN_REQUEST", traceId, "VALIDATION_ERROR", loanRequest.getCustomerId());
            throw e;
        } catch (Exception e) {
            tracingPort.recordOperation("CREATE_LOAN_REQUEST", traceId, "ERROR", loanRequest.getCustomerId());
            log.error("Error creating loan request: traceId={}, error={}", traceId, e.getMessage());
            throw new LoanRequestException("Failed to create loan request: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public LoanRequest getLoanRequestById(UUID id) {
        String traceId = UUID.randomUUID().toString();
        tracingPort.recordOperation("GET_LOAN_REQUEST", traceId, "START", id.toString());

        try {
            Optional<LoanRequest> requestOpt = persistencePort.findById(id);

            if (requestOpt.isEmpty()) {
                tracingPort.recordOperation("GET_LOAN_REQUEST", traceId, "NOT_FOUND", id.toString());
                throw new LoanRequestException("Loan request not found with id: " + id);
            }

            LoanRequest request = requestOpt.get();
            tracingPort.recordOperation("GET_LOAN_REQUEST", traceId, "SUCCESS", id.toString());
            log.info("Loan request retrieved: id={}, traceId={}", id, traceId);
            return request;

        } catch (LoanRequestException e) {
            throw e;
        } catch (Exception e) {
            tracingPort.recordOperation("GET_LOAN_REQUEST", traceId, "ERROR", id.toString());
            log.error("Error retrieving loan request: id={}, traceId={}, error={}", id, traceId, e.getMessage());
            throw new LoanRequestException("Failed to retrieve loan request: " + e.getMessage());
        }
    }

    @Override
    public void validateLoanRequest(LoanRequest loanRequest) {
        if (loanRequest.getCustomerId() == null || loanRequest.getCustomerId().isBlank()) {
            throw new LoanRequestException("Customer ID is required");
        }

        if (loanRequest.getAmount() == null || loanRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new LoanRequestException("Loan amount must be greater than zero");
        }

        if (loanRequest.getAmount().compareTo(new BigDecimal("100000")) > 0) {
            throw new LoanRequestException("Loan amount exceeds maximum allowed limit of 100,000");
        }

        if (loanRequest.getTermMonths() == null || loanRequest.getTermMonths() <= 0) {
            throw new LoanRequestException("Term months must be greater than zero");
        }

        if (loanRequest.getTermMonths() > 60) {
            throw new LoanRequestException("Term months cannot exceed 60 months");
        }

        if (loanRequest.getInterestRate() == null || loanRequest.getInterestRate().compareTo(BigDecimal.ZERO) <= 0) {
            throw new LoanRequestException("Interest rate must be greater than zero");
        }

        if (loanRequest.getInterestRate().compareTo(new BigDecimal("0.5")) > 0) {
            throw new LoanRequestException("Interest rate exceeds maximum allowed rate of 50%");
        }

        if (loanRequest.getProductType() == null || loanRequest.getProductType().isBlank()) {
            throw new LoanRequestException("Product type is required");
        }

        if (loanRequest.getIdempotencyKey() == null || loanRequest.getIdempotencyKey().isBlank()) {
            throw new LoanRequestException("Idempotency key is required for request processing");
        }

        if (loanRequest.getRequestDate() == null) {
            throw new LoanRequestException("Request date is required");
        }

        if (loanRequest.getRequestDate().isAfter(LocalDate.now())) {
            throw new LoanRequestException("Request date cannot be in the future");
        }

        log.debug("Loan request validation passed for customerId: {}", loanRequest.getCustomerId());
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/infrastructure/adapter/LoanRequestJpaAdapter.java ===
package com.pragma.loanrequest.infrastructure.adapter;

import com.pragma.loanrequest.domain.model.LoanRequest;
import com.pragma.loanrequest.domain.port.out.LoanRequestPersistencePort;
import com.pragma.loanrequest.infrastructure.persistence.entity.LoanRequestEntity;
import com.pragma.loanrequest.infrastructure.persistence.mapper.LoanRequestMapper;
import com.pragma.loanrequest.infrastructure.persistence.repository.LoanRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class LoanRequestJpaAdapter implements LoanRequestPersistencePort {

    private static final Logger log = LoggerFactory.getLogger(LoanRequestJpaAdapter.class);

    private final LoanRequestRepository loanRequestRepository;
    private final LoanRequestMapper loanRequestMapper;

    public LoanRequestJpaAdapter(
            LoanRequestRepository loanRequestRepository,
            LoanRequestMapper loanRequestMapper
    ) {
        this.loanRequestRepository = loanRequestRepository;
        this.loanRequestMapper = loanRequestMapper;
    }

    @Override
    public LoanRequest save(LoanRequest loanRequest) {
        try {
            LoanRequestEntity entity = loanRequestMapper.toEntity(loanRequest);
            LoanRequestEntity savedEntity = loanRequestRepository.save(entity);
            log.debug("Loan request persisted: id={}, customerId={}",
                    savedEntity.getId(), savedEntity.getCustomerId());
            return loanRequestMapper.toDomain(savedEntity);
        } catch (Exception e) {
            log.error("Error persisting loan request: customerId={}, error={}",
                    loanRequest.getCustomerId(), e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<LoanRequest> findById(UUID id) {
        try {
            Optional<LoanRequestEntity> entityOpt = loanRequestRepository.findById(id);
            if (entityOpt.isEmpty()) {
                log.debug("Loan request not found: id={}", id);
                return Optional.empty();
            }
            log.debug("Loan request found: id={}", id);
            return Optional.of(loanRequestMapper.toDomain(entityOpt.get()));
        } catch (Exception e) {
            log.error("Error finding loan request by id: id={}, error={}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean existsByIdempotencyKey(String idempotencyKey) {
        try {
            boolean exists = loanRequestRepository.existsByIdempotencyKey(idempotencyKey);
            log.debug("Idempotency key check: key={}, exists={}", idempotencyKey, exists);
            return exists;
        } catch (Exception e) {
            log.error("Error checking idempotency key: key={}, error={}", idempotencyKey, e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<LoanRequest> findByIdempotencyKey(String idempotencyKey) {
        try {
            Optional<LoanRequestEntity> entityOpt = loanRequestRepository.findByIdempotencyKey(idempotencyKey);
            if (entityOpt.isEmpty()) {
                log.debug("Loan request not found by idempotency key: key={}", idempotencyKey);
                return Optional.empty();
            }
            log.debug("Loan request found by idempotency key: key={}", idempotencyKey);
            return Optional.of(loanRequestMapper.toDomain(entityOpt.get()));
        } catch (Exception e) {
            log.error("Error finding loan request by idempotency key: key={}, error={}",
                    idempotencyKey, e.getMessage());
            throw e;
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/infrastructure/adapter/ExternalSystemAdapter.java ===
package com.pragma.loanrequest.infrastructure.adapter;

import com.pragma.loanrequest.domain.model.LoanRequest;
import com.pragma.loanrequest.domain.port.out.ExternalSystemPort;
import com.pragma.loanrequest.infrastructure.exception.LoanRequestException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ExternalSystemAdapter implements ExternalSystemPort {

    private static final Logger log = LoggerFactory.getLogger(ExternalSystemAdapter.class);
    private static final String RISK_BUREAU_CIRCUIT = "riskBureauCircuit";
    private static final String BANKING_CORE_CIRCUIT = "bankingCoreCircuit";

    @Override
    @CircuitBreaker(name = RISK_BUREAU_CIRCUIT, fallbackMethod = "riskBureauFallback")
    public RiskBureauResult checkRiskBureau(LoanRequest loanRequest) {
        log.info("Consultando buró de riesgos para customerId: {}", loanRequest.getCustomerId());
        
        if (loanRequest.getCustomerId() == null || loanRequest.getCustomerId().isBlank()) {
            throw new LoanRequestException("ID de cliente inválido para consulta al buró de riesgos", "INVALID_CUSTOMER_ID", 400);
        }
        
        simulateExternalCall(200);
        
        boolean hasHighRisk = evaluateRiskLevel(loanRequest);
        int score = calculateRiskScore(loanRequest);
        
        log.info("Resultado del buró de riesgos - CustomerId: {}, Score: {}, HighRisk: {}", 
                loanRequest.getCustomerId(), score, hasHighRisk);
        
        return new RiskBureauResult(!hasHighRisk, score, "SUCCESS");
    }

    @Override
    @CircuitBreaker(name = BANKING_CORE_CIRCUIT, fallbackMethod = "bankingCoreFallback")
    public BankingCoreResult checkBankingCore(LoanRequest loanRequest) {
        log.info("Consultando core bancario para customerId: {} con monto: {}", 
                loanRequest.getCustomerId(), loanRequest.getAmount());
        
        validateBankingCoreInput(loanRequest);
        
        simulateExternalCall(300);
        
        BigDecimal availableCredit = calculateAvailableCredit(loanRequest);
        boolean hasSufficientFunds = loanRequest.getAmount().compareTo(availableCredit.multiply(BigDecimal.valueOf(0.5))) <= 0;
        String accountStatus = determineAccountStatus(loanRequest.getCustomerId());
        
        log.info("Resultado del core bancario - CustomerId: {}, AvailableCredit: {}, HasSufficient: {}", 
                loanRequest.getCustomerId(), availableCredit, hasSufficientFunds);
        
        return new BankingCoreResult(hasSufficientFunds, availableCredit, accountStatus, "SUCCESS");
    }

    private boolean evaluateRiskLevel(LoanRequest loanRequest) {
        BigDecimal amount = loanRequest.getAmount();
        if (amount.compareTo(BigDecimal.valueOf(50000)) > 0) {
            return true;
        }
        
        Integer term = loanRequest.getTermMonths();
        if (term != null && term > 36) {
            return true;
        }
        
        return false;
    }

    private int calculateRiskScore(LoanRequest loanRequest) {
        int baseScore = 700;
        BigDecimal amount = loanRequest.getAmount();
        
        if (amount.compareTo(BigDecimal.valueOf(10000)) > 0) {
            baseScore -= 50;
        }
        if (amount.compareTo(BigDecimal.valueOf(30000)) > 0) {
            baseScore -= 50;
        }
        
        Integer term = loanRequest.getTermMonths();
        if (term != null && term > 24) {
            baseScore -= 30;
        }
        
        return Math.max(baseScore, 300);
    }

    private void validateBankingCoreInput(LoanRequest loanRequest) {
        if (loanRequest.getCustomerId() == null || loanRequest.getCustomerId().isBlank()) {
            throw new LoanRequestException("ID de cliente requerido para consulta al core bancario", "INVALID_CUSTOMER_ID", 400);
        }
        if (loanRequest.getAmount() == null || loanRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new LoanRequestException("Monto válido requerido para consulta al core bancario", "INVALID_AMOUNT", 400);
        }
    }

    private BigDecimal calculateAvailableCredit(LoanRequest loanRequest) {
        String customerId = loanRequest.getCustomerId();
        int hash = customerId.hashCode();
        BigDecimal baseAmount = BigDecimal.valueOf(Math.abs(hash % 100000) + 10000);
        return baseAmount;
    }

    private String determineAccountStatus(String customerId) {
        int hash = customerId.hashCode();
        int statusCode = Math.abs(hash % 3);
        return switch (statusCode) {
            case 0 -> "ACTIVE";
            case 1 -> "RESTRICTED";
            default -> "INACTIVE";
        };
    }

    private void simulateExternalCall(int durationMs) {
        try {
            Thread.sleep(durationMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Llamada externa interrumpida");
        }
    }

    private RiskBureauResult riskBureauFallback(LoanRequest loanRequest, Throwable t) {
        log.error("Fallback activado para buró de riesgos: {}", t.getMessage());
        return new RiskBureauResult(true, 650, "FALLBACK");
    }

    private BankingCoreResult bankingCoreFallback(LoanRequest loanRequest, Throwable t) {
        log.error("Fallback activado para core bancario: {}", t.getMessage());
        return new BankingCoreResult(true, BigDecimal.valueOf(100000), "UNKNOWN", "FALLBACK");
    }

    public record RiskBureauResult(boolean approved, int riskScore, String status) {}
    public record BankingCoreResult(boolean hasSufficientFunds, BigDecimal availableCredit, String accountStatus, String status) {}
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java ===
package com.pragma.loanrequest.infrastructure.adapter;

import com.pragma.loanrequest.domain.port.out.TracingPort;
import com.pragma.loanrequest.infrastructure.tracing.TracingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TracingAdapter implements TracingPort {

    private static final Logger log = LoggerFactory.getLogger(TracingAdapter.class);
    private static final String SERVICE_NAME = "loan-request-service";
    
    private final TracingService tracingService;

    public TracingAdapter(TracingService tracingService) {
        this.tracingService = tracingService;
    }

    @Override
    public void recordOperation(TracingRecord record) {
        log.debug("Registrando operación de trazabilidad: operationType={}, entityId={}", 
                record.operationType(), record.entityId());
        
        Map<String, Object> details = new HashMap<>();
        details.put("service", SERVICE_NAME);
        details.put("timestamp", Instant.now().toString());
        
        if (record.customerId() != null) {
            details.put("customerId", record.customerId());
        }
        if (record.amount() != null) {
            details.put("amount", record.amount().toString());
        }
        if (record.metadata() != null) {
            details.putAll(record.metadata());
        }
        
        tracingService.record(
            record.operationType(),
            record.entityId() != null ? record.entityId().toString() : null,
            record.status(),
            details
        );
        
        log.info("Operación registrada: type={}, entityId={}, status={}", 
                record.operationType(), record.entityId(), record.status());
    }

    @Override
    public void recordSuccess(String operationType, UUID entityId, String customerId, Map<String, Object> metadata) {
        TracingRecord record = new TracingRecord(
            operationType,
            entityId,
            customerId,
            null,
            "SUCCESS",
            metadata
        );
        recordOperation(record);
    }

    @Override
    public void recordFailure(String operationType, UUID entityId, String customerId, String errorCode, Map<String, Object> metadata) {
        Map<String, Object> enrichedMetadata = metadata != null ? new HashMap<>(metadata) : new HashMap<>();
        enrichedMetadata.put("errorCode", errorCode);
        
        TracingRecord record = new TracingRecord(
            operationType,
            entityId,
            customerId,
            null,
            "FAILED",
            enrichedMetadata
        );
        recordOperation(record);
    }

    @Override
    public void recordExternalCall(String operationType, String externalSystem, UUID entityId, 
                                    String customerId, long durationMs, boolean success, Map<String, Object> metadata) {
        Map<String, Object> enrichedMetadata = metadata != null ? new HashMap<>(metadata) : new HashMap<>();
        enrichedMetadata.put("externalSystem", externalSystem);
        enrichedMetadata.put("durationMs", durationMs);
        enrichedMetadata.put("success", success);
        
        String status = success ? "SUCCESS" : "FAILED";
        TracingRecord record = new TracingRecord(
            operationType,
            entityId,
            customerId,
            null,
            status,
            enrichedMetadata
        );
        recordOperation(record);
        
        log.info("Llamada externa registrada: system={}, operation={}, duration={}ms, success={}", 
                externalSystem, operationType, durationMs, success);
    }

    public record TracingRecord(
        String operationType,
        UUID entityId,
        String customerId,
        java.math.BigDecimal amount,
        String status,
        Map<String, Object> metadata
    ) {}
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestController.java ===
package com.pragma.loanrequest.infrastructure.controller;

import com.pragma.loanrequest.application.dto.LoanRequestDTO;
import com.pragma.loanrequest.application.service.LoanRequestService;
import com.pragma.loanrequest.domain.model.LoanRequest;
import com.pragma.loanrequest.infrastructure.exception.LoanRequestException;
import com.pragma.loanrequest.infrastructure.persistence.mapper.LoanRequestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/loan-requests")
@Tag(name = "Gestión de Solicitudes de Préstamos", description = "API para crear, consultar y validar solicitudes de préstamos")
public class LoanRequestController {

    private static final Logger log = LoggerFactory.getLogger(LoanRequestController.class);
    
    private final LoanRequestService loanRequestService;
    private final LoanRequestMapper loanRequestMapper;

    public LoanRequestController(LoanRequestService loanRequestService, LoanRequestMapper loanRequestMapper) {
        this.loanRequestService = loanRequestService;
        this.loanRequestMapper = loanRequestMapper;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva solicitud de préstamo", 
               description = "Crea una nueva solicitud de préstamo con validación de idempotencia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Solicitud creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
        @ApiResponse(responseCode = "409", description = "Solicitud duplicada (mismo idempotencyKey)"),
        @ApiResponse(responseCode = "422", description = "Validación de negocio fallida"),
        @ApiResponse(responseCode = "503", description = "Sistema externo no disponible")
    })
    public ResponseEntity<LoanRequestDTO> createLoanRequest(
            @Valid @RequestBody LoanRequestDTO requestDTO,
            @RequestHeader(value = "X-Idempotency-Key", required = false) String idempotencyKey) {
        
        log.info("Recibida solicitud de préstamo para customerId: {}, amount: {}", 
                requestDTO.getCustomerId(), requestDTO.getAmount());
        
        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            log.debug("Key de idempotencia proporcionada: {}", idempotencyKey);
        }
        
        try {
            LoanRequest loanRequest = loanRequestMapper.toDomain(requestDTO);
            
            if (idempotencyKey != null && !idempotencyKey.isBlank()) {
                loanRequest = new LoanRequest(
                    UUID.randomUUID(),
                    loanRequest.getCustomerId(),
                    loanRequest.getAmount(),
                    loanRequest.getTermMonths(),
                    loanRequest.getInterestRate(),
                    loanRequest.getRequestDate(),
                    loanRequest.getStatus(),
                    idempotencyKey,
                    loanRequest.getProductType(),
                    loanRequest.getChannel()
                );
            }
            
            LoanRequest createdRequest = loanRequestService.createLoanRequest(loanRequest);
            LoanRequestDTO responseDTO = loanRequestMapper.toDTO(createdRequest);
            
            log.info("Solicitud de préstamo creada exitosamente con ID: {}", createdRequest.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
            
        } catch (LoanRequestException e) {
            log.error("Error al crear solicitud de préstamo: code={}, message={}", e.getCode(), e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al crear solicitud de préstamo: {}", e.getMessage(), e);
            throw new LoanRequestException("Error interno al procesar la solicitud", "INTERNAL_ERROR", 500);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una solicitud de préstamo por ID", 
               description = "Recupera los detalles de una solicitud de préstamo específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
        @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public ResponseEntity<LoanRequestDTO> getLoanRequestById(
            @Parameter(description = "ID único de la solicitud de préstamo", required = true)
            @PathVariable UUID id) {
        
        log.info("Consultando solicitud de préstamo con ID: {}", id);
        
        try {
            LoanRequest loanRequest = loanRequestService.getLoanRequestById(id);
            
            if (loanRequest == null) {
                log.warn("Solicitud de préstamo no encontrada con ID: {}", id);
                throw new LoanRequestException("Solicitud de préstamo no encontrada", "NOT_FOUND", 404);
            }
            
            LoanRequestDTO responseDTO = loanRequestMapper.toDTO(loanRequest);
            log.info("Solicitud de préstamo recuperada exitosamente: {}", id);
            return ResponseEntity.ok(responseDTO);
            
        } catch (LoanRequestException e) {
            log.error("Error al obtener solicitud: code={}, message={}", e.getCode(), e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al obtener solicitud: {}", e.getMessage(), e);
            throw new LoanRequestException("Error interno al recuperar la solicitud", "INTERNAL_ERROR", 500);
        }
    }

    @PostMapping("/validate")
    @Operation(summary = "Validar una solicitud de préstamo", 
               description = "Valida una solicitud de préstamo contra sistemas externos (buró de riesgos y core bancario)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Validación completada"),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
        @ApiResponse(responseCode = "422", description = "Validación de negocio fallida"),
        @ApiResponse(responseCode = "503", description = "Sistema externo no disponible")
    })
    public ResponseEntity<ValidationResponse> validateLoanRequest(
            @Valid @RequestBody LoanRequestDTO requestDTO) {
        
        log.info("Validando solicitud de préstamo para customerId: {}", requestDTO.getCustomerId());
        
        try {
            LoanRequest loanRequest = loanRequestMapper.toDomain(requestDTO);
            loanRequestService.validateLoanRequest(loanRequest);
            
            ValidationResponse response = new ValidationResponse(
                loanRequest.getId() != null ? loanRequest.getId().toString() : null,
                "APPROVED",
                "La solicitud ha sido validada exitosamente"
            );
            
            log.info("Validación de solicitud completada exitosamente");
            return ResponseEntity.ok(response);
            
        } catch (LoanRequestException e) {
            log.error("Error en validación de solicitud: code={}, message={}", e.getCode(), e.getMessage());
            
            ValidationResponse errorResponse = new ValidationResponse(
                null,
                "REJECTED",
                e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
            
        } catch (Exception e) {
            log.error("Error inesperado en validación: {}", e.getMessage(), e);
            throw new LoanRequestException("Error interno al validar la solicitud", "INTERNAL_ERROR", 500);
        }
    }

    @GetMapping
    @Operation(summary = "Listar todas las solicitudes de préstamos", 
               description = "Retorna una lista de todas las solicitudes de préstamos registradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de solicitudes recuperada exitosamente")
    })
    public ResponseEntity<List<LoanRequestDTO>> getAllLoanRequests() {
        log.info("Listando todas las solicitudes de préstamos");
        throw new LoanRequestException("Funcionalidad no implementada", "NOT_IMPLEMENTED", 501);
    }

    public record ValidationResponse(String requestId, String status, String message) {}
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/infrastructure/exception/GlobalExceptionHandler.java ===
package com.pragma.loanrequest.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(LoanRequestException.class)
    public ResponseEntity<ErrorResponse> handleLoanRequestException(
            LoanRequestException ex, WebRequest request) {
        logger.error("Error de solicitud de préstamo: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(ex.getHttpStatus().value())
                .error(ex.getHttpStatus().getReasonPhrase())
                .message(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        logger.error("Error de validación: {}", ex.getMessage());
        
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null 
                            ? error.getDefaultMessage() 
                            : "Valor inválido",
                        (existing, replacement) -> existing
                ));
        
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Los datos de la solicitud no son válidos")
                .errors(errors)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        logger.error("Argumento ilegal: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(ex.getMessage())
                .errorCode("INVALID_ARGUMENT")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        logger.error("Error interno del servidor: ", ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("Error interno del servidor. Por favor, contacte al administrador.")
                .errorCode("INTERNAL_ERROR")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String errorCode;
        private String path;

        private ErrorResponse() {}

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getErrorCode() { return errorCode; }
        public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }

        public static class ErrorResponseBuilder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private String errorCode;
            private String path;

            public ErrorResponseBuilder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public ErrorResponseBuilder status(int status) {
                this.status = status;
                return this;
            }

            public ErrorResponseBuilder error(String error) {
                this.error = error;
                return this;
            }

            public ErrorResponseBuilder message(String message) {
                this.message = message;
                return this;
            }

            public ErrorResponseBuilder errorCode(String errorCode) {
                this.errorCode = errorCode;
                return this;
            }

            public ErrorResponseBuilder path(String path) {
                this.path = path;
                return this;
            }

            public ErrorResponse build() {
                ErrorResponse response = new ErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.errorCode = this.errorCode;
                response.path = this.path;
                return response;
            }
        }
    }

    public static class ValidationErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private Map<String, String> errors;
        private String path;

        private ValidationErrorResponse() {}

        public static ValidationErrorResponseBuilder builder() {
            return new ValidationErrorResponseBuilder();
        }

        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public Map<String, String> getErrors() { return errors; }
        public void setErrors(Map<String, String> errors) { this.errors = errors; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }

        public static class ValidationErrorResponseBuilder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private Map<String, String> errors;
            private String path;

            public ValidationErrorResponseBuilder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public ValidationErrorResponseBuilder status(int status) {
                this.status = status;
                return this;
            }

            public ValidationErrorResponseBuilder error(String error) {
                this.error = error;
                return this;
            }

            public ValidationErrorResponseBuilder message(String message) {
                this.message = message;
                return this;
            }

            public ValidationErrorResponseBuilder errors(Map<String, String> errors) {
                this.errors = errors;
                return this;
            }

            public ValidationErrorResponseBuilder path(String path) {
                this.path = path;
                return this;
            }

            public ValidationErrorResponse build() {
                ValidationErrorResponse response = new ValidationErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.errors = this.errors;
                response.path = this.path;
                return response;
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/infrastructure/exception/LoanRequestException.java ===
package com.pragma.loanrequest.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class LoanRequestException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus httpStatus;

    public LoanRequestException(String message, String errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public LoanRequestException(String message, String errorCode, HttpStatus httpStatus, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static LoanRequestException alreadyExists(String idempotencyKey) {
        return new LoanRequestException(
                "Ya existe una solicitud de préstamo con la clave de idempotencia: " + idempotencyKey,
                "DUPLICATE_IDEMPOTENCY_KEY",
                HttpStatus.CONFLICT
        );
    }

    public static LoanRequestException notFound(String id) {
        return new LoanRequestException(
                "No se encontró la solicitud de préstamo con ID: " + id,
                "LOAN_REQUEST_NOT_FOUND",
                HttpStatus.NOT_FOUND
        );
    }

    public static LoanRequestException invalidAmount(BigDecimal amount) {
        return new LoanRequestException(
                "El monto solicitado debe ser mayor a cero. Valor recibido: " + amount,
                "INVALID_AMOUNT",
                HttpStatus.BAD_REQUEST
        );
    }

    public static LoanRequestException invalidTerm(Integer termMonths) {
        return new LoanRequestException(
                "El plazo debe estar entre 1 y 360 meses. Valor recibido: " + termMonths,
                "INVALID_TERM",
                HttpStatus.BAD_REQUEST
        );
    }

    public static LoanRequestException invalidCustomerId(String customerId) {
        return new LoanRequestException(
                "El ID del cliente es obligatorio y no puede estar vacío. Valor recibido: " + customerId,
                "INVALID_CUSTOMER_ID",
                HttpStatus.BAD_REQUEST
        );
    }

    public static LoanRequestException externalServiceFailure(String serviceName, String message) {
        return new LoanRequestException(
                "Error en el servicio externo " + serviceName + ": " + message,
                "EXTERNAL_SERVICE_FAILURE",
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }

    public static LoanRequestException circuitBreakerOpen(String serviceName) {
        return new LoanRequestException(
                "El circuito está abierto para el servicio: " + serviceName + ". Por favor, intente más tarde.",
                "CIRCUIT_BREAKER_OPEN",
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }

    public static LoanRequestException validationError(String message) {
        return new LoanRequestException(
                message,
                "VALIDATION_ERROR",
                HttpStatus.BAD_REQUEST
        );
    }

    public static LoanRequestException internalError(String message) {
        return new LoanRequestException(
                message,
                "INTERNAL_ERROR",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/infrastructure/persistence/entity/LoanRequestEntity.java ===
package com.pragma.loanrequest.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "loan_requests", indexes = {
    @Index(name = "idx_idempotency_key", columnList = "idempotency_key", unique = true),
    @Index(name = "idx_customer_id", columnList = "customer_id"),
    @Index(name = "idx_status", columnList = "status"),
    @Index(name = "idx_request_date", columnList = "request_date")
})
public class LoanRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "customer_id", nullable = false, length = 50)
    private String customerId;

    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "term_months", nullable = false)
    private Integer termMonths;

    @Column(name = "interest_rate", nullable = false, precision = 10, scale = 4)
    private BigDecimal interestRate;

    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @Column(name = "idempotency_key", nullable = false, unique = true, length = 100)
    private String idempotencyKey;

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "channel", length = 50)
    private String channel;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Version
    @Column(name = "version")
    private Long version;

    public LoanRequestEntity() {}

    public LoanRequestEntity(String customerId, BigDecimal amount, Integer termMonths,
            BigDecimal interestRate, LocalDate requestDate, String status,
            String idempotencyKey, String productType, String channel) {
        this.customerId = customerId;
        this.amount = amount;
        this.termMonths = termMonths;
        this.interestRate = interestRate;
        this.requestDate = requestDate;
        this.status = status;
        this.idempotencyKey = idempotencyKey;
        this.productType = productType;
        this.channel = channel;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (requestDate == null) {
            requestDate = LocalDate.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/infrastructure/persistence/mapper/LoanRequestMapper.java ===
package com.pragma.loanrequest.infrastructure.persistence.mapper;

import com.pragma.loanrequest.domain.model.LoanRequest;
import com.pragma.loanrequest.infrastructure.persistence.entity.LoanRequestEntity;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class LoanRequestMapper {

    public LoanRequest toDomain(LoanRequestEntity entity) {
        if (entity == null) {
            return null;
        }
        return new LoanRequest(
            UUID.fromString(entity.getId()),
            entity.getCustomerId(),
            entity.getAmount(),
            entity.getTermMonths(),
            entity.getInterestRate(),
            entity.getRequestDate(),
            entity.getStatus(),
            entity.getIdempotencyKey(),
            entity.getProductType(),
            entity.getChannel()
        );
    }

    public LoanRequestEntity toEntity(LoanRequest domain) {
        if (domain == null) {
            return null;
        }
        LoanRequestEntity entity = new LoanRequestEntity();
        entity.setId(domain.getId().toString());
        entity.setCustomerId(domain.getCustomerId());
        entity.setAmount(domain.getAmount());
        entity.setTermMonths(domain.getTermMonths());
        entity.setInterestRate(domain.getInterestRate());
        entity.setRequestDate(domain.getRequestDate());
        entity.setStatus(domain.getStatus());
        entity.setIdempotencyKey(domain.getIdempotencyKey());
        entity.setProductType(domain.getProductType());
        entity.setChannel(domain.getChannel());
        return entity;
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/infrastructure/tracing/TracingService.java ===
package com.pragma.loanrequest.infrastructure.tracing;

import com.pragma.loanrequest.domain.port.out.TracingPort;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TracingService implements TracingPort {

    private static final Logger logger = LoggerFactory.getLogger(TracingService.class);
    private static final String SERVICE_NAME = "loanrequest-service";

    private final Map<String, TraceRecord> traceStore;

    public TracingService() {
        this.traceStore = new HashMap<>();
    }

    @Override
    public void recordOperation(String operationName, String requestId, String status, Map<String, Object> metadata) {
        String traceId = requestId != null ? requestId : UUID.randomUUID().toString();
        
        TraceRecord record = new TraceRecord(
            traceId,
            operationName,
            SERVICE_NAME,
            LocalDateTime.now(),
            status,
            metadata
        );
        
        traceStore.put(traceId, record);
        logger.info("Tracing recorded: operation={}, traceId={}, status={}", 
            operationName, traceId, status);
    }

    @Override
    public void recordSuccess(String operationName, String requestId, Map<String, Object> metadata) {
        recordOperation(operationName, requestId, "SUCCESS", metadata);
    }

    @Override
    public void recordError(String operationName, String requestId, String errorMessage, Map<String, Object> metadata) {
        Map<String, Object> errorMetadata = new HashMap<>();
        if (metadata != null) {
            errorMetadata.putAll(metadata);
        }
        errorMetadata.put("errorMessage", errorMessage);
        recordOperation(operationName, requestId, "ERROR", errorMetadata);
    }

    @Override
    public Map<String, Object> getTrace(String traceId) {
        TraceRecord record = traceStore.get(traceId);
        if (record == null) {
            return Map.of();
        }
        return record.toMap();
    }

    private static class TraceRecord {
        private final String traceId;
        private final String operationName;
        private final String serviceName;
        private final LocalDateTime timestamp;
        private final String status;
        private final Map<String, Object> metadata;

        public TraceRecord(String traceId, String operationName, String serviceName,
                          LocalDateTime timestamp, String status, Map<String, Object> metadata) {
            this.traceId = traceId;
            this.operationName = operationName;
            this.serviceName = serviceName;
            this.timestamp = timestamp;
            this.status = status;
            this.metadata = metadata != null ? metadata : Map.of();
        }

        public Map<String, Object> toMap() {
            Map<String, Object> map = new HashMap<>();
            map.put("traceId", traceId);
            map.put("operationName", operationName);
            map.put("serviceName", serviceName);
            map.put("timestamp", timestamp.toString());
            map.put("status", status);
            map.put("metadata", metadata);
            return map;
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/infrastructure/config/Resilience4jConfig.java ===
package com.pragma.loanrequest.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)
            .waitDurationInOpenState(Duration.ofSeconds(30))
            .slidingWindowSize(10)
            .minimumNumberOfCalls(5)
            .permittedNumberOfCallsInHalfOpenState(3)
            .automaticTransitionFromOpenToHalfOpenEnabled(true)
            .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
            .maxAttempts(3)
            .waitDuration(Duration.ofMillis(500))
            .ignoreExceptions(Exception.class)
            .build();
        return RetryRegistry.of(config);
    }

    @Bean
    public RateLimiterRegistry rateLimiterRegistry() {
        RateLimiterConfig config = RateLimiterConfig.custom()
            .limitRefreshPeriod(Duration.ofSeconds(1))
            .limitForPeriod(10)
            .timeoutDuration(Duration.ofMillis(500))
            .build();
        return RateLimiterRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanrequest/infrastructure/config/SwaggerConfig.java ===
package com.pragma.loanrequest.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Loan Request API")
                        .version("1.0.0")
                        .description("API para la gestión de solicitudes de préstamos. "
                                + "Permite crear, consultar y validar solicitudes de préstamos "
                                + "con soporte para idempotencia y trazabilidad.")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("desarrollo@pragma.com")
                                .url("https://www.pragma.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Token de autenticación JWT")));
    }
}

// === ARCHIVO: src/test/java/com/pragma/loanrequest/application/service/LoanRequestServiceTest.java ===
package com.pragma.loanrequest.application.service;

import com.pragma.loanrequest.domain.model.LoanRequest;
import com.pragma.loanrequest.domain.port.out.LoanRequestPersistencePort;
import com.pragma.loanrequest.domain.port.out.ExternalSystemPort;
import com.pragma.loanrequest.domain.port.out.TracingPort;
import com.pragma.loanrequest.infrastructure.exception.LoanRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitarios para LoanRequestService")
class LoanRequestServiceTest {

    @Mock
    private LoanRequestPersistencePort persistencePort;

    @Mock
    private ExternalSystemPort externalSystemPort;

    @Mock
    private TracingPort tracingPort;

    @InjectMocks
    private LoanRequestService loanRequestService;

    private LoanRequest loanRequest;
    private UUID loanRequestId;
    private String customerId;
    private String idempotencyKey;

    @BeforeEach
    void setUp() {
        loanRequestId = UUID.randomUUID();
        customerId = "CUST-001";
        idempotencyKey = UUID.randomUUID().toString();
        
        loanRequest = new LoanRequest(
                loanRequestId,
                customerId,
                new BigDecimal("10000.00"),
                12,
                new BigDecimal("0.15"),
                LocalDate.now(),
                "PENDING",
                idempotencyKey,
                "PERSONAL_LOAN",
                "DIGITAL"
        );
    }

    @Test
    @DisplayName("createLoanRequest - Crea solicitud exitosamente cuando no existe clave de idempotencia")
    void createLoanRequest_WhenIdempotencyKeyNotExists_CreatesSuccessfully() {
        when(persistencePort.existsByIdempotencyKey(idempotencyKey)).thenReturn(false);
        when(persistencePort.save(any(LoanRequest.class))).thenReturn(loanRequest);
        doNothing().when(tracingPort).recordOperation(anyString(), anyString(), any());
        doNothing().when(externalSystemPort).notifyExternalSystem(any(LoanRequest.class));

        LoanRequest result = loanRequestService.createLoanRequest(loanRequest);

        assertNotNull(result);
        assertEquals(customerId, result.getCustomerId());
        assertEquals(idempotencyKey, result.getIdempotencyKey());
        verify(persistencePort).save(any(LoanRequest.class));
        verify(tracingPort).recordOperation(anyString(), anyString(), any());
        verify(externalSystemPort).notifyExternalSystem(any(LoanRequest.class));
    }

    @Test
    @DisplayName("createLoanRequest - Retorna solicitud existente cuando la clave de idempotencia ya existe")
    void createLoanRequest_WhenIdempotencyKeyExists_ReturnsExisting() {
        when(persistencePort.existsByIdempotencyKey(idempotencyKey)).thenReturn(true);
        when(persistencePort.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.of(loanRequest));

        LoanRequest result = loanRequestService.createLoanRequest(loanRequest);

        assertNotNull(result);
        assertEquals(idempotencyKey, result.getIdempotencyKey());
        verify(persistencePort, never()).save(any(LoanRequest.class));
    }

    @Test
    @DisplayName("getLoanRequestById - Retorna solicitud cuando existe")
    void getLoanRequestById_WhenExists_ReturnsLoanRequest() {
        when(persistencePort.findById(loanRequestId)).thenReturn(Optional.of(loanRequest));

        LoanRequest result = loanRequestService.getLoanRequestById(loanRequestId);

        assertNotNull(result);
        assertEquals(loanRequestId, result.getId());
        verify(persistencePort).findById(loanRequestId);
    }

    @Test
    @DisplayName("getLoanRequestById - Lanza excepción cuando no existe")
    void getLoanRequestById_WhenNotExists_ThrowsException() {
        when(persistencePort.findById(loanRequestId)).thenReturn(Optional.empty());

        assertThrows(LoanRequestException.class, 
                () -> loanRequestService.getLoanRequestById(loanRequestId));
        verify(persistencePort).findById(loanRequestId);
    }

    @Test
    @DisplayName("validateLoanRequest - Valida correctamente una solicitud con datos válidos")
    void validateLoanRequest_WithValidData_ValidatesSuccessfully() {
        doNothing().when(persistencePort).save(any(LoanRequest.class));

        assertDoesNotThrow(() -> loanRequestService.validateLoanRequest(loanRequest));
    }

    @Test
    @DisplayName("validateLoanRequest - Lanza excepción cuando el monto es menor o igual a cero")
    void validateLoanRequest_WithInvalidAmount_ThrowsException() {
        LoanRequest invalidRequest = new LoanRequest(
                UUID.randomUUID(),
                customerId,
                BigDecimal.ZERO,
                12,
                new BigDecimal("0.15"),
                LocalDate.now(),
                "PENDING",
                UUID.randomUUID().toString(),
                "PERSONAL_LOAN",
                "DIGITAL"
        );

        assertThrows(LoanRequestException.class, 
                () -> loanRequestService.validateLoanRequest(invalidRequest));
    }

    @Test
    @DisplayName("validateLoanRequest - Lanza excepción cuando el plazo es menor a 1 mes")
    void validateLoanRequest_WithInvalidTerm_ThrowsException() {
        LoanRequest invalidRequest = new LoanRequest(
                UUID.randomUUID(),
                customerId,
                new BigDecimal("10000.00"),
                0,
                new BigDecimal("0.15"),
                LocalDate.now(),
                "PENDING",
                UUID.randomUUID().toString(),
                "PERSONAL_LOAN",
                "DIGITAL"
        );

        assertThrows(LoanRequestException.class, 
                () -> loanRequestService.validateLoanRequest(invalidRequest));
    }

    @Test
    @DisplayName("createLoanRequest - Registra trazabilidad al crear solicitud")
    void createLoanRequest_RecordsTracingOnCreation() {
        when(persistencePort.existsByIdempotencyKey(idempotencyKey)).thenReturn(false);
        when(persistencePort.save(any(LoanRequest.class))).thenReturn(loanRequest);
        doNothing().when(tracingPort).recordOperation(anyString(), anyString(), any());

        loanRequestService.createLoanRequest(loanRequest);

        verify(tracingPort, times(1)).recordOperation(
                eq("CREATE_LOAN_REQUEST"),
                anyString(),
                any());
    }
}

// === ARCHIVO: src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java ===
package com.pragma.loanrequest.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.loanrequest.application.dto.LoanRequestDTO;
import com.pragma.loanrequest.domain.model.LoanRequest;
import com.pragma.loanrequest.domain.port.in.LoanRequestUseCase;
import com.pragma.loanrequest.infrastructure.exception.GlobalExceptionHandler;
import com.pragma.loanrequest.infrastructure.exception.LoanRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoanRequestController.class)
@Import(GlobalExceptionHandler.class)
@DisplayName("Tests de integración para LoanRequestController")
class LoanRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoanRequestUseCase loanRequestUseCase;

    private LoanRequestDTO loanRequestDTO;
    private LoanRequest loanRequest;
    private UUID loanRequestId;
    private String idempotencyKey;

    @BeforeEach
    void setUp() {
        loanRequestId = UUID.randomUUID();
        idempotencyKey = UUID.randomUUID().toString();

        loanRequestDTO = new LoanRequestDTO();
        loanRequestDTO.setCustomerId("CUST-001");
        loanRequestDTO.setAmount(new BigDecimal("10000.00"));
        loanRequestDTO.setTermMonths(12);
        loanRequestDTO.setInterestRate(new BigDecimal("0.15"));
        loanRequestDTO.setProductType("PERSONAL_LOAN");
        loanRequestDTO.setChannel("DIGITAL");
        loanRequestDTO.setIdempotencyKey(idempotencyKey);

        loanRequest = new LoanRequest(
                loanRequestId,
                loanRequestDTO.getCustomerId(),
                loanRequestDTO.getAmount(),
                loanRequestDTO.getTermMonths(),
                loanRequestDTO.getInterestRate(),
                LocalDate.now(),
                "PENDING",
                idempotencyKey,
                loanRequestDTO.getProductType(),
                loanRequestDTO.getChannel()
        );
    }

    @Test
    @DisplayName("POST /api/v1/loan-requests - Crea solicitud exitosamente")
    void createLoanRequest_WhenValidRequest_ReturnsCreated() throws Exception {
        when(loanRequestUseCase.createLoanRequest(any(LoanRequest.class))).thenReturn(loanRequest);

        mockMvc.perform(post("/api/v1/loan-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value(loanRequestId.toString()))
                .andExpect(jsonPath("$.customerId").value("CUST-001"))
                .andExpect(jsonPath("$.status").value("PENDING"));

        verify(loanRequestUseCase, times(1)).createLoanRequest(any(LoanRequest.class));
    }

    @Test
    @DisplayName("POST /api/v1/loan-requests - Retorna 400 cuando el monto es inválido")
    void createLoanRequest_WhenInvalidAmount_ReturnsBadRequest() throws Exception {
        loanRequestDTO.setAmount(BigDecimal.ZERO);

        mockMvc.perform(post("/api/v1/loan-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequestDTO)))
                .andExpect(status().isBadRequest());

        verify(loanRequestUseCase, never()).createLoanRequest(any(LoanRequest.class));
    }

    @Test
    @DisplayName("POST /api/v1/loan-requests - Retorna 400 cuando el plazo es inválido")
    void createLoanRequest_WhenInvalidTerm_ReturnsBadRequest() throws Exception {
        loanRequestDTO.setTermMonths(0);

        mockMvc.perform(post("/api/v1/loan-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequestDTO)))
                .andExpect(status().isBadRequest());

        verify(loanRequestUseCase, never()).createLoanRequest(any(LoanRequest.class));
    }

    @Test
    @DisplayName("POST /api/v1/loan-requests - Retorna 400 cuando el customerId está vacío")
    void createLoanRequest_WhenEmptyCustomerId_ReturnsBadRequest() throws Exception {
        loanRequestDTO.setCustomerId("");

        mockMvc.perform(post("/api/v1/loan-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequestDTO)))
                .andExpect(status().isBadRequest());

        verify(loanRequestUseCase, never()).createLoanRequest(any(LoanRequest.class));
    }

    @Test
    @DisplayName("GET /api/v1/loan-requests/{id} - Retorna solicitud cuando existe")
    void getLoanRequestById_WhenExists_ReturnsOk() throws Exception {
        when(loanRequestUseCase.getLoanRequestById(loanRequestId)).thenReturn(loanRequest);

        mockMvc.perform(get("/api/v1/loan-requests/{id}", loanRequestId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(loanRequestId.toString()))
                .andExpect(jsonPath("$.customerId").value("CUST-001"))
                .andExpect(jsonPath("$.amount").value(10000.00));

        verify(loanRequestUseCase, times(1)).getLoanRequestById(loanRequestId);
    }

    @Test
    @DisplayName("GET /api/v1/loan-requests/{id} - Retorna 404 cuando no existe")
    void getLoanRequestById_WhenNotExists_ReturnsNotFound() throws Exception {
        when(loanRequestUseCase.getLoanRequestById(loanRequestId))
                .thenThrow(new LoanRequestException("Solicitud no encontrada", "NOT_FOUND"));

        mockMvc.perform(get("/api/v1/loan-requests/{id}", loanRequestId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(loanRequestUseCase, times(1)).getLoanRequestById(loanRequestId);
    }

    @Test
    @DisplayName("GET /api/v1/loan-requests/{id} - Retorna 400 cuando el ID es inválido")
    void getLoanRequestById_WhenInvalidId_ReturnsBadRequest() throws Exception {
        String invalidId = "invalid-uuid";

        mockMvc.perform(get("/api/v1/loan-requests/{id}", invalidId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(loanRequestUseCase, never()).getLoanRequestById(any(UUID.class));
    }

    @Test
    @DisplayName("POST /api/v1/loan-requests - Retorna 500 cuando hay error interno")
    void createLoanRequest_WhenInternalError_ReturnsInternalServerError() throws Exception {
        when(loanRequestUseCase.createLoanRequest(any(LoanRequest.class)))
                .thenThrow(new RuntimeException("Error interno del servidor"));

        mockMvc.perform(post("/api/v1/loan-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequestDTO)))
                .andExpect(status().isInternalServerError());

        verify(loanRequestUseCase, times(1)).createLoanRequest(any(LoanRequest.class));
    }

    @Test
    @DisplayName("POST /api/v1/loan-requests - Valida idempotencyKey en la solicitud")
    void createLoanRequest_WithIdempotencyKey_ProcessesIdempotently() throws Exception {
        when(loanRequestUseCase.createLoanRequest(any(LoanRequest.class))).thenReturn(loanRequest);

        mockMvc.perform(post("/api/v1/loan-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequestDTO)))
                .andExpect(status().isCreated());

        verify(loanRequestUseCase).createLoanRequest(argThat(
                request -> idempotencyKey.equals(request.getIdempotencyKey())));
    }
}
```
