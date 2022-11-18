package negocio;

/*Esta clase usa la librerias de lombok para generar automaticamente los Getter y Setter. Más información en https://www.projectlombok.org/*/
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;


@Getter
@Setter
public class Game {
    private Jugador jugador1;
    private Jugador jugador2;
    private Integer gamePuntajeJugador1;
    private Integer gamePuntajeJugador2;
    private String gamePuntajeTextoJugador1;
    private String gamePuntajeTextoJugador2;
    private Jugador ganador;

    private static final Long TIEMPO_DELAY = 500L;
    private static final Integer TREINTA_PUNTOS = 2;
    private static final Integer CUARENTA_PUNTOS = 3;
    private static final Integer PUNTO_VENTAJA = 4;
    private static final List<String> listaPuntos = Arrays.asList("0", "15", "30", "40", "PV");

    public Game(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        gamePuntajeJugador1 = 0;
        gamePuntajeJugador2 = 0;
        gamePuntajeTextoJugador1 = "";
        gamePuntajeTextoJugador2 = "";
        ganador = null;
    }

    public Game(Set set) {
        jugador1 = set.getJugador1();
        jugador2 = set.getJugador2();
        gamePuntajeJugador1 = 0;
        gamePuntajeJugador2 = 0;
        gamePuntajeTextoJugador1 = "";
        gamePuntajeTextoJugador2 = "";
        ganador = null;
    }

    void play(MostrarInformacion mostrarInformacion) {
        do {
            // Seleccionamos al azar jugador que saca
            Jugador jugadorSaca = obtenerJugadorSaca(this);
            mostrarSacadorGame(jugadorSaca, mostrarInformacion);
            // Seleccionamos al azar jugador que hace el punto
            Jugador jugador = obtenerGanador(this);
            incrementarPuntajeJugadorEnGame(jugador, mostrarInformacion);
            mostrarPuntajeGame(mostrarInformacion);

            try {
                // generamos una demora entre puntos de 0.5 segundos
                Thread.sleep(TIEMPO_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (ganador == null);
    }

    private void mostrarSacadorGame(Jugador jugadorSaca, MostrarInformacion mostrarInformacion) {
        mostrarInformacion.mostrarJugadorSaca(jugadorSaca.getNombre());
    }

    private Jugador obtenerJugadorSaca(Game game) {
        return (Math.random() > 0.5) ? game.getJugador1() : game.getJugador2();
    }

    private Jugador obtenerGanador(Game game) {
        return (
                (jugador1.getProbabilidad() * Math.random()) > (jugador2.getProbabilidad() * Math.random())) ?
                game.getJugador1() : game.getJugador2();
    }

    void incrementarPuntajeJugadorEnGame(Jugador jugador, MostrarInformacion mostrarInformacion) {
        boolean jugador1Puntaje = jugador.equals(jugador1);
        boolean jugador2Puntaje = jugador.equals(jugador2);

        // La puntuacion del Game con {X<40} es ( X - 40 ) o ( 40 - X ) lo que hace una puntuacion por encima de 40 => se designa ganador
        if ((gamePuntajeJugador1.equals(CUARENTA_PUNTOS) && gamePuntajeJugador2 < CUARENTA_PUNTOS && jugador1Puntaje)
                || (gamePuntajeJugador2.equals(CUARENTA_PUNTOS) && gamePuntajeJugador1 < CUARENTA_PUNTOS && jugador2Puntaje)) {

            designarGanador(jugador, mostrarInformacion);
            // Si el puntaje del Game es ( 40 - 40 ) o por encima => se activa la regla Deuce
        } else if (gamePuntajeJugador1 >= CUARENTA_PUNTOS && gamePuntajeJugador2 >= CUARENTA_PUNTOS) {

            activarReglaDeuce(jugador, mostrarInformacion);
            // En cualquier otro caso => se incrementa los puntajes
        } else {
            incrementarPuntajeGame(jugador, mostrarInformacion);
        }
    }

    private void activarReglaDeuce(Jugador jugador, MostrarInformacion mostrarInformacion) {
        boolean jugador1Puntaje = jugador.equals(jugador1);
        boolean jugador2Puntaje = jugador.equals(jugador2);

        // Si el puntaje del Game es ( 40 - 40 ) => se incrementa el puntaje a PV
        if (gamePuntajeJugador1.equals(CUARENTA_PUNTOS) && gamePuntajeJugador2.equals(CUARENTA_PUNTOS)) {

            incrementarPuntajeGame(jugador, mostrarInformacion);

            // Si el puntaje del Game es  ( PV - 40 ) o ( 40 - PV ) lo que hace una puntuacion por encima de PV => se designa ganador
        } else if ((gamePuntajeJugador1.equals(PUNTO_VENTAJA) && gamePuntajeJugador2.equals(CUARENTA_PUNTOS) && jugador1Puntaje)
                || (gamePuntajeJugador2.equals(PUNTO_VENTAJA) && gamePuntajeJugador1.equals(CUARENTA_PUNTOS) && jugador2Puntaje)) {

            designarGanador(jugador, mostrarInformacion);

            // Si el puntaje del Game es  ( PV - 40 ) o ( 40 - PV ) lo que hace una puntuacion ( PV - PV ) => se incrementa el puntaje y se activa la regla Deuce
        } else if ((gamePuntajeJugador1.equals(CUARENTA_PUNTOS) && gamePuntajeJugador2.equals(PUNTO_VENTAJA) && jugador1Puntaje)
                || (gamePuntajeJugador2.equals(CUARENTA_PUNTOS) && gamePuntajeJugador1.equals(PUNTO_VENTAJA) && jugador2Puntaje)) {

            incrementarPuntajeGame(jugador, mostrarInformacion);
            restablecerReglaDeuce(mostrarInformacion);
        }
    }

    private void restablecerReglaDeuce(MostrarInformacion mostrarInformacion) {
        mostrarInformacion.anunciarReglaDeuce();
        setGamePuntajeJugador1(CUARENTA_PUNTOS);
        setGamePuntajeJugador2(CUARENTA_PUNTOS);
    }

    private Integer incrementarPuntajeGame(Jugador jugador, MostrarInformacion mostrarInformacion) {
        mostrarInformacion.mostrarPuntoGame(jugador);

        if (jugador1.equals(jugador)) {
            return gamePuntajeJugador1++;
        } else {
            return gamePuntajeJugador2++;
        }
    }

    private void designarGanador(Jugador jugador, MostrarInformacion mostrarInformacion) {
        if (jugador1.equals(jugador)) {
            mostrarInformacion.mostrarPuntoGame(jugador1);
            ganador = jugador1;
        } else {
            mostrarInformacion.mostrarPuntoGame(jugador1);
            ganador = jugador2;
        }
        resetPuntajeGame();
    }

    private void resetPuntajeGame() {
        setGamePuntajeJugador1(0);
        setGamePuntajeJugador2(0);
    }
    void mostrarPuntajeGame(MostrarInformacion mostrarInformacion) {
        if (ganador == null) {
            mostrarInformacion.mostrarPuntajeGame(getDescripcionPuntaje(gamePuntajeJugador1), getDescripcionPuntaje(gamePuntajeJugador2));
        } else {
            anunciarGanador(mostrarInformacion);
        }
    }
    private String getDescripcionPuntaje(Integer puntajeGame) {
        return listaPuntos.get(puntajeGame);
    }
    private void anunciarGanador(MostrarInformacion mostrarInformacion) {
        mostrarInformacion.mostrarGanadorGame(ganador);
    }


}
