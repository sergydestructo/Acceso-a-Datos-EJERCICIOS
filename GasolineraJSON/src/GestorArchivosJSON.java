import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class GestorArchivosJSON implements GestorArchivos{
    final private Path directorio = Path.of("datos");
    final private Path clientes = directorio.resolve("clientes.json");
    final private Path pagos = directorio.resolve("pagos.json");


    final private String comienzoJSONCliente = "{" + System.lineSeparator() + "\t\"clientes\": [" + System.lineSeparator();
    final private String comienzoJSONPagos = "{" + System.lineSeparator() + "\t\"pagos\": [" + System.lineSeparator();
    final private String finalJSON = System.lineSeparator() + "\t]" + System.lineSeparator() + "}";

    public GestorArchivosJSON() throws IOException {
        prepararArchivo();
    }


    @Override
    public List<Cliente> leerClientes() {
        List<Cliente> clientesLeidos = new ArrayList<>();

        try (BufferedReader lector = Files.newBufferedReader(clientes)) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                if ((linea.length() == 1) || linea.contains("\t")) {
                    continue;
                }

                linea = linea.substring(linea.indexOf("{") + 1, linea.indexOf("}"));
                String[] campos = linea.split(",");

                // Todo el proceso de deserializar los datos debería moverlo a un método auxilia, esta solución es de cazurro
                int idCliente = Integer.parseInt(campos[0].substring(campos[0].indexOf(":") + 1).trim());
                String nombre = campos[1].substring(campos[1].indexOf(":") + 1).trim().replace("\"", "");
                String tfno = campos[2].substring(campos[2].indexOf(":") + 1).trim().replace("\"", "");
                String matricula = campos[3].substring(campos[3].indexOf(":") + 1).trim().replace("\"", "");

                Cliente cliente = new Cliente(idCliente, nombre, tfno, matricula);
                clientesLeidos.add(cliente);
            }
        } catch (IOException e) {
            System.out.println("Error de lectura en el archivo de clientes");
        }

        return clientesLeidos;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        List<Cliente> listaClientes = leerClientes();
        listaClientes.add(cliente);

        String linea = listaClientes.stream().map(
                (Cliente c) -> {
                    return "{\"id\": " + c.getId()
                            + ",\"nombre\": " + "\"" + c.getNombre() + "\""
                            + ",\"telefono\": " + "\"" + c.getTlfn() + "\""
                            + ",\"matricula\": " + "\"" + c.getMatricula() + "\""
                            + "}";
                }
        ).reduce(
                (String s1, String s2) -> {
                    return s1 + "," + System.lineSeparator() + s2;
                }
        ).orElse("");

        try {
            Files.writeString(clientes, comienzoJSONCliente, TRUNCATE_EXISTING);
            Files.writeString(clientes, linea, APPEND);
            Files.writeString(clientes, finalJSON, APPEND);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo");
        }

    }

    @Override
    public void guardarPago(Pago pago) {
        List<Pago> listaPagos = leerPagos();
        listaPagos.add(pago);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        String linea = listaPagos.stream().map(
                (Pago p) -> {
                    return "{\"id\": " + p.getId()
                            + ",\"idCliente\": " + p.getIdCliente()
                            + ",\"fecha\": " + "\"" + formato.format(p.getFecha()) + "\""
                            + ",\"importe\": " + p.getImporte()
                            + ",\"litros\": " + p.getLitros()
                            + ", \"combustible\": " + "\"" + p.getCombustible() + "\""
                            + "}";
                }
        ).reduce(
                (String s1, String s2) -> {
                    return s1 + "," + System.lineSeparator() + s2;
                }
        ).orElse("");

        try {
            Files.writeString(pagos, comienzoJSONPagos, TRUNCATE_EXISTING);
            Files.writeString(pagos, linea, APPEND);
            Files.writeString(pagos, finalJSON, APPEND);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo");
        }
    }

    @Override
    public List<Pago> leerPagos() {
        List<Pago> pagosLeidos = new ArrayList<>();

        try (BufferedReader lector = Files.newBufferedReader(pagos)) {
            String linea;
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            while ((linea = lector.readLine()) != null) {
                if (linea.length() == 1 || linea.contains("\t")) {
                    continue;
                }

                linea = linea.substring(linea.indexOf("{") + 1, linea.indexOf("}"));
                String[] campos = linea.split(",");

                try {
                    int idPago = Integer.parseInt(campos[0].substring(campos[0].indexOf(":") + 1).trim());
                    int idCliente = Integer.parseInt(campos[1].substring(campos[1].indexOf(":") + 1).trim());
                    Date fecha = formato.parse(campos[2].substring(campos[2].indexOf(":") + 1).trim().replace("\"", ""));
                    Double importe = Double.parseDouble(campos[3].substring(campos[3].indexOf(":") + 1).trim());
                    Double litros = Double.parseDouble(campos[4].substring(campos[4].indexOf(":") + 1).trim());
                    Combustible combustible = Combustible.valueOf(campos[5].substring(campos[5].indexOf(":") + 1).trim().replace("\"", ""));

                    Pago pago = new Pago(idPago, idCliente, fecha, importe, litros, combustible);

                    pagosLeidos.add(pago);
                } catch (ParseException e) {
                    System.out.println("Si sale esta excepción es que has modificado los datos SIN USAR ESTE PROGRAMA. ESO ESTA MUY MUY FEO (Tocará arreglarlo digo yo)");
                }

            }
        } catch (IOException e) {
            System.out.println("Error de lectura en el archivo de pagos");
        }

        return pagosLeidos;
    }

    @Override
    public void prepararArchivo() throws IOException {
        Files.createDirectories(directorio);

        if (Files.notExists(clientes)) {

            Files.createFile(clientes);

            // A ver si soy capaz de aprender a escribir bien en formato JSON jeje, locurita

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

            // A ver si soy capaz de aprender a escribir bien en formato JSON jeje, locurita

            /*
            String formatoJSON = "{[]}";

            Files.writeString(pagos, formatoJSON, APPEND);

             */
        }
    }
}

