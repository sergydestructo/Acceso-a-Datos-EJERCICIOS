import java.nio.file.Path;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Path archivo = args.length == 0 ? Path.of("gasolinera.sqlite") : Path.of(args[0]);

        GestorConexion conexion = new GestorConexion(archivo);

        if (conexion.primeraConexion()) {
            try {
                GestorArchivos gestorArchivos = new GestorSQLITE(conexion.abrirConexion());

                UI ui = new UI(gestorArchivos);
                ui.mostrarMenu();
            } catch (SQLException e) {
                System.err.println("Error al crear la conexión con SQLITE");
            }
        }

    }
}                                                                                                      