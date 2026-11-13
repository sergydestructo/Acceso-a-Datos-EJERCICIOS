import java.util.Comparator;

public class ComparadorClienteId implements Comparator<Cliente> {

    @Override
    public int compare(Cliente c1, Cliente c2) {
        int resultado = c1.compareTo(c2);

        if (resultado == 0) {
            resultado = Integer.compare(c1.getId(), c2.getId());
        }

        return resultado;
    }
}
