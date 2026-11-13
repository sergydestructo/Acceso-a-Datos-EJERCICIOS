import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorConexion {

    private final Path archivo;
    private final String url;

    public GestorConexion(Path archivo) {
        this.archivo = archivo;
        this.url = "jdbc:sqlite:" + archivo.toAbsolutePath();
    }

    /* Este método tiene dos propósitos: el primero es comprobar que la conexion es posible, si no lo es
     el programa directamente no ejecutará.
     El segundo propósito es crear, si no existiese, de manera automática la base de datos SQLite, ya que al crear una
     conexión, SQLite comprueba si existe la base de datos, si no existe, la crea automáticamente. */
    public boolean primeraConexion() {
        if (!Files.isRegularFile(archivo)) {
            System.err.println("No existe la base de datos SQLite " + archivo.toAbsolutePath());
            return false;
        }

        try (Connection conexion = DriverManager.getConnection(url)) {
            try (Statement configuracion = conexion.createStatement()) {
                configuracion.execute("PRAGMA foreign_keys = ON");
            }

            System.out.println("Conexión SQLITE abierta: " + archivo.toAbsolutePath());
            System.out.println("Conexión válida: " + conexion.isValid(2));
            return true;
        } catch (SQLException e) {
            System.err.println("Error al conectar con SQLite: " + e.getMessage());
            return false;
        }

    }

    public Connection abrirConexion() throws SQLException {
        Connection conexion = DriverManager.getConnection(url);
        try (Statement configuracion = conexion.createStatement()) {
            configuracion.execute("PRAGMA foreign_keys = ON");
        }
        return conexion;
    }
}
