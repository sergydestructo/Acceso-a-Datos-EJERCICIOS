# Parte IV. Comparativa final de colecciones

## 12. Características principales

| Colección | Tipo | Orden de iteración | ¿Admite duplicados? | Representación habitual |
|---|---|---|---|---|
| `ArrayList` | Lista | Orden posicional/de inserción | Sí | Array dinámico |
| `LinkedList` | Lista y deque | Orden de los nodos | Sí | Lista doblemente enlazada |
| `LinkedHashSet` | Conjunto | Inserción | No | Hash + enlaces de orden |
| `HashMap` | Mapa | No garantizado | Claves únicas; valores repetibles | Tabla hash |
| `TreeMap` | Mapa ordenado | Orden de las claves | Claves únicas; valores repetibles | Árbol rojo-negro |
| `HashSet` | Conjunto | No garantizado | No | Tabla hash |
| `TreeSet` | Conjunto ordenado | Orden natural/comparador | No | Árbol rojo-negro |
| `PriorityQueue` | Cola de prioridad | **No** garantiza recorrido por prioridad | Sí | Heap binario |
| `ArrayDeque` | Cola de doble extremo | Orden entre extremos | Sí | Array circular redimensionable |
| `LinkedHashMap` | Mapa | Inserción o acceso | Claves únicas; valores repetibles | Hash + enlaces de orden |

**Matices:** en `TreeSet` se consideran elementos equivalentes aquellos para los que la comparación devuelve `0`. Los mapas no son subtipos de `Collection`, aunque ofrecen vistas de sus claves, valores y entradas que permiten procesarlos con Streams.

## 13. Costes temporales de las principales operaciones

La **notación O grande** indica cómo crece el trabajo necesario cuando aumenta el número de elementos (`n`). Para interpretar la tabla:

- **O(1)** significa **tiempo constante**: una operación de «acceso directo», sin tener que recorrer toda la estructura. Por ejemplo, acceder a una posición de un `ArrayList` mediante su índice.
- **O(n)** significa **tiempo lineal**: en el peor caso, hay que recorrer todos los elementos para encontrar o procesar lo que necesitamos. Si se duplica el número de elementos, el trabajo puede duplicarse aproximadamente.
- **O(log n)** significa **tiempo logarítmico**: no hace falta recorrer toda la estructura; en cada paso se descarta una parte importante de los candidatos. Es lo que sucede al buscar en un árbol equilibrado.
- **O(n log n)** significa que el trabajo crece algo más rápido que O(n), pero mucho menos que O(n²). Es el coste habitual de **ordenar** `n` elementos mediante comparaciones.

`n` representa el número de elementos. Las complejidades de hash son **esperadas** bajo supuestos habituales; las de crecimiento de arrays pueden ser **amortizadas**. Son orientaciones para elegir estructuras, no una garantía de tiempo de ejecución en milisegundos.

En la columna **Tiempo de ordenación** se indica el coste de **obtener todos los elementos ordenados según un comparador**, no el de una inserción individual. En `TreeMap` y `TreeSet` el orden ya se mantiene en la propia estructura, por lo que basta con recorrerla para obtener una secuencia ordenada. En el resto se considera una ordenación general de sus elementos (o entradas, en el caso de los mapas).

| Colección | Búsqueda/consulta | Inserción | Eliminación | Tiempo de ordenación |
|---|---|---|---|---|
| `ArrayList` | O(1) por índice; O(n) por valor | O(1) amortizado al final; O(n) en medio | O(n) en general | **O(n log n)** |
| `LinkedList` | O(n) por índice/valor; O(1) en extremos | O(1) en extremos o con iterador situado | O(1) en extremos o con iterador situado | **O(n log n)** |
| `LinkedHashSet` | O(1) esperada | O(1) esperada | O(1) esperada | **O(n log n)** |
| `HashMap` | O(1) esperada por clave | O(1) esperada | O(1) esperada | **O(n log n)** |
| `TreeMap` | O(log n) por clave | O(log n) | O(log n) | **O(n)** al recorrerlo (ya ordenado por clave) |
| `HashSet` | O(1) esperada | O(1) esperada | O(1) esperada | **O(n log n)** |
| `TreeSet` | O(log n) | O(log n) | O(log n) | **O(n)** al recorrerlo (ya ordenado) |
| `PriorityQueue` | O(1) para consultar cabeza; O(n) para buscar valor | O(log n) | O(log n) para extraer cabeza; O(n) para eliminar un valor arbitrario | **O(n log n)** (el iterador no da orden de prioridad) |
| `ArrayDeque` | O(1) en extremos; O(n) por valor | O(1) amortizado en extremos | O(1) en extremos; O(n) por valor | **O(n log n)** |
| `LinkedHashMap` | O(1) esperada por clave | O(1) esperada | O(1) esperada | **O(n log n)** (inserción/acceso no equivale a ordenar por clave) |

**Importante:** crear un `TreeMap` o `TreeSet` insertando `n` elementos **no es O(n)**: construirlo mediante inserciones sucesivas cuesta O(n log n). El O(n) de la tabla se refiere exclusivamente a **recorrer una estructura que ya está ordenada**.

## 14. ¿Qué estructura elegirías?

| Necesidad principal | Colección recomendada | Motivo |
|---|---|---|
| Lista con acceso frecuente por índice | `ArrayList` | Acceso posicional eficiente |
| Lista enlazada, con trabajo frecuente en extremos | `LinkedList` | Inserción y eliminación en extremos |
| Elementos únicos conservando inserción | `LinkedHashSet` | Unicidad + orden de inserción |
| Relacionar claves con valores | `HashMap` | Búsqueda eficiente por clave |
| Mapa con claves ordenadas y consultas por rango | `TreeMap` | Orden y operaciones de árbol |
| Evitar duplicados, sin importar el orden | `HashSet` | Conjunto basado en hash |
| Conjunto ordenado | `TreeSet` | Orden natural o personalizado |
| Procesar primero el elemento de más prioridad | `PriorityQueue` | Consulta y extracción de cabeza eficientes |
| Utilizar una pila o una cola de dos extremos | `ArrayDeque` | Operaciones eficientes por los extremos |
| Mantener orden de inserción o acceso en un mapa | `LinkedHashMap` | Útil para políticas de caché LRU |

## 15. Cuestiones de reflexión final

Contesta razonadamente, **sin necesidad de escribir código**, a las siguientes preguntas:

1. ¿Qué diferencia existe entre una operación intermedia y una operación terminal de un Stream?
2. ¿Por qué un `map()` no modifica automáticamente los objetos de la colección original?
3. ¿Cuál es la diferencia conceptual entre `Map<K,V>` y `Stream.map()`?
4. ¿Qué propiedades debe cumplir una función reductora para ser correcta aunque los datos se agrupen de distintas maneras?
5. ¿Por qué una operación de reducción necesita tratar cuidadosamente los casos en que no hay elementos?
6. ¿Cuándo conviene conservar el comparador al reconstruir una colección?
7. ¿Por qué recorrer una `PriorityQueue` no equivale a ir extrayendo sus elementos según la prioridad?
8. ¿Qué se pierde si al procesar un `Map` se conservan únicamente sus valores?
9. ¿Qué diferencias hay entre `HashSet`, `LinkedHashSet` y `TreeSet` respecto al orden y a la unicidad?
10. ¿Por qué dos colecciones con los mismos elementos pueden producir listas resultantes en distinto orden?
11. ¿Cuándo sería preferible `ArrayDeque` a `LinkedList` para implementar una pila?
12. ¿Por qué una estructura más avanzada no es necesariamente mejor para un problema sencillo?

---

## 16. Lista de comprobación antes de entregar

- [ ] He creado los diez archivos `.java` y todos compilan con Java 17 o posterior.
- [ ] Cada archivo tiene un `main` que prepara los datos y muestra los seis resultados.
- [ ] He implementado los métodos pendientes de los `record`.
- [ ] He realizado los 60 ejercicios: diez veces los apartados A, B, C, D, E y F.
- [ ] No he utilizado bucles ni `forEach()` para resolver los ejercicios.
- [ ] Todos los ejercicios terminan en un número, una cadena, un objeto o una colección, nunca en un Stream ni en un envoltorio de resultado opcional.
- [ ] En los ejercicios C, D y E he usado expresamente `reduce()`.
- [ ] Los filtros construyen nuevas colecciones y no alteran los datos de partida.
- [ ] Cuando correspondía, he conservado las claves, el orden y los comparadores.
- [ ] He respondido a las cuestiones de reflexión final.

**Fin del cuaderno.**
