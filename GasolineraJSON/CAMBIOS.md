Versión de JDK: 21 Ubicación de los ficheros: /datos Formato de los ficheros: .json

Commit inicial de la practica: fc0fcc63c8bad1fa91321ca2307242b6a3c4d12b

Decisiones de diseño:
  - Se ha utilizado programación funcional para los metodos de guardado en .json.

  - Para los métodos de lectura se ha añadido una condición para poder saltarse las lineas irrelevantes (Las que no contienen datos)

  - La creación de la clase MigrarCSVToJSON se ha conseguido gracias a sueños y esperanzas, lo que quiere decir que me he roto la cabeza para sacarlo como buenamente he podido usando las herramientas que conozco. Seguramente esté plagado de errores los cuales soy incapaz de detectar y mucho menos de solucionar.

  - La ruta de archivos .json no puede ser modificada por el usuario. Esto se debe a que el enunciado de la practica y el ejemplo de ejecución piden dos cosas distintas y a riesgo de hacer cosas sin saber, prefiero ceñirme al enunciado.

  - Se han añadido métodos get y set a los gestores correspondientes por necesidad para conseguir llevar a cabo la migración de datos.

  - Por último, la clase UI ha sido modificada aunque solo en un par de lineas para ajustarse al funcionamiento implementado con el ejercicio.
