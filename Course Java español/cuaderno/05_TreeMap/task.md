## 6. `TreeMap<K,V>` — Movimientos bancarios

**¿Qué es?** `TreeMap` almacena pares clave-valor **ordenados por clave**, de acuerdo con el orden natural o con un comparador. Se basa en un árbol rojo-negro y ofrece búsquedas, inserciones y eliminaciones en tiempo logarítmico. Permite también consultar rangos de claves.

**Documentación oficial:** [TreeMap — Oracle](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/TreeMap.html).

### Archivo de trabajo: `EjerciciosTreeMap.java`

Completa los siete huecos del archivo: el método del `record` y los métodos `ejercicioA` a `ejercicioF`. Ejecuta `main` para ver los resultados y usa **Check** para comprobar el comportamiento.

### Ejercicios

**A. `map` con lambda.** Convierte cada entrada en una cadena con el formato `"FECHA: CONCEPTO (IMPORTE €)"`. Respeta el orden cronológico y utiliza una lambda. **Resultado:** `List<String>`.

**B. `map` con método propio.** Implementa `Transaccion.obtenerImporteConSigno()`: los ingresos se expresarán en positivo y los gastos en negativo. Aplícalo mediante `Transaccion::obtenerImporteConSigno`. **Resultado:** `List<Double>`.

**C. `reduce` predefinido.** Suma los importes registrados, sin aplicar signos (todos los importes del código de partida son positivos), utilizando `Double::sum`. **Resultado:** `double`.

**D. `reduce` con lambda.** Encuentra el movimiento con el **mayor importe absoluto**. Si hay empate, selecciona el más antiguo. Debes conservar tanto la fecha como los datos de la transacción. **Resultado:** `Map.Entry<LocalDate, Transaccion>`.

**E. `map` + `reduce`.** Calcula el saldo neto de los movimientos utilizando `Transaccion::obtenerImporteConSigno` y `Double::sum`. **Resultado:** `double`.

**F. `filter`.** Crea un mapa nuevo con los gastos de importe superior a 100 €, conservando fechas y orden cronológico. **Resultado:** `TreeMap<LocalDate, Transaccion>`.

**Para pensar:** ¿por qué tiene sentido usar aquí `TreeMap` y no `HashMap` si queremos examinar movimientos por fechas?

---

