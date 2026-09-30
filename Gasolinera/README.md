Versión de JDK: 21
Ubicación de los ficheros: /datos
Formato de los ficheros: .csv

Decisiones de diseño:
  - Se han creado clases gestoras para varias cosas, principalmente gestores para pagos, clientes, input del usuario y apertura/escritura de archivos. Esta ultima clase implementa una interfaz con los métodos generales necesarios para una buena permanencia de nuestro datos.
    
  - Las clases Cliente y Pago implementan un criterio de ordenación natural que se ajusta a lo pedido en el enunciado, junto a dos clases comparadoras utilizadas para añadir otra capa de ordenación cuando sea necesario.
    
  - No se han usado funciones lambda debido al desconocimiento de la existencia de las mismas. En futuras practicas evaluaré si usarlas o no.
    
  - Las clases gestoras de clientes y pagos almacenan dichos tipos de objeto en un ArrayList, aunque también pueden ser usados otros tipos de colecciones como un Tree Map, aunque habría que pensar en como implementar distintos criterios de ordenación, ya que un map solo puede ordenar por clave, no por valores.
    
  - Una decisión de diseño que tomo siempre es la de separar la UI en su propia clase. Aunque hay métodos de otras clases que muestran cosas por pantalla, solo muestran cosas que son del contexto del método. (Ej: Un método que se asegura que un String no esta vacío será quien comunique dicha información)
    
  - Existe un enumerado para los tipos de combustibles del ejercicio. Si bien son solo dos y quizá no sea lo más optimo, decidí usarlos para coger practica con ellos.

Modificaciones si cambia el formato de almacenamiento: (Por ejemplo, JSON)
  - El cambio más notable sería la creación de una nueva clase GestorArchivosJSON que implemente la interfaz GestorArchivos y ajustar sus métodos al formato elegido, pero el cambio necesario en el código existente sería de literalmente una linea, específicamente la línea 17 de la clase UI, donde creamos un objeto GestorArchivosCSV en una variable de tipo GestorArchivo.
