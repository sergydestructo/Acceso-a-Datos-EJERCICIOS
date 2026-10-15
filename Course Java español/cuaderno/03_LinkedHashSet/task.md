## 4. `LinkedHashSet<E>` — Biblioteca digital

**¿Qué es?** `LinkedHashSet` es un conjunto que elimina elementos duplicados y conserva el orden de inserción. Combina la búsqueda mediante hash con un encadenamiento que recuerda el orden en el que se añadieron los elementos.

**Documentación oficial:** [LinkedHashSet — Oracle](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/LinkedHashSet.html).

### Archivo de trabajo: `EjerciciosLinkedHashSet.java`

Completa los siete huecos del archivo: el método del `record` y los métodos `ejercicioA` a `ejercicioF`. Ejecuta `main` para ver los resultados y usa **Check** para comprobar el comportamiento.

### Ejercicios

**A. `map` con lambda.** Obtén los títulos de los libros en mayúsculas usando una lambda. Elimina títulos duplicados y conserva su primer orden de aparición. **Resultado:** `LinkedHashSet<String>`.

**B. `map` con método propio.** Implementa `Libro.generarReferencia()` con el formato `"Autor (año): Título"`. Aplica `Libro::generarReferencia`. **Resultado:** `LinkedHashSet<String>`.

**C. `reduce` predefinido.** Suma las páginas de todos los libros mediante `Integer::sum`. **Resultado:** `int`.

**D. `reduce` con lambda.** Localiza el libro con el año de publicación más antiguo. **Resultado:** `Libro`.

**E. `map` + `reduce`.** Crea una cadena con los títulos separados por `" | "`, sin separador al principio ni al final. Transforma los libros en títulos mediante `Libro::titulo` y utiliza una lambda para reducir las cadenas. **Resultado:** `String`.

**F. `filter`.** Conserva los libros publicados antes de 1970 y con más de 300 páginas, manteniendo el orden de inserción. **Resultado:** `LinkedHashSet<Libro>`.

**Para pensar:** si dos libros tienen el mismo título pero distintos ISBN, ¿por qué podrían mantenerse ambos en el conjunto original y convertirse en un único elemento tras A?

---

