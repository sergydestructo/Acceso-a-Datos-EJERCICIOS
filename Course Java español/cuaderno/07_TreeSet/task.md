## 8. `TreeSet<E>` / `SortedSet<E>` — Clasificación de estudiantes

**¿Qué es?** `TreeSet` es un conjunto ordenado basado en un árbol rojo-negro. Organiza sus elementos mediante el orden natural o un `Comparator`; ese criterio también determina cuándo dos elementos se consideran equivalentes dentro del conjunto. `SortedSet` es la interfaz de conjuntos ordenados que implementa, entre otras, esta clase.

**Documentación oficial:** [TreeSet — Oracle](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/TreeSet.html) · [SortedSet — Oracle](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/SortedSet.html).

### Archivo de trabajo: `EjerciciosTreeSet.java`

Completa los siete huecos del archivo: el método del `record` y los métodos `ejercicioA` a `ejercicioF`. Ejecuta `main` para ver los resultados y usa **Check** para comprobar el comportamiento.

### Ejercicios

**A. `map` con lambda.** Genera una lista con el formato `"Nombre - nota - APROBADO"` o `"Nombre - nota - SUSPENSO"`, por ejemplo `"Ana - 8.0 - APROBADO"`. Usa una lambda y respeta la clasificación original. **Resultado:** `List<String>`.

**B. `map` con método propio.** Implementa `Estudiante.calcularPuntuacionPonderada()` multiplicando su nota por los créditos. Transforma la colección con `Estudiante::calcularPuntuacionPonderada`. **Resultado:** `List<Double>`.

**C. `reduce` predefinido.** Calcula el total de créditos mediante `Integer::sum`. **Resultado:** `int`.

**D. `reduce` con lambda.** Encuentra al estudiante con más horas de estudio. En caso de empate, elige el de mayor nota; si continúa el empate, el de menor identificador. **Resultado:** `Estudiante`.

**E. `map` + `reduce`.** Calcula la suma de las puntuaciones ponderadas mediante `Estudiante::calcularPuntuacionPonderada` y `Double::sum`. **Resultado:** `double`.

**F. `filter`.** Construye un `TreeSet<Estudiante>` nuevo que solo incluya aprobados con más de 100 horas de estudio. **Debe conservar el comparador original**. **Resultado:** `TreeSet<Estudiante>`.

**Para pensar:** Ana y Lucía tienen la misma nota. ¿Qué ocurriría si el comparador solo tuviese en cuenta la nota, sin comparar también los identificadores?

---
