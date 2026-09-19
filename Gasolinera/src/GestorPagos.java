import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class GestorPagos {
    private List<Pago> pagos;
    private GestorClientes gestorClientes;

    public GestorPagos(GestorClientes gestorClientes) {
        this.pagos = new ArrayList<Pago>();
        this.gestorClientes = gestorClientes;
    }

    public void crearPago(int idCliente, Date fecha, float importe, float litros, String combustible) {
        pagos.add(new Pago(pagos.size() + 1, idCliente, fecha, importe, litros, combustible));

        //Llamada a clase gestora de escribir en archivo, se hará más adelante

        System.out.println("Pago con identificador " + pagos.size() +
                "del cliente " + gestorClientes.buscarPorId(idCliente).getNombre() +
                " con importe " + importe);
    }

    public void mostrarPagos(List<Pago> pagos) {
        pagos.sort(new ComparadorPagosId());

        for (Pago pago : pagos) {
            System.out.println(pago);
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
