import java.util.stream.Collectors;
import java.util.*;

public class EjerciciosArrayList {

    record Alumno(String nombre, int edad, double nota) {

        // Ejercicio B: implementar este método.
        String generarInforme() {
            return nombre + " (" + edad + " años): " + nota;
        }
    }

    public static List<String> ejercicioA(ArrayList<Alumno> alumnos) {
        return alumnos.stream()
                .map(alumno -> alumno.nombre().toUpperCase())
                .toList();
    }

    public static List<String> ejercicioB(ArrayList<Alumno> alumnos) {
        return alumnos.stream()
                .map(Alumno::generarInforme)
                .toList();
    }

    public static double ejercicioC(ArrayList<Alumno> alumnos) {
        return alumnos.stream()
                .map(Alumno::nota)
                .reduce(0.0, Double::sum);
    }

    public static Alumno ejercicioD(ArrayList<Alumno> alumnos) {
        return alumnos.stream()
                .reduce((alumno1, alumno2) -> alumno1.edad > alumno2.edad ? alumno1 : alumno2)
                .orElse(null);
    }

    public static int ejercicioE(ArrayList<Alumno> alumnos) {
        return alumnos.stream()
                .map(Alumno::edad)
                .reduce(0, Integer::sum);
    }

    public static List<Alumno> ejercicioF(ArrayList<Alumno> alumnos) {
        return alumnos.stream()
                .filter(alumno -> alumno.nota >= 5)
                .toList();
    }

    public static void main(String[] args) {
        ArrayList<Alumno> alumnos = new ArrayList<>();

        alumnos.add(new Alumno("Ana", 19, 8.5));
        alumnos.add(new Alumno("Luis", 22, 4.2));
        alumnos.add(new Alumno("Marta", 20, 9.1));
        alumnos.add(new Alumno("Pedro", 23, 5.5));
        alumnos.add(new Alumno("Lucía", 21, 7.3));
        alumnos.add(new Alumno("Carlos", 24, 3.8));

        System.out.println("A: " + ejercicioA(alumnos));
System.out.println("B: " + ejercicioB(alumnos));
System.out.println("C: " + ejercicioC(alumnos));
System.out.println("D: " + ejercicioD(alumnos));
System.out.println("E: " + ejercicioE(alumnos));
System.out.println("F: " + ejercicioF(alumnos));
    }
}
