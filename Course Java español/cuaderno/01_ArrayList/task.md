# Parte I. Tres colecciones sencillas

## 2. `ArrayList<E>` — Calificaciones de alumnos

**¿Qué es?** `ArrayList` es una lista basada en un array redimensionable. Mantiene el orden de inserción, admite elementos repetidos y permite acceder rápidamente a una posición mediante su índice. Es una buena primera opción cuando necesitas una lista general de elementos.

**Documentación oficial:** [ArrayList — Oracle](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html).

### Archivo de trabajo: `EjerciciosArrayList.java`

Completa los siete huecos del archivo: el método del `record` y los métodos `ejercicioA` a `ejercicioF`. Ejecuta `main` para ver los resultados y usa **Check** para comprobar el comportamiento.

### Ejercicios

**A. `map` con lambda.** Transforma los alumnos en una `List<String>` con sus nombres en mayúsculas. Es obligatorio expresar la transformación mediante una lambda. **Resultado:** `List<String>`.

**B. `map` con método propio.** Implementa `Alumno.generarInforme()` con el formato `"Nombre (edad años): nota"`, por ejemplo `"Eva (31 años): 4.0"`. Transforma los alumnos en sus informes utilizando `Alumno::generarInforme`. **Resultado:** `List<String>`.

**C. `reduce` predefinido.** Suma todas las notas mediante `Double::sum`. **Resultado:** `double`.

**D. `reduce` con lambda.** Encuentra al alumno de mayor edad comparando dos alumnos mediante una lambda. **Resultado:** `Alumno`.

> **Pista (solo para este primer ejercicio de búsqueda):** una reducción sin valor identidad puede entregar un resultado que debes desenvolver. El método `.orElse(...)` permite obtener el objeto o un valor alternativo. Dado que en este ejercicio hay alumnos, puedes estudiar el uso de `.orElse(null)`.

**E. `map` + `reduce`.** Calcula la suma de las edades de todos los alumnos. Transforma cada alumno en su edad mediante una lambda y reduce los enteros con `Integer::sum`. **Resultado:** `int`.

**F. `filter`.** Obtén únicamente los alumnos cuya nota sea mayor o igual que 5, conservando su orden de aparición. **Resultado:** `List<Alumno>`.

---
