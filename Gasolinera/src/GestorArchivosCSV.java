import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.APPEND;

public class GestorArchivosCSV implements GestorArchivos {

    final private Path directorio = Path.of("datos");
    final private Path clientes = directorio.resolve("clientes.csv");
    final private Path pagos = directorio.resolve("pagos.csv");

    public GestorArchivosCSV() throws IOException {
        prepararArchivo();
    }


    @Override
    public List<Cliente> leerClientes() {
        List<Cliente> clientesLeidos = new ArrayList<>();

        try (BufferedReader lector = Files.newBufferedReader(clientes)) {
            //Inicializamos la variable con la primera línea para ignorar la cabecera CSV al leer el archivo
            String linea = lector.readLine();

            while ((linea = lector.readLine()) != null) {
                String[] datosCliente = linea.split(",");

                //replace se usa para quitar el ";" al final de la linea
                Cliente cliente = new Cliente(
                        Integer.parseInt(datosCliente[0]),
                        datosCliente[1], datosCliente[2],
                        datosCliente[3].replace(";", "")
                );

                clientesLeidos.add(cliente);
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

        return clientesLeidos;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        String linea = cliente.getId() + ","
                + cliente.getNombre() + ","
                + cliente.getTlfn() + ","
                + cliente.getMatricula() + ";"
                + System.lineSeparator();

        try {
            Files.writeString(clientes, linea, APPEND);
        } catch (IOException e) {
            System.out.println("No se ha guardao XD");
        }
    }

    @Override
    public List<Pago> leerPagos() {
        List<Pago> pagosLeidos = new ArrayList<>();

        try (BufferedReader lector = Files.newBufferedReader(pagos)) {
            //Inicializamos la variable con la primera línea para ignorar la cabecera CSV al leer el archivo
            String linea = lector.readLine();
            SimpleDateFormat formatio = new SimpleDateFormat("dd/MM/yyyy");

            while ((linea = lector.readLine()) != null) {
                String[] datosPago = linea.split(",");

                //replace se usa para quitar el ";" al final de la linea
                try {
                    Pago pago = new Pago(
                            Integer.parseInt(datosPago[0]),
                            Integer.parseInt(datosPago[1]),
                            formatio.parse(datosPago[2]),
                            Double.parseDouble(datosPago[3]),
                            Double.parseDouble(datosPago[4]),
                            Combustible.valueOf(datosPago[5].replace(";", "").toUpperCase())
                    );

                    pagosLeidos.add(pago);
                } catch (ParseException e) {
                    System.out.println("Esta excepción en principio no tiene que saltar NUNCA");
                }

            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        return pagosLeidos;

    }

    @Override
    public void guardarPago(Pago pago) {
        SimpleDateFormat formatio = new SimpleDateFormat("dd/MM/yyyy");

        String linea = pago.getId() + ","
                + pago.getIdCliente() + ","
                + formatio.format(pago.getFecha()) + ","
                + pago.getImporte() + ","
                + pago.getLitros() + ","
                + pago.getCombustible() + ";"
                + System.lineSeparator();

        try {
            Files.writeString(pagos, linea, APPEND);
        } catch (IOException e) {
            System.out.println("No se ha guardao XD");
        }
    }

    @Override
    public void prepararArchivo() throws IOException {
        Files.createDirectories(directorio);

        if (Files.notExists(clientes)) {
            Files.createFile(clientes);
            String cabecera = "Id,Nombre,Teléfono,Matrícula;" + System.lineSeparator();

            Files.writeString(clientes, cabecera, APPEND);
        }

        if (Files.notExists(pagos)) {
            Files.createFile(pagos);
            String cabecera = "Id,IdCliente,Fecha,Importe,Litros,Combustible;" + System.lineSeparator();

            Files.writeString(pagos, cabecera, APPEND);
        }
    }
}


