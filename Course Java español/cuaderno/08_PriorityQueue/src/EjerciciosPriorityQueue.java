import java.util.stream.Collectors;
import java.util.*;

public class EjerciciosPriorityQueue {

    record Tarea(String id, int prioridad,
                 long duracionMinutos, int consumoRecursos) {

        // Ejercicio B: implementar este método.
        long calcularCosteComputacional() {
            return 0L;
        }
    }

    public static List<String> ejercicioA(PriorityQueue<Tarea> tareas) {
        return null;
    }

    public static List<Long> ejercicioB(PriorityQueue<Tarea> tareas) {
        return null;
    }

    public static long ejercicioC(PriorityQueue<Tarea> tareas) {
        return 0L;
    }

    public static Tarea ejercicioD(PriorityQueue<Tarea> tareas) {
        return null;
    }

    public static long ejercicioE(PriorityQueue<Tarea> tareas) {
        return 0L;
    }

    public static PriorityQueue<Tarea> ejercicioF(PriorityQueue<Tarea> tareas) {
        return null;
    }

    public static void main(String[] args) {
        Comparator<Tarea> comparador =
                Comparator.comparingInt(Tarea::prioridad)
                          .reversed()
                          .thenComparing(Tarea::id);

        PriorityQueue<Tarea> tareas = new PriorityQueue<>(comparador);

        tareas.add(new Tarea("T01", 3, 120, 4));
        tareas.add(new Tarea("T02", 5, 45, 8));
        tareas.add(new Tarea("T03", 1, 240, 2));
        tareas.add(new Tarea("T04", 4, 90, 6));
        tareas.add(new Tarea("T05", 5, 30, 9));
        tareas.add(new Tarea("T06", 2, 180, 3));

        System.out.println("A: " + ejercicioA(tareas));
System.out.println("B: " + ejercicioB(tareas));
System.out.println("C: " + ejercicioC(tareas));
System.out.println("D: " + ejercicioD(tareas));
System.out.println("E: " + ejercicioE(tareas));
System.out.println("F: " + ejercicioF(tareas));
    }
}
