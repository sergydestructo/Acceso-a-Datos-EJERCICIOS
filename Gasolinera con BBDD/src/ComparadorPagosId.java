import java.util.Comparator;

public class ComparadorPagosId implements Comparator<Pago> {

    @Override
    public int compare(Pago p1, Pago p2) {
        int resultado = p2.compareTo(p1);

        if (resultado == 0) {
            resultado = Integer.compare(p2.getId(), p1.getId());
        }

        return resultado;
    }
}
