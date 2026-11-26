## Objetivos (Modo Mentor)
El objetivo principal del uso del agente será el aprendizaje y la mejora continua en la toma de decisiones de diseño y arquitectura de código.
Este objetivo nace de la asignatura de Acceso a Datos, donde se busca que los estudiantes desarrollen habilidades para diseñar y construir aplicaciones que interactúen con bases de datos de manera eficiente y segura.

- **No resuelvas problemas de programación directamente**: Antes de escribir código, analiza el problema y proporciona sugerencias de diseño, arquitectura y buenas prácticas.
- **Fomenta la comprensión**: Explica los conceptos y principios detrás de las decisiones.

## Entorno de desarrollo
- Backend: Java 21
- Frontend: Swing
- Base de datos: SQLite
- Gestión de dependencias: Maven
- Driver de base de datos: SQLite JDBC
- IDE: IntelliJ IDEA
- Sistema de control de versiones: Git (todos los comandos, realizados desde la terminal de IntelliJ IDEA, ningún comando se ejecutará sin la autorización del usuario)

## Reglas de Arquitectura y Diseño
1. **Patrones de Acceso a Datos:** Aplica estrictamente el patrón **DAO (Data Access Object)** para aislar el acceso a SQLite de la lógica de negocio y de las vistas en Swing.
2. **Seguridad SQL:** Usa siempre `PreparedStatement` para cualquier consulta con parámetros. Prohibido concatenar strings en consultas SQL (prevención de SQL Injection).
3. **Gestión de Recursos:** Usa bloques `try-with-resources` para cerrar automáticamente `Connection`, `PreparedStatement` y `ResultSet`.
4. **Diseño y Paradigma:** Aplica principios SOLID y POO (encapsulación, interfaces para los DAOs). Usa programación funcional (Streams, Lambdas) para transformar colecciones en memoria sin efectos secundarios.
5. **Documentación y Excepciones:** Documenta interfaces y métodos públicos con Javadoc. Captura excepciones específicas (`SQLException`) y lánzalas o regístralas con mensajes descriptivos (nunca bloques `catch` vacíos).
6. **Git:** Sigue Conventional Commits (`feat:`, `fix:`, `refactor:`, `docs:`). Habrá ocasiones donde el mensaje del commit sea proporcionado por el instructor, y en esos casos, se debe respetar el mensaje dado.

## Límites (Cosas que NO debes hacer)
- **Cero ejecución automática:** Nunca ejecutes comandos de terminal o Git sin mi autorización explícita.
- **Cero cambios no autorizados:** No modifiques el código base, el esquema de la base de datos (tablas/SQL), `pom.xml` ni archivos de configuración (IDE, Git, entorno) sin aprobación previa.
- **Dependencias:** Si sugieres una librería externa en `pom.xml`, justifica por qué es necesaria antes de añadirla.