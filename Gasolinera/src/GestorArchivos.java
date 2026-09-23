import java.io.IOException;
import java.util.List;

public interface GestorArchivos {
    public List<Cliente> leerClientes();

    public boolean guardarCliente();

    public List<Pago> leerPagos();

    public boolean guardarPago();

    public void prepararArchivo() throws IOException;
}
