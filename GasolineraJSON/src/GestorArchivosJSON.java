import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.WRITE;
import static java.nio.file.StandardOpenOption.READ;

public class GestorArchivosJSON implements GestorArchivos{
    final private Path directorio = Path.of("datos");
    final private Path clientes = directorio.resolve("clientes.json");
    final private Path pagos = directorio.resolve("pagos.json");

    @Override
    public List<Cliente> leerClientes() {
        return List.of();
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        String linea = "{\"id\": " + cliente.getId()
                + ",\"nombre\": " + "\"" + cliente.getNombre() + "\""
                + ",\"telefono\": " + "\"" + cliente.getTlfn() + "\""
                + ",\"matricula\": " + "\"" + cliente.getMatricula() + "\""
                + "}";

        try {
            if (!Files.readString(clientes).isEmpty()) {
                linea = "," + System.lineSeparator() + linea;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.writeString(clientes, linea, APPEND);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo");
        }

    }

    @Override
    public void guardarPago(Pago pago) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        String linea = "{\"id\": " + pago.getId()
                + ",\"idCliente\": " + pago.getIdCliente()
                + ",\"fecha\": " + "\"" + formato.format(pago.getFecha()) + "\""
                + ",\"importe\": " + pago.getImporte()
                + ",\"litros\": " + pago.getLitros()
                + ", \"combustible\": " + "\"" + pago.getCombustible() + "\""
                + "}";

        try {
            if (!Files.readString(pagos).isEmpty()) {
                linea = "," + System.lineSeparator() + linea;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.writeString(pagos, linea, APPEND);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo");
        }
    }

    @Override
    public List<Pago> leerPagos() {
        return List.of();
    }

    @Override
    public void prepararArchivo() throws IOException {
        Files.createDirectories(directorio);

        if (Files.notExists(clientes)) {

            Files.createFile(clientes);

            /*  String formatoJSON = "{" + System.lineSeparator()
                    + "\"clientes\": [" + System.lineSeparator()
                    + System.lineSeparator()
                    + "]" + System.lineSeparator()
                    + "}" + System.lineSeparator();

            Files.writeString(clientes, formatoJSON, APPEND);

           */
        }

        if (Files.notExists(pagos)) {

            Files.createFile(pagos);

            /*
            String formatoJSON = "{[]}";

            Files.writeString(pagos, formatoJSON, APPEND);

             */
        }
    }

    public static void main(String[] args) {
        GestorArchivos prueba = new GestorArchivosJSON();

        try {
            prueba.prepararArchivo();

            Cliente pruebaCliente = new Cliente(1, "sasa", "2312", "abc");
            prueba.guardarCliente(pruebaCliente);

            Pago pruebaPago = new Pago(1,1,new Date(), 12.12, 12.12, Combustible.valueOf("DIESEL"));
            prueba.guardarPago(pruebaPago);

        } catch (IOException e) {
            System.out.println("Petada");
        }
    }
}

