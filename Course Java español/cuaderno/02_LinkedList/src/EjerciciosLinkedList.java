import java.util.stream.Collectors;
import java.util.*;

public class EjerciciosLinkedList {

    record Compra(String producto, int cantidad, double precioUnitario) {

        // Ejercicio B: implementar este método.
        double calcularImporte() {
            return cantidad * precioUnitario;
        }
    }

    public static LinkedList<String> ejercicioA(LinkedList<Compra> compras) {
        return compras.stream()
                .map(compra -> compra.producto.toUpperCase() + " (" + compra.cantidad + " unidades)")
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static List<Double> ejercicioB(LinkedList<Compra> compras) {
        return compras.stream()
                .map(Compra::calcularImporte)
                .toList();
    }

    public static int ejercicioC(LinkedList<Compra> compras) {
        return compras.stream()
                .map(Compra::cantidad)
                .reduce(0,Integer::sum);
    }

    public static Compra ejercicioD(LinkedList<Compra> compras) {
        return compras.stream()
                .reduce((compra1, compra2) ->
                        compra1.precioUnitario > compra2.precioUnitario ? compra1 : compra2)
                .orElse(null);
    }

    public static double ejercicioE(LinkedList<Compra> compras) {
        return compras.stream()
                .map(Compra::calcularImporte)
                .reduce(0.0, Double::sum);
    }

    public static LinkedList<Compra> ejercicioF(LinkedList<Compra> compras) {
        return compras.stream()
                .filter(compra -> compra.calcularImporte() > 60)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static void main(String[] args) {
        LinkedList<Compra> compras = new LinkedList<>();

        compras.add(new Compra("Teclado", 2, 35.0));
        compras.add(new Compra("Ratón", 3, 18.5));
        compras.add(new Compra("Monitor", 1, 220.0));
        compras.add(new Compra("Cable HDMI", 5, 9.9));
        compras.add(new Compra("Disco SSD", 2, 89.0));
        compras.add(new Compra("Webcam", 1, 65.0));

        System.out.println("A: " + ejercicioA(compras));
System.out.println("B: " + ejercicioB(compras));
System.out.println("C: " + ejercicioC(compras));
System.out.println("D: " + ejercicioD(compras));
System.out.println("E: " + ejercicioE(compras));
System.out.println("F: " + ejercicioF(compras));
    }
}
