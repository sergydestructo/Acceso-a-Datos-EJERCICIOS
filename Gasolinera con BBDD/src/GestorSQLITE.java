import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GestorSQLITE implements GestorArchivos{
    Connection conexion;

    public GestorSQLITE(Connection conexion) {
        this.conexion = conexion;
    }


    @Override
    public List<Cliente> leerClientes() {
        return List.of();
    }

    @Override
    public void guardarCliente(Cliente cliente) {

    }

    @Override
    public List<Pago> leerPagos() {
        return List.of();
    }

    @Override
    public void guardarPago(Pago pago) {

    }

    /* El metodo sigue con el mismo nombre que se usó al crear la interfaz GestorArchivos pero su proposito es
       comprobar si existen las tablas y crearlas en caso negativo */
    @Override
    public void prepararArchivo() {
        String sql = "";
    }

    @Override
    public Path getClientes() {
        return null;
    }

    @Override
    public Path getPagos() {
        return null;
    }
}
