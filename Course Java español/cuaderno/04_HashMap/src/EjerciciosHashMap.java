import java.util.stream.Collectors;
import java.util.*;

public class EjerciciosHashMap {

    record Producto(String nombre, double precio,
                    int stock, String categoria) {

        // Ejercicio B: implementar este método.
        double calcularValorInventario() {
            return precio * stock;
        }
    }

    public static List<String> ejercicioA(HashMap<String, Producto> inventario) {
        return inventario.entrySet().stream()
                .map(entrada ->
                        entrada.getKey() + " - "
                        + entrada.getValue().nombre + " - "
                        + entrada.getValue().stock)
                .toList();
    }

    public static List<Double> ejercicioB(HashMap<String, Producto> inventario) {
        return inventario.values().stream()
                .map(Producto::calcularValorInventario)
                .toList();
    }

    public static int ejercicioC(HashMap<String, Producto> inventario) {
        return inventario.values().stream()
                .map(Producto::stock)
                .reduce(0,Integer::sum);
    }

    public static Producto ejercicioD(HashMap<String, Producto> inventario) {
        return inventario.values().stream()
                .reduce((producto1, producto2) ->
                        producto1.precio > producto2.precio ? producto1
                        : producto1.precio < producto2.precio ? producto2
                        : producto1.nombre.compareTo(producto2.nombre) <= 0 ? producto1 : producto2 )
                .orElse(null);
    }

    public static double ejercicioE(HashMap<String, Producto> inventario) {
        return inventario.values().stream()
                .map(Producto::calcularValorInventario)
                .reduce(0.0,Double::sum);
    }

    public static HashMap<String, Producto> ejercicioF(HashMap<String, Producto> inventario) {
        return inventario.entrySet().stream()
                .filter(entrada -> entrada.getValue().categoria.equals("Informática")
                                && entrada.getValue().stock < 20)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (producto1, producto2) -> producto1,
                        HashMap::new
                ));
    }

    public static void main(String[] args) {
        HashMap<String, Producto> inventario = new HashMap<>();

        inventario.put("P001", new Producto("Portátil", 850, 8, "Informática"));
        inventario.put("P002", new Producto("Monitor", 220, 15, "Informática"));
        inventario.put("P003", new Producto("Mesa", 180, 5, "Mobiliario"));
        inventario.put("P004", new Producto("Silla", 120, 12, "Mobiliario"));
        inventario.put("P005", new Producto("Teclado", 45, 30, "Informática"));
        inventario.put("P006", new Producto("Impresora", 190, 3, "Oficina"));

        System.out.println("A: " + ejercicioA(inventario));
System.out.println("B: " + ejercicioB(inventario));
System.out.println("C: " + ejercicioC(inventario));
System.out.println("D: " + ejercicioD(inventario));
System.out.println("E: " + ejercicioE(inventario));
System.out.println("F: " + ejercicioF(inventario));
    }
}
