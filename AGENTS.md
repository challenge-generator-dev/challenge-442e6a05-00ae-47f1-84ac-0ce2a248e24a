# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de un Servicio de Gestión de Solicitudes**.

| | |
|---|---|
| Tema | Experiencia en Frameworks |
| Nivel | advanced-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean con capas de dominio, aplicación e infraestructura |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.5.6
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.9.1
- com.h2database:h2 n/a
- org.postgresql:postgresql n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.mockito:mockito-core n/a
- org.mockito:mockito-junit-jupiter n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Recepción y Persistencia de Solicitudes**: Servicio que recibe y persiste solicitudes de préstamos con garantías de idempotencia.
- **Fase 2 — Validación de Solicitudes**: Servicio que valida las solicitudes recibidas antes de persistirlas en la base de datos.
- **Fase 3 — Trazabilidad de Operaciones**: Servicio que genera y persiste registros de trazabilidad para cada operación realizada.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (36)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/loanrequest/application/service/LoanRequestService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/adapter/LoanRequestJpaAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/adapter/ExternalSystemAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/tracing/TracingService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/config/SwaggerConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/adapter/LoanRequestJpaAdapter.java` — `LoanRequestRepository.save`
      Se invoca `save` sobre `LoanRequestRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/adapter/LoanRequestJpaAdapter.java` — `LoanRequestRepository.findById`
      Se invoca `findById` sobre `LoanRequestRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `TracingRecord.operationType`
      Se invoca `operationType` sobre `TracingRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `TracingRecord.entityId`
      Se invoca `entityId` sobre `TracingRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `TracingRecord.customerId`
      Se invoca `customerId` sobre `TracingRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `TracingRecord.amount`
      Se invoca `amount` sobre `TracingRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `TracingRecord.metadata`
      Se invoca `metadata` sobre `TracingRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `TracingService.record`
      Se invoca `record` sobre `TracingService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java` — `TracingRecord.status`
      Se invoca `status` sobre `TracingRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestController.java` — `LoanRequestDTO.getCustomerId`
      Se invoca `getCustomerId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestController.java` — `LoanRequestDTO.getAmount`
      Se invoca `getAmount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestController.java` — `LoanRequestMapper.toDTO`
      Se invoca `toDTO` sobre `LoanRequestMapper`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanrequest/infrastructure/tracing/TracingService.java` — `TraceRecord.toMap`
      Se invoca `toMap` sobre `TraceRecord`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.setCustomerId`
      Se invoca `setCustomerId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.setAmount`
      Se invoca `setAmount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.setTermMonths`
      Se invoca `setTermMonths` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.setInterestRate`
      Se invoca `setInterestRate` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.setProductType`
      Se invoca `setProductType` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.setChannel`
      Se invoca `setChannel` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.setIdempotencyKey`
      Se invoca `setIdempotencyKey` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.getCustomerId`
      Se invoca `getCustomerId` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.getAmount`
      Se invoca `getAmount` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.getTermMonths`
      Se invoca `getTermMonths` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.getInterestRate`
      Se invoca `getInterestRate` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.getProductType`
      Se invoca `getProductType` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java` — `LoanRequestDTO.getChannel`
      Se invoca `getChannel` sobre `LoanRequestDTO`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (24)

- `pom.xml`
- `src/main/java/com/pragma/loanrequest/LoanRequestApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/loanrequest/domain/model/LoanRequest.java`
- `src/main/java/com/pragma/loanrequest/domain/port/in/LoanRequestUseCase.java`
- `src/main/java/com/pragma/loanrequest/domain/port/out/LoanRequestPersistencePort.java`
- `src/main/java/com/pragma/loanrequest/domain/port/out/ExternalSystemPort.java`
- `src/main/java/com/pragma/loanrequest/domain/port/out/TracingPort.java`
- `src/main/java/com/pragma/loanrequest/application/dto/LoanRequestDTO.java`
- `src/main/java/com/pragma/loanrequest/infrastructure/persistence/repository/LoanRequestRepository.java`
- `src/main/java/com/pragma/loanrequest/application/service/LoanRequestService.java`
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/LoanRequestJpaAdapter.java`
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/ExternalSystemAdapter.java`
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter/TracingAdapter.java`
- `src/main/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestController.java`
- `src/main/java/com/pragma/loanrequest/infrastructure/exception/GlobalExceptionHandler.java`
- `src/main/java/com/pragma/loanrequest/infrastructure/exception/LoanRequestException.java`
- `src/main/java/com/pragma/loanrequest/infrastructure/persistence/entity/LoanRequestEntity.java`
- `src/main/java/com/pragma/loanrequest/infrastructure/persistence/mapper/LoanRequestMapper.java`
- `src/main/java/com/pragma/loanrequest/infrastructure/tracing/TracingService.java`
- `src/main/java/com/pragma/loanrequest/infrastructure/config/Resilience4jConfig.java`
- `src/main/java/com/pragma/loanrequest/infrastructure/config/SwaggerConfig.java`
- `src/test/java/com/pragma/loanrequest/application/service/LoanRequestServiceTest.java`
- `src/test/java/com/pragma/loanrequest/infrastructure/controller/LoanRequestControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/loanrequest`
- `src/main/java/com/pragma/loanrequest/domain`
- `src/main/java/com/pragma/loanrequest/domain/model`
- `src/main/java/com/pragma/loanrequest/domain/port`
- `src/main/java/com/pragma/loanrequest/application`
- `src/main/java/com/pragma/loanrequest/application/service`
- `src/main/java/com/pragma/loanrequest/application/dto`
- `src/main/java/com/pragma/loanrequest/infrastructure`
- `src/main/java/com/pragma/loanrequest/infrastructure/adapter`
- `src/main/java/com/pragma/loanrequest/infrastructure/config`
- `src/main/java/com/pragma/loanrequest/infrastructure/controller`
- `src/main/java/com/pragma/loanrequest/infrastructure/exception`
- `src/main/java/com/pragma/loanrequest/infrastructure/persistence`
- `src/main/java/com/pragma/loanrequest/infrastructure/persistence/entity`
- `src/main/java/com/pragma/loanrequest/infrastructure/persistence/mapper`
- `src/main/java/com/pragma/loanrequest/infrastructure/tracing`
- `src/main/resources`
- `src/test/java/com/pragma/loanrequest`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean con capas de dominio, aplicación e infraestructura**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Brecha que el reto ataca: Chapter Backend, Especialidad Desarrollador, Tecnolog�a Java, Advanced � Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programaci�n principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
