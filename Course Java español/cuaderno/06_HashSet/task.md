## 7. `HashSet<E>` — Red de sensores IoT

**¿Qué es?** `HashSet` guarda elementos sin duplicados mediante una estructura hash. No ofrece acceso por índice ni garantiza ningún orden de recorrido. Resulta adecuado cuando lo importante es evitar repeticiones y comprobar pertenencia.

**Documentación oficial:** [HashSet — Oracle](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/HashSet.html).

### Archivo de trabajo: `EjerciciosHashSet.java`

Completa los siete huecos del archivo: el método del `record` y los métodos `ejercicioA` a `ejercicioF`. Ejecuta `main` para ver los resultados y usa **Check** para comprobar el comportamiento.

### Ejercicios

**A. `map` con lambda.** Convierte cada sensor en `"ID: TEMPERATURA °C"`. Añade `" - ALERTA"` cuando su temperatura supere los 70 °C. Usa una lambda. **Resultado:** `HashSet<String>`.

**B. `map` con método propio.** Implementa `Sensor.calcularIndiceRiesgo()` con la fórmula `temperatura × (3.3 / voltaje)`. Obtén los índices mediante `Sensor::calcularIndiceRiesgo`. **Resultado:** `List<Double>`.

**C. `reduce` predefinido.** Calcula la humedad máxima utilizando `Double::max` dentro de un `reduce()`. **Resultado:** `double`.

**D. `reduce` con lambda.** Encuentra el sensor que tenga el menor voltaje. Si hay empate, elige el identificador alfabéticamente menor. **Resultado:** `Sensor`.

**E. `map` + `reduce`.** Calcula la suma de los índices de riesgo utilizando `Sensor::calcularIndiceRiesgo` y `Double::sum`. **Resultado:** `double`.

**F. `filter`.** Conserva únicamente sensores **activos** cuya temperatura sea superior a 60 °C. **Resultado:** `HashSet<Sensor>`.

**Para pensar:** ¿cómo decide un `HashSet<Sensor>` si dos sensores son el mismo elemento? ¿Basta con que compartan su `id`?

---

