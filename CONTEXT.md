## CONTEXT.md - Repostorio de ejercicios y proyectos de Acceso a Datos

1. Descripción del repositorio

Acceso-a-Datos-EJERCICIOS es un repositorio educativo destinado a centralizar ejercicios, prácticas, proyectos y otros recursos relacionados con el módulo profesional de Acceso a Datos, perteneciente al Ciclo Formativo de Grado Superior (CFGS) de Desarrollo de Aplicaciones Multiplataforma (DAM).

El repositorio sirve como espacio de almacenamiento, desarrollo y consulta de las actividades realizadas durante el aprendizaje de los contenidos de la asignatura.

Su finalidad es mantener un histórico organizado de los ejercicios y proyectos, facilitar su mantenimiento y permitir su ampliación progresiva a medida que se avanza en el temario.

2. Objetivos

Los principales objetivos del repositorio son:

Centralizar todos los ejercicios y proyectos relevantes para la asignatura Acceso a Datos.
Aplicar de forma práctica los conceptos teóricos estudiados durante el curso.
Experimentar con diferentes mecanismos de persistencia y acceso a la información.
Desarrollar aplicaciones capaces de almacenar, recuperar, modificar y eliminar datos.
Mantener ejemplos funcionales que puedan servir como referencia para futuros ejercicios.
Facilitar la reutilización, ampliación y refactorización del código.
Documentar los conocimientos adquiridos mediante ejemplos prácticos.
3. Contexto académico y tecnológico

El repositorio está vinculado al módulo de Acceso a Datos del CFGS de Desarrollo de Aplicaciones Multiplataforma.

El lenguaje de programación principal es Java, aunque podrán incorporarse otras tecnologías y herramientas cuando los ejercicios o proyectos lo requieran.

Entre los contenidos que pueden abordarse se encuentran:

Gestión de ficheros y directorios.
Flujos de entrada y salida (I/O).
Serialización y deserialización de objetos.
Tratamiento de ficheros de texto y binarios.
Persistencia de información en diferentes formatos.
Acceso a bases de datos relacionales.
Conectividad mediante JDBC.
Operaciones CRUD.
Gestión de transacciones.
Persistencia mediante tecnologías ORM.
Acceso a datos en formatos estructurados como XML y JSON.
Integración de diferentes mecanismos de almacenamiento en aplicaciones.

No todos estos contenidos tienen que estar representados necesariamente en el estado actual del repositorio. Constituyen el ámbito tecnológico y académico al que pertenece.

4. Estructura del repositorio

El repositorio se organiza mediante directorios independientes para agrupar ejercicios y proyectos relacionados.

Entre los directorios y proyectos existentes se encuentran:

Directorio	Descripción
Course Java español/	Material o ejercicios relacionados con Java.
Ficheros binarios con Java/	Prácticas de gestión de ficheros binarios mediante Java.
Gasolinera/	Proyecto relacionado con la gestión de una gasolinera.
GasolineraJSON/	Variante del proyecto Gasolinera que trabaja con JSON.
Gasolinera con BBDD/	Variante del proyecto Gasolinera que incorpora una base de datos.
GestorArmadurasMH/	Proyecto de gestión de armaduras del videojuego Monster Hunter Wilds.

También pueden existir directorios auxiliares generados por los entornos de desarrollo.

Criterios de organización
Cada ejercicio o proyecto debe mantenerse en su propio directorio.
Los proyectos independientes deben poder identificarse fácilmente.
Cuando existan distintas implementaciones de un mismo ejercicio, deben mantenerse diferenciadas.
Se debe evitar mezclar código fuente de ejercicios no relacionados.
Los recursos necesarios para ejecutar un ejercicio deben permanecer junto a este, siempre que tenga sentido.
La estructura debe favorecer la comprensión del propósito de cada directorio.

La estructura podrá evolucionar conforme se incorporen nuevos contenidos.

5. Convenciones de desarrollo

Siempre que se trabaje en este repositorio, se procurará respetar las siguientes convenciones:

Java
Utilizar nombres de clases en UpperCamelCase.
Utilizar nombres de métodos y variables en lowerCamelCase.
Utilizar nombres descriptivos para clases, métodos, variables y atributos.
Mantener una separación lógica de responsabilidades.
Evitar duplicaciones innecesarias de código.
Aplicar encapsulación y los principios básicos de programación orientada a objetos.
Gestionar adecuadamente las excepciones.
Cerrar correctamente los recursos de entrada y salida, preferiblemente mediante try-with-resources.
Utilizar las bibliotecas estándar de Java cuando sean suficientes.
Organización del código
Mantener el código fuente separado de los archivos generados durante la compilación.
Evitar incluir archivos temporales, binarios generados o configuraciones personales del IDE cuando no sean necesarios.
Mantener las dependencias y configuraciones propias de cada proyecto.
Evitar introducir dependencias externas sin una justificación clara.
Comentarios y documentación
Escribir comentarios cuando aporten información relevante.
Evitar comentarios redundantes que simplemente describan instrucciones evidentes.
Documentar las funcionalidades importantes y las decisiones técnicas que puedan resultar difíciles de entender.
Mantener la documentación actualizada cuando se modifique el comportamiento de un proyecto.
6. Criterios para incorporar nuevos ejercicios

Al añadir un nuevo ejercicio o proyecto al repositorio:

Crear un directorio independiente para el ejercicio.
Utilizar un nombre descriptivo que permita identificar su contenido.
Mantener todos los archivos necesarios para su desarrollo y ejecución.
Respetar las convenciones de nomenclatura del lenguaje utilizado.
Incluir un README.md cuando el ejercicio tenga suficiente entidad para necesitar documentación propia.
Indicar, cuando corresponda, los requisitos necesarios para ejecutar el proyecto.
Evitar incorporar archivos generados automáticamente que puedan reconstruirse fácilmente.

Cuando un ejercicio sea una ampliación de otro ya existente, se debe valorar si conviene mantenerlo como proyecto independiente o integrarlo en el proyecto original.

La decisión debe priorizar la claridad y la facilidad de mantenimiento. La toma de esta decisión debe ser consensuada con el instructor, especialmente cuando se trate de ejercicios que forman parte de la evaluación del módulo.

Habrá ocasiones en las que el instructor sea quien decida la ubicación de un ejercicio, especialmente cuando se trate de prácticas que forman parte de la evaluación del módulo.

7. Instrucciones para asistentes de programación e IA

Cualquier asistente de programación que trabaje sobre este repositorio debe tener en cuenta las siguientes indicaciones:

Entender que se trata de un repositorio educativo, no necesariamente de una aplicación comercial o de producción.
Priorizar soluciones claras, comprensibles y didácticas.
Adaptar las implementaciones al nivel y los contenidos propios del CFGS de DAM.
No introducir arquitecturas, patrones o abstracciones innecesariamente complejas.
Respetar la estructura existente de cada ejercicio.
No modificar otros proyectos cuando la tarea esté limitada a uno concreto.
No asumir que todos los directorios utilizan la misma tecnología, versión de Java o sistema de construcción.
Inspeccionar los archivos existentes antes de proponer cambios.
Reutilizar las dependencias y herramientas ya presentes cuando sea apropiado.
No inventar requisitos funcionales que no estén especificados.
Explicar las decisiones técnicas cuando puedan contribuir al aprendizaje.
No sustituir una solución sencilla por una implementación excesivamente sofisticada.
Prioridad educativa

Cuando existan varias formas de resolver un problema, se debe priorizar aquella que permita comprender mejor el concepto de Acceso a Datos que se está trabajando.

El código debe ser correcto y funcional, pero también debe resultar comprensible para un estudiante del ciclo formativo.

8. Gestión de versiones y Git

Se recomienda mantener un historial de cambios comprensible.

Los commits deberían:

Describir brevemente el cambio realizado.
Estar relacionados con un ejercicio o proyecto concreto.
Evitar agrupar modificaciones independientes sin necesidad.
No incorporar credenciales, contraseñas, archivos personales ni información sensible.

Los archivos de configuración específicos de un entorno de desarrollo deben excluirse cuando no sean necesarios para el funcionamiento o la colaboración en el proyecto.

9. Filosofía del repositorio

Este repositorio debe entenderse como un espacio de aprendizaje y evolución continua.

No se pretende que todos los ejercicios sigan una arquitectura uniforme ni que todos tengan el mismo grado de complejidad.

Cada ejercicio debe responder a sus propios objetivos académicos, respetando las tecnologías y los conocimientos necesarios para resolverlo.

Se priorizan:

La comprensión de los conceptos de Acceso a Datos.
La corrección funcional de las soluciones.
La claridad y legibilidad del código.
La organización de los ejercicios.
La posibilidad de consultar y reutilizar los proyectos como material de aprendizaje.

En definitiva: este repositorio constituye un archivo personal y académico de ejercicios y proyectos del módulo de Acceso a Datos del CFGS de DAM, cuyo contenido irá creciendo conforme se incorporen nuevos conocimientos, tecnologías y actividades prácticas.