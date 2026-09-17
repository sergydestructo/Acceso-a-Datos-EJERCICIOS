import java.util.ArrayList;

public class GestorClientes {
    ArrayList<Cliente> clientes;

    GestorClientes(){
        this.clientes = new ArrayList<Cliente>();
    }

    // mover SOUTS a clase UI, cambiando el tipo de retorno a boolean o no, tengo que preguntar
    public void crearCliente(String nombre, String tlfn, String matricula){
        if (!existeCliente(matricula)) {
            clientes.add(new Cliente(clientes.size() + 1, nombre, tlfn, matricula));
            System.out.println("Cliente con identificador " + clientes.size() + " añadido.");
        } else {
            System.out.println("Ya existe un cliente con esa matricula");
        }

    }

    // Implementar comparable/comparator para poder ordenar de manera correcta (Primero por nombre, si nombre coincide, por ID ascendente)
    public void listarClientes(){
        if (clientes.isEmpty()) {
            System.out.println("No existe ningún cliente");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente.toString());
            }
        }
    }

    // Refinar el metodo para que se ajuste a lo pedido en la practica, es decir, que ignore mayusculas y minusculas
    public void buscarCliente(String input){

        int clientesMostrados = 0;
        for (Cliente cliente : clientes) {
            if (cliente.getMatricula().contains(input) || cliente.getNombre().contains(input) || cliente.getTlfn().contains(input)) {
                clientesMostrados++;
                System.out.println(cliente);
            }

        }

        if (clientesMostrados == 0) {
            System.out.println("Ningun cliente cumple los requisitos de busqueda");
        }
    }

    public boolean existeCliente(String matricula) {
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

    public static void main(String[] args) {
        GestorClientes prueba = new GestorClientes();

        prueba.listarClientes();

        prueba.crearCliente("Sergio","634021859","ABC1234");
        prueba.crearCliente("Ivan", "123456789","DCBA321");
        prueba.crearCliente("Locura", "321312312", "ABC1234");

        prueba.listarClientes();

        prueba.buscarCliente("Sergio");

        prueba.buscarCliente("4");

        prueba.buscarCliente("H");

    }
}
