import java.io.IOException;
import java.util.List;

public interface GestorArchivos {
    public List<Cliente> leerClientes();

    public void guardarCliente(Cliente cliente);

    public List<Pago> leerPagos();

    public void guardarPago(Pago pago);

    public void prepararArchivo() throws IOException;
}
