## 3. `LinkedList<E>` — Historial de compras

**¿Qué es?** `LinkedList` es una lista doblemente enlazada. Mantiene el orden y permite duplicados. Insertar o extraer elementos por sus extremos es eficiente, aunque acceder a una posición arbitraria requiere recorrer la lista. También implementa la interfaz `Deque`.

**Documentación oficial:** [LinkedList — Oracle](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/LinkedList.html).

### Archivo de trabajo: `EjerciciosLinkedList.java`

Completa los siete huecos del archivo: el método del `record` y los métodos `ejercicioA` a `ejercicioF`. Ejecuta `main` para ver los resultados y usa **Check** para comprobar el comportamiento.

### Ejercicios

**A. `map` con lambda.** Genera descripciones con el formato `"PRODUCTO (X unidades)"` mediante una lambda, respetando el orden de las compras. **Resultado:** `LinkedList<String>`.

**B. `map` con método propio.** Implementa `Compra.calcularImporte()` como el producto de la cantidad por el precio unitario. Utiliza `Compra::calcularImporte` para obtener todos los importes. **Resultado:** `List<Double>`.

**C. `reduce` predefinido.** Calcula el número total de unidades compradas mediante `Integer::sum`. **Resultado:** `int`.

**D. `reduce` con lambda.** Encuentra la compra de mayor **precio unitario**, comparando dos objetos `Compra`. **Resultado:** `Compra`.

**E. `map` + `reduce`.** Calcula el coste total de todas las compras utilizando `Compra::calcularImporte` y `Double::sum`. **Resultado:** `double`.

**F. `filter`.** Conserva exclusivamente las compras cuyo **importe total** supere los 60 €, respetando el orden original. **Resultado:** `LinkedList<Compra>`.

---

