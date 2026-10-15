# Cuaderno de ejercicios: programación funcional y colecciones en Java

**Módulo:** Programación / ampliación de Java  
**Nivel:** progresivo, de iniciación a avanzado  
**Versión del curso:** Java 21  
**Contenido:** 10 tipos de colecciones · 60 ejercicios · 10 programas de partida

---

## 1. Objetivos

Esta práctica tiene dos objetivos complementarios:

1. Aplicar las operaciones `map()`, `filter()` y `reduce()` de la API de Streams para resolver problemas reales.
2. Aprender las diferencias entre las colecciones de Java y cuándo conviene utilizar cada una.

**Regla fundamental: los Streams son un mecanismo de procesamiento, nunca el resultado final.** Cada ejercicio deberá obtener un **número, una cadena, un objeto del dominio o una colección**. No se admitirá devolver un `Stream` ni un `Optional` como resultado final.

### 1.1. Estructura de los bloques

Todos los bloques proponen **exactamente seis ejercicios**, con la misma organización:

| Ejercicio | Trabajo obligatorio | Resultado final |
|:--:|---|---|
| **A** | `map()` con una **lambda** | Una colección |
| **B** | `map()` con un **método que debes implementar** en la clase o `record` | Una colección |
| **C** | `reduce()` con un **método predefinido** de Java | Un número |
| **D** | `reduce()` con una **lambda** | Un objeto concreto (o el valor indicado) |
| **E** | `map()` + `reduce()`, combinando lambdas y métodos preexistentes | Un número o una cadena |
| **F** | `filter()` con un predicado | Una colección |

### 1.2. Normas de realización

- Cada bloque es un programa Java independiente, con su propia clase y su propio `main`.
- Abre el archivo `.java` de cada tarea y completa los huecos. El `main` ya crea y rellena la colección que necesitas.
- Completa los métodos que aparecen con `throw new UnsupportedOperationException()`: son tareas para el alumnado, no código terminado.
- Implementa las soluciones de los ejercicios **A–F en los métodos estáticos `ejercicioA` a `ejercicioF`**, llamados desde `main`.
- No utilices `for`, `while` ni `do-while` para resolver los ejercicios. Tampoco utilices `forEach()` como sustituto de un bucle, ni `peek()` para acumular resultados.
- En **C, D y E** se exige utilizar expresamente `reduce()`: no lo sustituyas por `sum()`, `max()`, `min()`, `count()` ni por `collect()`.
- En **A, B y F** termina el procesamiento con una operación terminal que construya el resultado pedido. Puedes utilizar `toList()` o `collect(...)` según necesites una lista, conjunto, mapa, cola, etc.
- Puedes encadenar operaciones intermedias adicionales, como `sorted()` o `mapToObj()`, si son necesarias, pero **no omitas la operación principal** exigida en el enunciado.
- No modifiques la colección original salvo que el enunciado lo indique expresamente. En los ejercicios de filtrado debes construir una **colección nueva**.
- En los ejercicios con mapas, decide si necesitas trabajar con `keySet()`, `values()` o `entrySet()`. Si debes conservar las claves originales, no las pierdas durante la transformación.
- En los conjuntos y colas ordenados, conserva los comparadores cuando el ejercicio lo requiera.
- Los datos de partida son deliberadamente pequeños para poder comprobar los resultados, pero la solución debe funcionar también con más elementos.

> **Nota sobre importes:** se utiliza `double` para que el foco de la práctica sea la programación funcional. En aplicaciones económicas reales suele ser preferible `BigDecimal` para evitar problemas de precisión decimal.

### 1.3. Organización de la entrega

Completa las diez tareas del curso y compruébalas con **Check**. Ejecuta también cada `main` para mostrar los resultados A–F. Las cuestiones de reflexión de la última pantalla requieren una respuesta razonada y revisión docente; Check solo comprueba el código.

---
