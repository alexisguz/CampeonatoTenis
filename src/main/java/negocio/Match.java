package negocio;

/*Esta clase usa la librerias de lombok para generar automaticamente los Getter y Setter. Más información en https://www.projectlombok.org/*/
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {
    private Jugador jugador1;
    private Jugador jugador2;
    private Integer puntajeMatchJugador1;
    private Integer puntajeMatchJugador2;
    private Set setEnCurso;
    private Jugador ganador;
    private String nombreTorneo;
    private static final Integer DOS = 2;
    private static final Integer TRES = 2;

    public Match(Jugador jugador1, Jugador jugador2, String nombreTorneo) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        puntajeMatchJugador1 = 0;
        puntajeMatchJugador2 = 0;
        setEnCurso = null;
        this.nombreTorneo = nombreTorneo;

    }
    public void play(MostrarInformacion mostrarInformacion) {
        do {
            setEnCurso = new Set(this);
            setEnCurso.play(mostrarInformacion);
            incrementarPuntajeJugadorMatch(setEnCurso.getGanador());
            anunciarGanador(mostrarInformacion);
        } while (ganador == null);

        // Se muestra el puntaje del Match
        mostrarInformacion.mostrarPuntajeMatch(getPuntajeMatchJugador1(), getPuntajeMatchJugador2());
    }
    void incrementarPuntajeJugadorMatch(Jugador jugador) {
        boolean jugador1Puntaje = jugador.equals(jugador1);
        boolean jugador2Puntaje = jugador.equals(jugador2);
        incrementarPuntajeMatch(jugador);

        // El primer jugador en ganar 3 sets (osea el mejor de 3) gana el Match
        if ((puntajeMatchJugador1.equals(TRES) && puntajeMatchJugador2 <= DOS && jugador1Puntaje)
                || (puntajeMatchJugador2.equals(TRES) && puntajeMatchJugador1 <= DOS && jugador2Puntaje)) {
            designarGanador(jugador);
        }
    }
    private void incrementarPuntajeMatch(Jugador jugador) {
        if (jugador1.equals(jugador)) {
            puntajeMatchJugador1++;
        } else {
            puntajeMatchJugador2++;
        }
    }
    private void designarGanador(Jugador jugador) {
        ganador = jugador1.equals(jugador) ? jugador1 : jugador2;
    }

    private void anunciarGanador(MostrarInformacion mostrarInformacion) {
        if (ganador != null) {
            mostrarInformacion.mostrarGanadorMatch(ganador, nombreTorneo);
        }
    }
}
