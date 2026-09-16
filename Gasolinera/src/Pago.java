import java.util.Date;
import java.util.Objects;

public class Pago {
    final private int id;
    final private int idCliente;
    final private Date fecha;
    final private float importe;
    final private float litros;
    final private String combustible;

    // Cambiar constructor para comprobar inputs correctos/corregir decimales

    public Pago(int id, int idCliente, Date fecha, float importe, float litros, String combustible) {
        this.id = id;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.importe = importe;
        this.litros = litros;
        this.combustible = combustible;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pago pago)) return false;
        return getId() == pago.getId() && getIdCliente() == pago.getIdCliente() && Float.compare(getImporte(), pago.getImporte()) == 0 && Float.compare(getLitros(), pago.getLitros()) == 0 && Objects.equals(getFecha(), pago.getFecha()) && Objects.equals(getCombustible(), pago.getCombustible());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdCliente(), getFecha(), getImporte(), getLitros(), getCombustible());
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

    public float getImporte() {
        return importe;
    }

    public float getLitros() {
        return litros;
    }

    public String getCombustible() {
        return combustible;
    }
}
