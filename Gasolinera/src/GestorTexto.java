import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class GestorTexto {
    final private Scanner scan;

    public GestorTexto() {
        this.scan = new Scanner(System.in);
    }

    public String inputTexto() {
       while (true) {
           String input = scan.nextLine();

           if (input.isEmpty()) {
               System.out.println("El texto no puede estar en blanco");
               continue;
           }

           return sanearTexto(input);
       }
    }

    public String inputMatriculas() {
        String input = inputTexto();
        input = input.toUpperCase();
        return sanearTexto(input);
    }

    public int inputOpciones() {
        while (true) {
            try {
                return Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Introduzca un numero válido");
            }
        }
    }

    public Date inputFecha() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);

        while (true) {
            System.out.print("Introduzca una fecha (dd/MM/yyyy): ");
            String input = scan.nextLine();

            if (input.isEmpty()) {
                return new Date();
            }

            try {
                Date fecha = formato.parse(input);

                if (!formato.format(fecha).equals(input)) {
                    System.out.println("Formato incorrecto.");
                    continue;
                }

                return fecha;

            } catch (ParseException e) {
                System.out.println("Fecha o formato incorrecto.");
            }
        }
    }

    public double inputNumeroDecimal() {
        while (true) {
            System.out.print("Introduzca un número mayor que 0 (máximo 2 decimales): ");
            String input = scan.nextLine().trim();

            // Aceptar tanto "." como "," como separador decimal
            input = input.replace(',', '.');

            // Comprobar formato: uno o más dígitos y, opcionalmente, 1-2 decimales
            if (!input.matches("\\d+(\\.\\d{1,2})?")) {
                System.out.println("Introduzca un número válido con máximo 2 decimales.");
                continue;
            }

            double numero = Double.parseDouble(input);

            if (numero <= 0) {
                System.out.println("El número debe ser mayor que 0.");
                continue;
            }

            return numero;
        }
    }

    public Combustible inputCombustible() {
        while (true) {
            System.out.print("Introduzca el tipo de combustible (GASOLINA/DIESEL): ");
            String input = scan.nextLine().trim().toUpperCase();

            try {
                return Combustible.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de combustible no válido.");
            }
        }
    }

    private String sanearTexto(String input) {
        input = input.strip();

        return input;
    }
}
