import java.util.stream.Collectors;
import java.util.*;

public class EjerciciosTreeSet {

    record Estudiante(int id, String nombre, double nota,
                      int horasEstudio, int creditos) {

        // Ejercicio B: implementar este método.
        double calcularPuntuacionPonderada() {
            return 0.0;
        }
    }

    public static List<String> ejercicioA(TreeSet<Estudiante> estudiantes) {
        return null;
    }

    public static List<Double> ejercicioB(TreeSet<Estudiante> estudiantes) {
        return null;
    }

    public static int ejercicioC(TreeSet<Estudiante> estudiantes) {
        return 0;
    }

    public static Estudiante ejercicioD(TreeSet<Estudiante> estudiantes) {
        return null;
    }

    public static double ejercicioE(TreeSet<Estudiante> estudiantes) {
        return 0.0;
    }

    public static TreeSet<Estudiante> ejercicioF(TreeSet<Estudiante> estudiantes) {
        return null;
    }

    public static void main(String[] args) {
        Comparator<Estudiante> comparador =
                Comparator.comparingDouble(Estudiante::nota)
                          .reversed()
                          .thenComparingInt(Estudiante::id);

        TreeSet<Estudiante> estudiantes = new TreeSet<>(comparador);

        estudiantes.add(new Estudiante(1, "Ana", 8.5, 120, 6));
        estudiantes.add(new Estudiante(2, "Luis", 6.3, 90, 5));
        estudiantes.add(new Estudiante(3, "Marta", 9.7, 150, 6));
        estudiantes.add(new Estudiante(4, "Pedro", 5.2, 70, 4));
        estudiantes.add(new Estudiante(5, "Lucía", 8.5, 135, 6));
        estudiantes.add(new Estudiante(6, "Carlos", 4.1, 60, 5));

        System.out.println("A: " + ejercicioA(estudiantes));
System.out.println("B: " + ejercicioB(estudiantes));
System.out.println("C: " + ejercicioC(estudiantes));
System.out.println("D: " + ejercicioD(estudiantes));
System.out.println("E: " + ejercicioE(estudiantes));
System.out.println("F: " + ejercicioF(estudiantes));
    }
}
