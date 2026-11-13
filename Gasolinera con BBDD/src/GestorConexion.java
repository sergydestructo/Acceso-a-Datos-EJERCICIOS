import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorConexion {

    private Path archivo;
    private String url;

    public GestorConexion(Path archivo) {
        this.archivo = archivo;
        this.url = "jdbc:sqlite:" + archivo.toAbsolutePath();
    }

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
        return DriverManager.getConnection(url);
    }
}
