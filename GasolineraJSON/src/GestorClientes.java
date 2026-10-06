import java.util.ArrayList;
import java.util.List;

public class GestorClientes {
    private List<Cliente> clientes;
    private GestorArchivos gestorArchivos;

    // Cuando haya que gestionar lectura y escritura, sopesar si es necesario sobrecargar el constructor o directamente hacerlo distinto (Con un parametro tampoco hay que liarse)
    public GestorClientes(GestorArchivos gestorArchivos) {
        this.clientes = new ArrayList<Cliente>();
        this.gestorArchivos = gestorArchivos;
    }

    public void leerClientes() {
        this.clientes = gestorArchivos.leerClientes();
    }

    public void crearCliente(String nombre, String tlfn, String matricula) {
        if (!existeCliente(matricula)) {
            Cliente cliente = new Cliente(clientes.size() + 1, nombre, tlfn, matricula);

            clientes.add(cliente);

            gestorArchivos.guardarCliente(cliente);

            //Llamada a clase gestora de escribir en archivo, se hará más adelante

            System.out.println("Cliente con identificador " + clientes.size() + " añadido.");
        } else {
            System.out.println("Ya existe un cliente con esa matricula");
        }
    }

    private void mostrarClientes(List<Cliente> clientes) {
        clientes.sort(new ComparadorClienteId());

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public void listarClientes() {
        if (!existenClientes()) {
            System.out.println("No existe ningún cliente");
        } else {
            mostrarClientes(clientes);
        }
    }

    // Refinar el metodo para que se ajuste a lo pedido en la practica, es decir, que ignore mayusculas y minusculas <- Se encargará el gestor de texto
    public void buscarCliente(String input){
        List<Cliente> resultados = new ArrayList<>();
        input = input.toLowerCase();

        for (Cliente cliente : clientes) {
            if (cliente.getMatricula().toLowerCase().contains(input) ||
                    cliente.getNombre().toLowerCase().contains(input) ||
                    cliente.getTlfn().contains(input)) {
                resultados.add(cliente);
            }
        }

        if (resultados.isEmpty()) {
            System.out.println("No existe ningún cliente que cumpla los requisitos de busqueda");
        } else {
            mostrarClientes(resultados);
        }
    }

    public Cliente buscarPorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    private boolean existeCliente(String matricula) {
        for (Cliente cliente : clientes) {
            if (cliente.getMatricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    // metodo sobrecargado para procesar pagos
    public boolean existeCliente(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean existenClientes() {
        return !clientes.isEmpty();
    }
}
