import java.util.stream.Collectors;
import java.util.*;

public class EjerciciosArrayDeque {

    record PaginaVisitada(String url, String dominio,
                          long segundos, int clics,
                          long bytesDescargados) {

        // Ejercicio B: implementar este método.
        double calcularNivelInteraccion() {
            return 0.0;
        }
    }

    public static List<String> ejercicioA(ArrayDeque<PaginaVisitada> historial) {
        return null;
    }

    public static List<Double> ejercicioB(ArrayDeque<PaginaVisitada> historial) {
        return null;
    }

    public static long ejercicioC(ArrayDeque<PaginaVisitada> historial) {
        return 0L;
    }

    public static PaginaVisitada ejercicioD(ArrayDeque<PaginaVisitada> historial) {
        return null;
    }

    public static double ejercicioE(ArrayDeque<PaginaVisitada> historial) {
        return 0.0;
    }

    public static ArrayDeque<PaginaVisitada> ejercicioF(ArrayDeque<PaginaVisitada> historial) {
        return null;
    }

    public static void main(String[] args) {
        ArrayDeque<PaginaVisitada> historial = new ArrayDeque<>();

        historial.addLast(new PaginaVisitada(
                "/inicio", "ejemplo.es", 120, 4, 50000));
        historial.addLast(new PaginaVisitada(
                "/productos", "tienda.es", 340, 15, 120000));
        historial.addLast(new PaginaVisitada(
                "/noticias", "diario.es", 210, 7, 80000));
        historial.addLast(new PaginaVisitada(
                "/carrito", "tienda.es", 95, 12, 35000));
        historial.addLast(new PaginaVisitada(
                "/video", "video.es", 780, 3, 2500000));
        historial.addLast(new PaginaVisitada(
                "/contacto", "ejemplo.es", 55, 2, 15000));

        System.out.println("A: " + ejercicioA(historial));
System.out.println("B: " + ejercicioB(historial));
System.out.println("C: " + ejercicioC(historial));
System.out.println("D: " + ejercicioD(historial));
System.out.println("E: " + ejercicioE(historial));
System.out.println("F: " + ejercicioF(historial));
    }
}
