import java.util.stream.Collectors;
import java.time.*;
import java.util.*;

public class EjerciciosTreeMap {

    record Transaccion(String concepto, double importe, String tipo) {

        // Ejercicio B: implementar este método.
        double obtenerImporteConSigno() {
            return 0.0;
        }
    }

    public static List<String> ejercicioA(TreeMap<LocalDate, Transaccion> movimientos) {
        return null;
    }

    public static List<Double> ejercicioB(TreeMap<LocalDate, Transaccion> movimientos) {
        return null;
    }

    public static double ejercicioC(TreeMap<LocalDate, Transaccion> movimientos) {
        return 0.0;
    }

    public static Map.Entry<LocalDate, Transaccion> ejercicioD(TreeMap<LocalDate, Transaccion> movimientos) {
        return null;
    }

    public static double ejercicioE(TreeMap<LocalDate, Transaccion> movimientos) {
        return 0.0;
    }

    public static TreeMap<LocalDate, Transaccion> ejercicioF(TreeMap<LocalDate, Transaccion> movimientos) {
        return null;
    }

    public static void main(String[] args) {
        TreeMap<LocalDate, Transaccion> movimientos = new TreeMap<>();

        movimientos.put(LocalDate.of(2026, 1, 5),
                new Transaccion("Nómina", 2300, "INGRESO"));
        movimientos.put(LocalDate.of(2026, 1, 7),
                new Transaccion("Alquiler", 850, "GASTO"));
        movimientos.put(LocalDate.of(2026, 1, 9),
                new Transaccion("Compra", 145, "GASTO"));
        movimientos.put(LocalDate.of(2026, 1, 12),
                new Transaccion("Venta", 320, "INGRESO"));
        movimientos.put(LocalDate.of(2026, 1, 15),
                new Transaccion("Seguro", 75, "GASTO"));
        movimientos.put(LocalDate.of(2026, 1, 21),
                new Transaccion("Extra", 180, "INGRESO"));

        System.out.println("A: " + ejercicioA(movimientos));
System.out.println("B: " + ejercicioB(movimientos));
System.out.println("C: " + ejercicioC(movimientos));
System.out.println("D: " + ejercicioD(movimientos));
System.out.println("E: " + ejercicioE(movimientos));
System.out.println("F: " + ejercicioF(movimientos));
    }
}
