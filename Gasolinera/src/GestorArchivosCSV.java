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

public class GestorArchivosCSV implements GestorArchivos{

    final private Path directorio = Path.of("datos");
    final private Path clientes = directorio.resolve("clientes.csv");
    final private Path pagos = directorio.resolve("pagos.csv");

    public GestorArchivosCSV() throws IOException{
        prepararArchivo();
    }



    @Override
    public List<Cliente> leerClientes() {
        List<Cliente> clientesLeidos = new ArrayList<>();

        try (BufferedReader lector = Files.newBufferedReader(clientes)) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                String[] datosCliente = linea.split(",");

                //replace se usa para quitar el ";" al final de la linea
                Cliente cliente = new Cliente(
                        Integer.parseInt(datosCliente[0].trim()),
                        datosCliente[1], datosCliente[2].trim(),
                        datosCliente[3].replace(";", "").trim()
                );

                clientesLeidos.add(cliente);
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

        return clientesLeidos;
    }

    @Override
    public boolean guardarCliente() {
        return false;
    }

    @Override
    public List<Pago> leerPagos() {
        List<Pago> pagosLeidos = new ArrayList<>();

        try (BufferedReader lector = Files.newBufferedReader(pagos)) {
            String linea;
            SimpleDateFormat formatio = new SimpleDateFormat("dd/MM/yyyy");

            while ((linea = lector.readLine()) != null) {
                String[] datosPago = linea.split(",");

                //replace se usa para quitar el ";" al final de la linea
                try {
                    Pago pago = new Pago(
                            Integer.parseInt(datosPago[0].trim()),
                            Integer.parseInt(datosPago[1].trim()),
                            formatio.parse(datosPago[2].trim()),
                            Double.parseDouble(datosPago[3].trim()),
                            Double.parseDouble(datosPago[4].trim()),
                            Combustible.valueOf(datosPago[5].replace(";", "").trim().toUpperCase())
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
    public boolean guardarPago() {
        return false;
    }

    @Override
    public void prepararArchivo() throws IOException {
        Files.createDirectories(directorio);

        if (Files.notExists(clientes)) {
            Files.createFile(clientes);
        }

        if (Files.notExists(pagos)) {
            Files.createFile(pagos);
        }
    }

    public static void main(String[] args) {
        GestorArchivosCSV prueba;

        try {
            prueba = new GestorArchivosCSV();
            List<Cliente> jeje = prueba.leerClientes();
            System.out.println(jeje);
            List<Pago> jaja = prueba.leerPagos();
            System.out.println(jaja);
        } catch (IOException e) {
            System.out.println("petada");
        }

    }
}


