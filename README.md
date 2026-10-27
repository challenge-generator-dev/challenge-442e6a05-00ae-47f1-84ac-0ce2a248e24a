# Implementación de un Servicio de Gestión de Solicitudes

En el contexto de una plataforma de gestión de solicitudes de préstamos, se requiere implementar un servicio que maneje la recepción, validación y persistencia de solicitudes. El servicio debe asegurar la idempotencia de las solicitudes, manejar los errores de los sistemas externos (como el buró de crédito y el core bancario) y garantizar la trazabilidad de las operaciones. Los actores involucrados son el originador de créditos, el motor antifraude, el buró de riesgos y el core bancario. El servicio debe soportar un throughput de 1 500 solicitudes por segundo en hora pico y una latencia máxima de 2 segundos por solicitud.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Experiencia en Frameworks |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Recepción y Persistencia de Solicitudes

**Objetivo:** Implementar la funcionalidad básica para recibir y persistir solicitudes de préstamos con garantías de idempotencia.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- El servicio debe recibir solicitudes de préstamos a través de un endpoint REST. Cada solicitud debe ser persistida en la base de datos con un identificador único que garantice la idempotencia. Si se recibe una solicitud con el mismo identificador, el servicio debe devolver la misma respuesta que se dio en la primera invocación.
- El servicio debe manejar los errores de los sistemas externos (buró de riesgos y core bancario) y garantizar que las solicitudes se persistan correctamente incluso en caso de fallos transitorios.

**Entregable:** Servicio que recibe y persiste solicitudes de préstamos con garantías de idempotencia.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo manejar la consistencia de los datos en caso de fallos transitorios.
- Piensa en cómo garantizar que el servicio sea escalable para soportar el throughput requerido.

</details>

### Fase 2: Validación de Solicitudes

**Objetivo:** Implementar la validación de las solicitudes recibidas antes de persistirlas en la base de datos.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- El servicio debe validar que las solicitudes recibidas cumplan con ciertos criterios antes de persistirlas. Por ejemplo, se debe verificar que el monto de la solicitud esté dentro de un rango válido y que el solicitante cumpla con ciertos requisitos mínimos.
- El servicio debe devolver un error si una solicitud no pasa las validaciones y no debe persistirla en la base de datos.

**Entregable:** Servicio que valida las solicitudes recibidas antes de persistirlas en la base de datos.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo implementar las validaciones de manera eficiente y escalable.
- Piensa en cómo manejar los errores de validación y garantizar que el servicio sea robusto.

</details>

### Fase 3: Trazabilidad de Operaciones

**Objetivo:** Implementar la trazabilidad de las operaciones realizadas por el servicio.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- El servicio debe generar registros de trazabilidad para cada operación realizada, incluyendo la recepción, validación y persistencia de solicitudes. Los registros deben incluir información relevante como el identificador de la solicitud, el timestamp de la operación y el resultado de la operación.
- El servicio debe garantizar que los registros de trazabilidad sean persistidos de manera segura y estén disponibles para su consulta en caso de ser necesario.

**Entregable:** Servicio que genera y persiste registros de trazabilidad para cada operación realizada.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo implementar la persistencia de los registros de trazabilidad de manera eficiente y escalable.
- Piensa en cómo garantizar la seguridad y disponibilidad de los registros de trazabilidad.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es la idempotencia y cómo se aplica en el contexto de este reto?
- **paraQueSirve**: ¿Para qué sirve la validación de solicitudes en este contexto?
- **comoSeUsa**: ¿Cómo se usa la trazabilidad de operaciones para mejorar la robustez del servicio?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden ocurrir durante la validación de solicitudes y cómo se manejan?
- **queDecisionesImplica**: ¿Qué decisiones implica la implementación de la trazabilidad de operaciones en este contexto?

## Criterios de Evaluacion

- Implementación correcta de la idempotencia de las solicitudes.
- Validación efectiva de las solicitudes recibidas.
- Generación y persistencia segura de registros de trazabilidad.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
