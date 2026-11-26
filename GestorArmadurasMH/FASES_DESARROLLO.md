# GestorArmadurasMH - Fases de desarrollo

## Resumen
- Proyecto: Gestor de Armaduras de Monster Hunter Wilds
- Stack: Java 21, Maven, Swing, SQLite, Gson, SQLite JDBC
- API fuente: https://wilds.mhdb.io/ (documentación: https://docs.wilds.mhdb.io/)
- Idiomas soportados: español (`es`) e inglés (`en`)

## Fases realizadas

### Fase 1 - Base del proyecto Maven
- **Estado**: Completada
- **Proceso**:
  - Ajuste de `pom.xml` a Java 21 (`maven.compiler.source/target`).
  - Adición de dependencia Gson (`com.google.code.gson:gson:2.15.0`) para el parseo JSON de la API.
  - Verificación de compilación y commit/push del cambio.

### Fase 2 - Esquema SQLite inicial
- **Estado**: Completada
- **Proceso**:
  - Creación de `src/main/resources/db/schema.sql`.
  - Diseño canónico + i18n (`*_i18n`) con `lang IN ('es','en')`.
  - Tablas base: `skill`, `skill_rank`, `item`, `armor_set`, `armor_piece`, `charm`, `charm_rank`.
  - Tablas de relación: bonus set/grupo con umbrales explícitos, slots, skills, materiales de crafteo.
  - `sync_metadata` para trazabilidad de sincronización.
  - PK basada en `id` de API; `game_id` donde la API lo expone; sin `game_id` en `armor_piece`.
  - Validación de carga del esquema en SQLite (20 tablas creadas).

### Fase 3 - Dominio y contratos DAO
- **Estado**: Completada
- **Proceso**:
  - Creación de paquetes base (`config`, `domain.enums/model`, `dao.interfaces/sqlite/exception`, `api.dto/client`, `sync`, `ui`).
  - Enums: `ArmorKind`, `Rank`, `SkillKind`, `BonusKind`, `Language`.
  - Modelos de dominio: `Skill`, `SkillRank`, `Item`, `CraftingMaterial`, `ArmorSet`, `ArmorSetBonus`, `ArmorPiece`, `ResistanceType`, `Charm`, `CharmRank`.
  - Excepción propia `DaoException` (unchecked) para aislar JDBC.
  - Interfaces DAO: `SkillDao`, `ItemDao`, `ArmorSetDao`, `ArmorPieceDao`, `CharmDao`.

### Fase 4 - Infra JDBC/SQLite y DAOs de lectura
- **Estado**: Completada
- **Proceso**:
  - Adición de `org.xerial:sqlite-jdbc:3.54.1.0` al `pom.xml`.
  - `DatabaseConnectionProvider`: apertura de conexión SQLite en ruta configurable, `PRAGMA foreign_keys = ON`, errores como `DaoException`.
  - `DatabaseInitializer`: método explícito `initialize()`; si falta alguna tabla requerida, recrea el esquema desde `schema.sql`.
  - Implementaciones SQLite de lectura: `SqliteSkillDao`, `SqliteItemDao`, `SqliteArmorSetDao`, `SqliteArmorPieceDao`, `SqliteCharmDao`.
  - Uso de `PreparedStatement` y try-with-resources.
  - Verificación T4.5: `mvn clean compile` OK; smoke test temporal confirmó inicialización y creación de 20 tablas.

## Fases pendientes

### Fase 5 - Sincronización API -> SQLite
- **Estado**: Pendiente
- **Implementación pendiente**:
  - Crear DTOs Gson para endpoints: `/skills`, `/items`, `/armor/sets`, `/armor`, `/charms` (en `es` y `en`).
  - Implementar cliente HTTP con `java.net.http.HttpClient` y paginación (`limit/offset`) donde aplique.
  - Implementar importador/sincronizador idempotente y transaccional.
  - Orden de importación por dependencias: skills/ranks -> items -> armor sets base -> armor pieces/slots/skills/materiales -> charms/ranks/skills/materiales -> bonus set/grupo.
  - Upsert por `id` API; rellenar tablas canónicas e i18n; validar consistencia entre idiomas.
  - Registrar estado en `sync_metadata` (inicio, fin, éxito/error, mensaje).
- **Decisiones de diseño pendientes**:
  - Estrategia de upsert concreta: `ON CONFLICT DO UPDATE` por entidad + borrar/reinsertar relaciones por agregado, o upsert granular por relación.
  - Política ante datos eliminados en API: mantener, marcar obsoleto o eliminar en BD local.
  - Alcance de sync: completa manual, por endpoint, o incremental si la API lo permite.
  - Manejo de errores parciales: rollback total por idioma/endpoints o commit parcial con estado detallado.
  - Validación de datos: qué hacer si faltan campos/localizaciones o hay inconsistencias entre `es` y `en`.

### Fase 6 - Casos de uso y servicios
- **Estado**: Pendiente
- **Implementación pendiente**:
  - Capa de servicio/casos de uso entre UI y DAOs (evitar lógica en Swing).
  - Consultas orientadas a UI: listado de sets, detalle de set, detalle de pieza con materiales, búsqueda por skill/material, listado de charms.
  - Gestión de idioma activo y resolución de textos localizados.
  - Soporte para disparar sincronización explícita desde la aplicación.
- **Decisiones de diseño pendientes**:
  - Definir interfaz de servicios y DTOs/vistas de UI (no exponer entidades JDBC/raw directamente si complica la vista).
  - Estrategia de carga: entidades completas vs consultas ligeras para listados.
  - Caché en memoria o consultas directas a SQLite en cada vista.
  - Formato de errores hacia UI (mensajes de usuario vs detalles técnicos).

### Fase 7 - Interfaz Swing
- **Estado**: Pendiente
- **Implementación pendiente**:
  - Ventana principal y navegación por secciones (Sets, Piezas, Charms, Skills, Materiales/Sync).
  - Selector de idioma `es/en`.
  - Vistas de detalle: pieza con defensa, resistencias, slots, skills y materiales; set con bonus y piezas.
  - Filtros/búsqueda y manejo de estados vacíos/cargando/error.
  - Acción explícita de inicializar BD y sincronizar datos.
- **Decisiones de diseño pendientes**:
  - Estructura UI: MVC simple, MVP, o separación por panels/controladores.
  - Componentes Swing a usar (`JTable`, `JList`, `CardLayout`, etc.) según complejidad.
  - UX para sincronización (progreso, bloqueo, hilo en background con `SwingWorker`).
  - Empaquetado/ejecución: run desde IDE, Maven exec/plugin, o JAR ejecutable.

### Fase 8 - Pruebas, calidad y documentación
- **Estado**: Pendiente
- **Implementación pendiente**:
  - Pruebas de integración DAO con SQLite temporal.
  - Pruebas de importación: conteos API vs BD y muestreo de relaciones.
  - Validar constraints/FKs y comportamiento ante datos incompletos.
  - Ejecutar comandos de verificación Maven disponibles (`test/verify`) y revisar warnings relevantes.
  - Actualizar `GestorArmadurasMH/CONTEXT.md` con decisiones técnicas y estado real.
- **Decisiones de diseño pendientes**:
  - Framework de tests (JUnit 5 recomendado; requiere dependencia y aprobación).
  - Cobertura mínima objetivo y casos críticos.
  - Estrategia para tests que dependen de la API externa: fixtures locales, mocks o smoke tests controlados.
  - Convención de logging y mensajes de error para depuración.

## Notas de control
- No se realizan cambios de Git sin autorización explícita del usuario.
- No se añaden dependencias sin justificación y aprobación.
- Decorations quedan fuera del alcance actual; el diseño debe permitir incorporarlas más adelante.
