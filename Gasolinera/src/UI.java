import java.util.Date;
import java.util.Scanner;

public class UI {
    // Cambiar más adelante por la clase que trate el texto, dicha clase será la que se encargue de utilizar el scanner.
    final private GestorClientes clientes;
    final private GestorPagos pagos;
    final private GestorTexto input;

    UI() {
            this.input = new GestorTexto();
            this.clientes = new GestorClientes();
            this.pagos = new GestorPagos(clientes);
    }

    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\nGESTOR GASOLINERA");
            System.out.println("1. Dar de alta a un cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Buscar clientes");
            System.out.println("4. Procesar un pago");
            System.out.println("5. Consultar pagos");
            System.out.println("0. Salir");
            System.out.print("\nIntroduzca una opción: ");

            switch (input.inputOpciones()) {
                case 1:
                    altaCliente();
                    break;
                case 2:
                    listaCliente();
                    break;
                case 3:
                    buscaCliente();
                    break;
                case 4:
                    altaPago();
                    break;
                case 5:
                    listaPago();
                    break;
                case 0:
                    salir = true;
                    System.out.println("salir");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    public void altaCliente() {
        System.out.print("Introduzca el nombre: ");
        String nombre = input.inputTexto();

        System.out.print("Introduzca el teléfono: ");
        String tlfn = input.inputTexto();

        System.out.print("Introduzca la matrícula: ");
        String matricula = input.inputMatriculas();

        clientes.crearCliente(nombre, tlfn, matricula);
    }

    public void listaCliente() {
        System.out.println("LISTADO DE CLIENTES\n");

        clientes.listarClientes();
    }

    public void buscaCliente() {
        System.out.print("Introduzca un texto para buscar clientes: ");

        clientes.buscarCliente(input.inputTexto());
    }

    public void altaPago() {
        if (clientes.existenClientes()) {
            System.out.println("CLIENTES DISPONIBLES PARA CREAR PAGO");
            clientes.listarClientes();

            System.out.print("Introduzca el identificador del cliente: ");
            int idCliente = input.inputOpciones();

            if (clientes.existeCliente(idCliente)) {
                System.out.print("Introduzca una fecha: ");
                Date fecha = input.inputFecha();

                System.out.print("Introduzca el importe: ");
                Double importe = input.inputNumeroDecimal();

                System.out.print("Introduzca los litros: ");
                Double litros = input.inputNumeroDecimal();

                System.out.println("Introduzca el tipo de combustible");
                Combustible combustible = input.inputCombustible();

                pagos.crearPago(idCliente, fecha, importe, litros, combustible);

            } else {
                System.out.println("El identificador dado no corresponde a ningún cliente");
            }

        } else {
            System.out.println("Debe existir al menos un cliente para poder dar de alta un pago");
        }

    }

    public void listaPago() {
        System.out.println("LISTADO DE PAGOS\n");
        pagos.listarPagos();
    }


    public static void main(String[] args) {
        UI prueba = new UI();

        prueba.mostrarMenu();
    }
}
