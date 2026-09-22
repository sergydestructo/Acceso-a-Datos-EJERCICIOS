import java.util.List;

public class GestorArchivosCSV implements GestorArchivos{


    @Override
    public List<Cliente> leerClientes() {
        return List.of();
    }

    @Override
    public boolean guardarCliente() {
        return false;
    }

    @Override
    public List<Pago> leerPagos() {
        return List.of();

    }

    @Override
    public boolean guardarPago() {
        return false;
    }

    @Override
    public void prepararArchivo() {

    }
}
