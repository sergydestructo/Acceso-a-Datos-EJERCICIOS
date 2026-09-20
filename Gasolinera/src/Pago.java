import java.util.Date;
import java.util.Objects;
public class Pago implements Comparable<Pago>{
    final private int id;
    final private int idCliente;
    final private Date fecha;
    final private Double importe;
    final private Double litros;
    final private Combustible combustible;

    public Pago(int id, int idCliente, Date fecha, Double importe, Double litros, Combustible combustible) {
        this.id = id;
        this.idCliente = idCliente;
        this.fecha = fecha;

        if (importe <= 0) {
            throw new IllegalArgumentException("El importe debe ser mayor que 0");
        }

        if (importe * 100 % 1 != 0) {
            throw new IllegalArgumentException("El importe debe tener maximo dos decimales");
        }

        this.importe = importe;

        if (litros <= 0) {
            throw new IllegalArgumentException("Los litros deben ser mayores que 0");
        }

        if (litros * 100 % 1 != 0) {
            throw new IllegalArgumentException("Los litros deben tener máximo dos decimales");
        }

        this.litros = litros;

        this.combustible = combustible;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pago pago)) return false;
        return getId() == pago.getId() && getIdCliente() == pago.getIdCliente() && Objects.equals(getFecha(), pago.getFecha()) && Objects.equals(getImporte(), pago.getImporte()) && Objects.equals(getLitros(), pago.getLitros()) && getCombustible() == pago.getCombustible();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdCliente(), getFecha(), getImporte(), getLitros(), getCombustible());
    }


    @Override
    public int compareTo(Pago o) {
        return o.getFecha().compareTo(fecha);
    }

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public Double getImporte() {
        return importe;
    }

    public Double getLitros() {
        return litros;
    }

    public Combustible getCombustible() {
        return combustible;
    }
}
