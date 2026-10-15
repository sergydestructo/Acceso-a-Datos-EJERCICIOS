import java.util.stream.Collectors;
import java.util.*;

public class EjerciciosLinkedHashSet {

    record Libro(String isbn, String titulo, String autor,
                 int paginas, int anio) {

        // Ejercicio B: implementar este método.
        String generarReferencia() {
            return autor + " (" + anio + "): " + titulo;
        }
    }

    public static LinkedHashSet<String> ejercicioA(LinkedHashSet<Libro> libros) {
        return libros.stream()
                .map(libro -> libro.titulo.toUpperCase())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static LinkedHashSet<String> ejercicioB(LinkedHashSet<Libro> libros) {
        return libros.stream()
                .map(Libro::generarReferencia)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static int ejercicioC(LinkedHashSet<Libro> libros) {
        return libros.stream()
                .map(Libro::paginas)
                .reduce(0, Integer::sum);
    }

    public static Libro ejercicioD(LinkedHashSet<Libro> libros) {
        return libros.stream()
                .reduce((libro1, libro2) ->
                        libro1.anio < libro2.anio ? libro1 : libro2)
                .orElse(null);
    }

    public static String ejercicioE(LinkedHashSet<Libro> libros) {
        return libros.stream()
                .map(Libro::titulo)
                .reduce((titulo1, titulo2) ->
                        titulo1 + " | " + titulo2)
                .orElse("");
    }

    public static LinkedHashSet<Libro> ejercicioF(LinkedHashSet<Libro> libros) {
        return libros.stream()
                .filter(libro -> libro.anio < 1970 && libro.paginas > 300)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static void main(String[] args) {
        LinkedHashSet<Libro> libros = new LinkedHashSet<>();

        libros.add(new Libro("978-01", "Dune", "Frank Herbert", 680, 1965));
        libros.add(new Libro("978-02", "Fundación", "Isaac Asimov", 310, 1951));
        libros.add(new Libro("978-03", "1984", "George Orwell", 328, 1949));
        libros.add(new Libro("978-04", "Solaris", "Stanislaw Lem", 250, 1961));
        libros.add(new Libro("978-05", "Neuromante", "William Gibson", 320, 1984));
        libros.add(new Libro("978-06", "Hyperion", "Dan Simmons", 500, 1989));

        System.out.println("A: " + ejercicioA(libros));
System.out.println("B: " + ejercicioB(libros));
System.out.println("C: " + ejercicioC(libros));
System.out.println("D: " + ejercicioD(libros));
System.out.println("E: " + ejercicioE(libros));
System.out.println("F: " + ejercicioF(libros));
    }
}
