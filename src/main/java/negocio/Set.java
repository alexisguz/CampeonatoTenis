package negocio;

/*Esta clase usa la librerias de lombok para generar automaticamente los Getter y Setter. Más información en https://www.projectlombok.org/*/
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Set {
    private Jugador jugador1;
    private Jugador jugador2;
    private Integer puntajeSetJugador1;
    private Integer puntajeDesempateJugador1;
    private Integer puntajeSetJugador2;
    private Integer puntajeDesempateJugador2;
    private Game gameEnCurso;
    private Jugador ganador;

    private static final Integer DOS = 2;
    private static final Integer CUATRO = 4;
    private static final Integer CINCO = 5;
    private static final Integer SEIS = 6;
    private static final Integer SIETE = 7;

    public Set(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        puntajeSetJugador1 = 0;
        puntajeDesempateJugador1 = 0;
        puntajeSetJugador2 = 0;
        puntajeDesempateJugador2 = 0;
        gameEnCurso = null;
        ganador = null;
    }

    public Set(Match match) {
        jugador1 = match.getJugador1();
        jugador2 = match.getJugador2();
        puntajeSetJugador1 = 0;
        puntajeDesempateJugador1 = 0;
        puntajeSetJugador2 = 0;
        puntajeDesempateJugador2 = 0;
        gameEnCurso = null;
        ganador = null;
    }

    void play(MostrarInformacion mostrarInformacion) {
        do {
            gameEnCurso = new Game(this);
            gameEnCurso.play(mostrarInformacion);
            incrementarPuntajeSetJugador(gameEnCurso.getGanador());
            mostrarPuntajeSet(mostrarInformacion);
        } while (ganador == null);
    }
    void incrementarPuntajeSetJugador(Jugador jugador) {
        boolean jugador1Puntaje = jugador.equals(jugador1);
        boolean jugador2Puntaje = jugador.equals(jugador2);

        // Si el puntaje del Set es ( 5 - 4 ) o ( 4 - 5 ) y lleva a ( 4 - 6 ) o ( 6 - 4 ) => se incrementa el puntaje y se designa un ganador
        if ((puntajeSetJugador1.equals(CINCO) && puntajeSetJugador2 <= CUATRO && jugador1Puntaje)
                || (puntajeSetJugador2.equals(CINCO) && puntajeSetJugador1 <= CUATRO && jugador2Puntaje)) {
            incrementarPuntajeSet(jugador);
            designarGanador(jugador);
            // Si el puntaje del Set es ( 6 - 6 ) => se activa el desempate
        } else if ((puntajeSetJugador2.equals(SEIS) && puntajeSetJugador1.equals(SEIS))) {
            activarDesempate(jugador);
            // Si el puntaje del Set es ( 5 - 6 ) o ( 6 - 5 ) y lleva a  ( 5 - 7 ) o ( 7 - 5 ) => se incrementa el puntaje y se designa un ganador
        } else if ((puntajeSetJugador1.equals(SEIS) && puntajeSetJugador2 <= CINCO && jugador1Puntaje)
                || (puntajeSetJugador2.equals(SEIS) && puntajeSetJugador1 <= CINCO && jugador2Puntaje)) {
            incrementarPuntajeSet(jugador);
            designarGanador(jugador);
            // En todos los otros casos => se incrementa los puntajes del set
        } else {
            incrementarPuntajeSet(jugador);
        }
    }

    private void incrementarPuntajeSet(Jugador jugador) {
        if (jugador.equals(jugador1)) {
            puntajeSetJugador1++;
        } else {
            puntajeSetJugador2++;
        }
    }
    private void designarGanador(Jugador jugador) {
        if (jugador1.equals(jugador)) {
            ganador = jugador1;
        } else {
            ganador = jugador2;
        }
    }

    void mostrarPuntajeSet(MostrarInformacion mostrarInformacion) {
        mostrarInformacion.mostrarPuntajeSet(puntajeSetJugador1, puntajeSetJugador2);

        if (puntajeDesempateJugador1 != 0 || puntajeDesempateJugador2 != 0) {
            mostrarInformacion.mostrarTieBreakPuntaje(puntajeDesempateJugador1, puntajeDesempateJugador2);
        }

        if (ganador != null) {
            anunciarGanador(mostrarInformacion);
        }
    }
    private void anunciarGanador(MostrarInformacion mostrarInformacion) {
        if (ganador != null) {
            mostrarInformacion.mostrarGanadorSet(ganador);
        }
    }
    private void activarDesempate(Jugador jugador) {
        boolean jugador1Puntaje = jugador.equals(jugador1);
        boolean jugador2Puntaje = jugador.equals(jugador2);

        // Incrementar el puntaje de Desempate
        incrementarPuntajeDesempate(jugador);
        // Si el puntaje de desempate es al menos 7 + 2 puntos de diferencia => se incrementa el puntaje y se designa un ganador
        if ((puntajeDesempateJugador1 >= SIETE && (puntajeDesempateJugador1 >= (puntajeDesempateJugador2 + DOS)) && jugador1Puntaje)
                || (puntajeDesempateJugador2 >= SIETE && (puntajeDesempateJugador2 >= (puntajeDesempateJugador1 + DOS))) && jugador2Puntaje) {

            incrementarPuntajeSet(jugador);
            designarGanador(jugador);
        }
    }
    private void incrementarPuntajeDesempate(Jugador jugador) {
        if (jugador.equals(jugador1)) {
            puntajeDesempateJugador1++;
        } else {
            puntajeDesempateJugador2++;
        }
    }


}
