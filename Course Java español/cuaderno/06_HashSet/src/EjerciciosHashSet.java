import java.util.stream.Collectors;
import java.util.*;

public class EjerciciosHashSet {

    record Sensor(String id, double temperatura,
                  double humedad, double voltaje, boolean activo) {

        // Ejercicio B: implementar este método.
        double calcularIndiceRiesgo() {
            return 0.0;
        }
    }

    public static HashSet<String> ejercicioA(HashSet<Sensor> sensores) {
        return null;
    }

    public static List<Double> ejercicioB(HashSet<Sensor> sensores) {
        return null;
    }

    public static double ejercicioC(HashSet<Sensor> sensores) {
        return 0.0;
    }

    public static Sensor ejercicioD(HashSet<Sensor> sensores) {
        return null;
    }

    public static double ejercicioE(HashSet<Sensor> sensores) {
        return 0.0;
    }

    public static HashSet<Sensor> ejercicioF(HashSet<Sensor> sensores) {
        return null;
    }

    public static void main(String[] args) {
        HashSet<Sensor> sensores = new HashSet<>();

        sensores.add(new Sensor("S01", 25.5, 45, 3.3, true));
        sensores.add(new Sensor("S02", 78.2, 30, 2.8, true));
        sensores.add(new Sensor("S03", 65.0, 70, 3.1, true));
        sensores.add(new Sensor("S04", 90.1, 20, 2.5, false));
        sensores.add(new Sensor("S05", 38.4, 55, 3.2, true));
        sensores.add(new Sensor("S06", 82.7, 40, 2.7, true));

        System.out.println("A: " + ejercicioA(sensores));
System.out.println("B: " + ejercicioB(sensores));
System.out.println("C: " + ejercicioC(sensores));
System.out.println("D: " + ejercicioD(sensores));
System.out.println("E: " + ejercicioE(sensores));
System.out.println("F: " + ejercicioF(sensores));
    }
}
