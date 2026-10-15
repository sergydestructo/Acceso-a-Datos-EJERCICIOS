# Parte III. Colas y estructuras avanzadas

## 9. `PriorityQueue<E>` — Planificador de tareas con heap

**¿Qué es?** `PriorityQueue` es una cola de prioridad implementada sobre un **heap binario**. Ofrece acceso rápido al elemento de la cabeza, según el comparador definido. Insertar y extraer la cabeza tiene coste logarítmico. **Su iteración no visita necesariamente los elementos en orden de prioridad**: un heap no es una lista completamente ordenada.

**Documentación oficial:** [PriorityQueue — Oracle](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/PriorityQueue.html).

### Archivo de trabajo: `EjerciciosPriorityQueue.java`

Completa los siete huecos del archivo: el método del `record` y los métodos `ejercicioA` a `ejercicioF`. Ejecuta `main` para ver los resultados y usa **Check** para comprobar el comportamiento.

### Ejercicios

**A. `map` con lambda.** Genera cadenas con el formato `"ID - prioridad - duración"`, por ejemplo `"T01 - 3 - 120"`. El resultado debe presentarse por prioridad descendente y, en caso de empate, por identificador ascendente. Puedes aplicar `sorted()` antes de transformar mediante una lambda. **Resultado:** `List<String>`.

**B. `map` con método propio.** Implementa `Tarea.calcularCosteComputacional()` como `duracionMinutos × consumoRecursos` y aplícalo mediante `Tarea::calcularCosteComputacional`. **Resultado:** `List<Long>`.

**C. `reduce` predefinido.** Calcula la duración total de las tareas utilizando `Long::sum`. **Resultado:** `long`.

**D. `reduce` con lambda.** Encuentra la tarea de mayor coste computacional. Si hay empate, elige la de prioridad más alta; si persiste, la de identificador alfabéticamente menor. **Resultado:** `Tarea`.

**E. `map` + `reduce`.** Calcula el coste computacional agregado mediante `Tarea::calcularCosteComputacional` y `Long::sum`. **Resultado:** `long`.

**F. `filter`.** Construye una `PriorityQueue<Tarea>` nueva con las tareas de prioridad mayor o igual que 4 y duración inferior a 100 minutos. **Conserva el comparador original**. **Resultado:** `PriorityQueue<Tarea>`.

**Para pensar:** ¿por qué el Stream de una `PriorityQueue` no garantiza el mismo orden que extraer sucesivamente elementos con `poll()`? ¿Qué procedimiento modificaría la cola original?

---
