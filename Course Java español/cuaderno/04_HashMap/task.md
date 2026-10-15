# Parte II. Mapas y conjuntos

## 5. `HashMap<K,V>` — Inventario comercial

**¿Qué es?** `HashMap` almacena asociaciones entre una clave y un valor. Las claves son únicas, aunque los valores pueden repetirse. Utiliza una estructura hash, habitualmente eficiente para buscar, insertar y eliminar entradas por su clave. **No garantiza un orden de recorrido.**

**Documentación oficial:** [HashMap — Oracle](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/HashMap.html).

### Archivo de trabajo: `EjerciciosHashMap.java`

Completa los siete huecos del archivo: el método del `record` y los métodos `ejercicioA` a `ejercicioF`. Ejecuta `main` para ver los resultados y usa **Check** para comprobar el comportamiento.

### Ejercicios

**A. `map` con lambda.** Construye descripciones con formato `"CÓDIGO - NOMBRE - STOCK"` a partir de las entradas del mapa, usando una lambda y conservando el código del producto en cada cadena. **Resultado:** `List<String>`; su orden no es relevante.

**B. `map` con método propio.** Implementa `Producto.calcularValorInventario()` como `precio × stock` y transforma todos los productos utilizando `Producto::calcularValorInventario`. **Resultado:** `List<Double>`.

**C. `reduce` predefinido.** Calcula las unidades totales disponibles en el inventario con `Integer::sum`. **Resultado:** `int`.

**D. `reduce` con lambda.** Localiza el producto de mayor precio unitario. Si dos productos tienen el mismo precio, elige el de nombre alfabéticamente menor. **Resultado:** `Producto`.

**E. `map` + `reduce`.** Calcula el valor económico total del inventario mediante `Producto::calcularValorInventario` y `Double::sum`. **Resultado:** `double`.

**F. `filter`.** Crea un mapa nuevo que contenga solo los productos de categoría `"Informática"` con stock inferior a 20. **Conserva sus códigos originales como claves.** **Resultado:** `HashMap<String, Producto>`.

**Para pensar:** si solo recorres `inventario.values()`, ¿cómo recuperarías los códigos necesarios para construir el mapa del ejercicio F?

---

