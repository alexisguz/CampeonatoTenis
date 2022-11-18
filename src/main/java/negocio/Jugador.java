package negocio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Jugador {
    private String nombre;
    private int probabilidad;

    public Jugador() {
    }

    public Jugador(String nombre, int probabilidad) {
        this.nombre = nombre;
        this.probabilidad = probabilidad;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", probabilidad=" + probabilidad +
                '}';
    }
}
