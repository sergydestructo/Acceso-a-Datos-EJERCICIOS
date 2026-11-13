import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path archivo = args.length == 0 ? Path.of("gasolinera.sqlite") : Path.of(args[0]);


        GestorConexion conexion = new GestorConexion(archivo);

        if (conexion.primeraConexion()) {
            UI ui = new UI();
            ui.mostrarMenu();
        }

    }
}                                                                                                      