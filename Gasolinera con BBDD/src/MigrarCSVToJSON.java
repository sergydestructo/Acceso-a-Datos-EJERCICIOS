import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class MigrarCSVToJSON {
    final private GestorArchivosCSV gestorArchivosCSV;
    final private GestorArchivos gestorArchivosJSON;
    final private Scanner scan;
    private int clientesMigrados;
    private int pagosMigrados;
    public MigrarCSVToJSON() {

        try {
            this.gestorArchivosCSV = new GestorArchivosCSV();
            this.gestorArchivosJSON = new GestorArchivosJSON();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.scan = new Scanner(System.in);
        this.clientesMigrados = 0;
        this.pagosMigrados = 0;
    }

    public void migracion() {
        if (Files.notExists(gestorArchivosJSON.getClientes()) && Files.notExists(gestorArchivosJSON.getPagos())) {
            System.out.print("¿Desea migrar datos CSV a JSON? (Y/n): ");
            String input;

            do {
                input = scan.nextLine();
            } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n") && !input.isEmpty());

            if (input.equalsIgnoreCase("y") || input.isEmpty()) {
                System.out.print("Introduzca el directorio de origen CSV: ");
                input = scan.nextLine();

                gestorArchivosCSV.setDirectorio(Path.of(input));

                if (Files.notExists(gestorArchivosCSV.getClientes()) || Files.notExists(gestorArchivosCSV.getPagos())) {
                    System.out.println("No existen los archivos necesarios para migrar");
                } else {
                    try {
                        gestorArchivosJSON.prepararArchivo();
                    } catch (IOException e) {
                        System.out.println("No se han podido crear o leer los archivos");
                    }
                    migrarClientes();
                    migrarPagos();
                }
            } else {
                try {
                    gestorArchivosJSON.prepararArchivo();
                } catch (IOException e) {
                    System.out.println("No se han podido crear o leer los archivos");
                }
            };
        }
    }

    private void migrarClientes() {
        List<Cliente> clientes = gestorArchivosCSV.leerClientes();

        for (Cliente c : clientes) {
            gestorArchivosJSON.guardarCliente(c);
            clientesMigrados++;
        }

        System.out.println("Clientes migrados: " + clientesMigrados);
    }

    private void migrarPagos() {
        List<Pago> pagos = gestorArchivosCSV.leerPagos();

        for (Pago p : pagos) {
            gestorArchivosJSON.guardarPago(p);
            pagosMigrados++;
        }

        System.out.println("Pagos migrados: " + pagosMigrados);
    }


}
