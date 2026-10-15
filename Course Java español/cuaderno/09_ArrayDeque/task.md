## 10. `ArrayDeque<E>` — Historial de navegación

**¿Qué es?** `ArrayDeque` implementa una **cola de doble extremo** utilizando un array circular que puede crecer. Admite inserciones y extracciones tanto por el principio como por el final. Es una opción muy utilizada para implementar pilas (LIFO) y colas (FIFO), y no admite elementos `null`.

**Documentación oficial:** [ArrayDeque — Oracle](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayDeque.html).

### Archivo de trabajo: `EjerciciosArrayDeque.java`

Completa los siete huecos del archivo: el método del `record` y los métodos `ejercicioA` a `ejercicioF`. Ejecuta `main` para ver los resultados y usa **Check** para comprobar el comportamiento.

### Ejercicios

**A. `map` con lambda.** Obtén una lista con el formato `"URL: X min"`, por ejemplo `"/inicio: 2.0 min"`; convierte el tiempo a minutos con decimales. Usa una lambda y respeta el orden de recorrido de la cola. **Resultado:** `List<String>`.

**B. `map` con método propio.** Implementa `PaginaVisitada.calcularNivelInteraccion()` como `clics × 10 + segundos / 60.0`. Aplica `PaginaVisitada::calcularNivelInteraccion`. **Resultado:** `List<Double>`.

**C. `reduce` predefinido.** Calcula el total de bytes descargados utilizando `Long::sum`. **Resultado:** `long`.

**D. `reduce` con lambda.** Encuentra la página con más tiempo de permanencia; ante un empate, selecciona la que tenga más clics. **Resultado:** `PaginaVisitada`.

**E. `map` + `reduce`.** Calcula el tiempo total de navegación en minutos. Convierte cada duración de segundos a minutos mediante una lambda y suma con `Double::sum`. **Resultado:** `double`.

**F. `filter`.** Construye una `ArrayDeque<PaginaVisitada>` nueva con las páginas en las que se registraron más de cinco clics, conservando el orden del historial. **Resultado:** `ArrayDeque<PaginaVisitada>`.

**Para pensar:** ¿cómo cambia el comportamiento de la estructura si insertas usando `addFirst()` en lugar de `addLast()`? Explica cómo representarías una pila y una cola FIFO.

---
