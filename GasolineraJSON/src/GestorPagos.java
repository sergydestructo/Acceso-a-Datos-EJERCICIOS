import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class GestorPagos {
    private List<Pago> pagos;
    private GestorClientes gestorClientes;
    private GestorArchivos gestorArchivos;

    public GestorPagos(GestorClientes gestorClientes, GestorArchivos gestorArchivos) {
        this.pagos = new ArrayList<Pago>();
        this.gestorClientes = gestorClientes;
        this.gestorArchivos = gestorArchivos;
    }

    public void leerPagos() {
        this.pagos = gestorArchivos.leerPagos();
    }

    public void crearPago(int idCliente, Date fecha, Double importe, Double litros, Combustible combustible) {
        Pago pago = new Pago(pagos.size() + 1, idCliente, fecha, importe, litros, combustible);

        pagos.add(pago);

        gestorArchivos.guardarPago(pago);

        System.out.println("Pago con identificador " + pagos.size() +
                "del cliente " + gestorClientes.buscarPorId(idCliente).getNombre() +
                " con importe " + importe);
    }

    public void mostrarPagos(List<Pago> pagos) {
        pagos.sort(new ComparadorPagosId());
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (Pago pago : pagos) {
            Cliente cliente = gestorClientes.buscarPorId(pago.getIdCliente());

            System.out.println(
                    "ID: " + pago.getId() +
                            " CLIENTE: " + cliente.getNombre() +
                            " FECHA: " + formato.format(pago.getFecha()) +
                            " IMPORTE: " + pago.getImporte() +
                            " LITROS: " + pago.getLitros() +
                            " COMBUSTIBLE: " + pago.getCombustible()
            );
        }
    }

    public void listarPagos() {
        if (!existenPagos()) {
            System.out.println("No existe ningun pago");
        } else {
            mostrarPagos(pagos);
        }
    }

    private boolean existenPagos() {
        return !pagos.isEmpty();
    }
}
