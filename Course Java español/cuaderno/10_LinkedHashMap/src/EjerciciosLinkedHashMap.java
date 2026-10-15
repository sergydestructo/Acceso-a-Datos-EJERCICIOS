import java.util.stream.Collectors;
import java.util.*;

public class EjerciciosLinkedHashMap {

    record EntradaCache(String recurso, long bytes,
                        int accesos, long costeRegeneracion) {

        // Ejercicio B: implementar este método.
        long calcularCosteAcumulado() {
            return 0L;
        }
    }

    public static List<String> ejercicioA(LinkedHashMap<String, EntradaCache> cache) {
        return null;
    }

    public static List<Long> ejercicioB(LinkedHashMap<String, EntradaCache> cache) {
        return null;
    }

    public static long ejercicioC(LinkedHashMap<String, EntradaCache> cache) {
        return 0L;
    }

    public static Map.Entry<String, EntradaCache> ejercicioD(LinkedHashMap<String, EntradaCache> cache) {
        return null;
    }

    public static long ejercicioE(LinkedHashMap<String, EntradaCache> cache) {
        return 0L;
    }

    public static LinkedHashMap<String, EntradaCache> ejercicioF(LinkedHashMap<String, EntradaCache> cache) {
        return null;
    }

    public static void main(String[] args) {
        LinkedHashMap<String, EntradaCache> cache =
                new LinkedHashMap<>(16, 0.75f, true);

        cache.put("R01", new EntradaCache("logo.png", 25000, 80, 10));
        cache.put("R02", new EntradaCache("video.mp4", 5000000, 12, 500));
        cache.put("R03", new EntradaCache("estilos.css", 15000, 120, 5));
        cache.put("R04", new EntradaCache("app.js", 450000, 95, 45));
        cache.put("R05", new EntradaCache("foto.jpg", 850000, 35, 80));
        cache.put("R06", new EntradaCache("datos.json", 65000, 60, 25));

        // Simulamos accesos anteriores al procesamiento funcional.
        cache.get("R02");
        cache.get("R01");
        cache.get("R04");

        System.out.println("A: " + ejercicioA(cache));
System.out.println("B: " + ejercicioB(cache));
System.out.println("C: " + ejercicioC(cache));
System.out.println("D: " + ejercicioD(cache));
System.out.println("E: " + ejercicioE(cache));
System.out.println("F: " + ejercicioF(cache));
    }
}
