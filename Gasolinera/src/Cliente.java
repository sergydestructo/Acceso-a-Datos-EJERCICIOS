import java.util.Objects;

public class Cliente {
    final private int id;
    final private String nombre;
    final private String tlfn;
    final private String matricula;

    public Cliente(int id, String nombre, String tlfn, String matricula) {
            this.id = id;
            this.nombre = nombre;
            this.tlfn = tlfn;
            this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTlfn() {
        return tlfn;
    }

    public String getMatricula() {
        return matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cliente cliente)) return false;
        return getId() == cliente.getId() && Objects.equals(getNombre(), cliente.getNombre()) && Objects.equals(getTlfn(), cliente.getTlfn()) && Objects.equals(getMatricula(), cliente.getMatricula());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getTlfn(), getMatricula());
    }
}
