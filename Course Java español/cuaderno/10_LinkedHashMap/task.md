## 11. `LinkedHashMap<K,V>` — Caché de recursos LRU

**¿Qué es?** `LinkedHashMap` combina una tabla hash con una lista doblemente enlazada que recuerda el orden de las entradas. Puede configurarse para mantener el **orden de inserción** o el **orden de acceso**; esta segunda modalidad ayuda a implementar cachés de tipo LRU (*Least Recently Used*: menos recientemente utilizado).

**Documentación oficial:** [LinkedHashMap — Oracle](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/LinkedHashMap.html).

### Archivo de trabajo: `EjerciciosLinkedHashMap.java`

Completa los siete huecos del archivo: el método del `record` y los métodos `ejercicioA` a `ejercicioF`. Ejecuta `main` para ver los resultados y usa **Check** para comprobar el comportamiento.

### Ejercicios

**A. `map` con lambda.** Obtén una lista con el formato `"CLAVE - recurso - X KiB"`, por ejemplo `"R01 - logo.png - 24.4140625 KiB"` (1024 bytes = 1 KiB). Usa una lambda y conserva el orden actual de acceso. **Resultado:** `List<String>`.

**B. `map` con método propio.** Implementa `EntradaCache.calcularCosteAcumulado()` como `accesos × costeRegeneracion` y aplícalo mediante `EntradaCache::calcularCosteAcumulado`. **Resultado:** `List<Long>`.

**C. `reduce` predefinido.** Calcula el tamaño total de la caché en bytes usando `Long::sum`. **Resultado:** `long`.

**D. `reduce` con lambda.** Encuentra la entrada con más accesos. Si hay empate, selecciona la de mayor tamaño. Debes conservar su clave y su valor. **Resultado:** `Map.Entry<String, EntradaCache>`.

**E. `map` + `reduce`.** Calcula el coste acumulado total de regeneración de la caché mediante `EntradaCache::calcularCosteAcumulado` y `Long::sum`. **Resultado:** `long`.

**F. `filter`.** Construye un `LinkedHashMap<String, EntradaCache>` nuevo con los recursos que tengan más de 50 accesos y ocupen menos de 500 000 bytes. Conserva las claves y el **orden actual de recorrido**. No es necesario que el mapa nuevo tenga modo de acceso LRU. **Resultado:** `LinkedHashMap<String, EntradaCache>`.

**Para pensar:** observa las tres llamadas a `get()` que aparecen al final del código inicial. ¿Por qué cambian el orden en que se recorrerán las entradas? ¿Qué problema puede aparecer si consultas el mismo mapa con `get()` mientras recorres su `entrySet()`?

---
