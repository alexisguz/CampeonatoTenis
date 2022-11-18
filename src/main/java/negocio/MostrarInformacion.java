package negocio;

public interface MostrarInformacion {
    void mostrarJugadorSaca(String nombre);
    void mostrarPuntoGame(Jugador jugador);

    void mostrarPuntajeGame(String gamePuntajeJugador1, String gamePuntajeJugador2);

    void anunciarReglaDeuce();

    void mostrarGanadorGame(Jugador jugador);

    void mostrarPuntajeSet(Integer puntajeSetJugador1, Integer puntajeSetJugador2);

    void mostrarTieBreakPuntaje(Integer puntajeSetJugador1, Integer puntajeSetJugador2);

    void mostrarGanadorSet(Jugador jugador);

    void mostrarPuntajeMatch(Integer puntajeMatchJugador1, Integer puntajeMatchJugador2);

    void mostrarGanadorMatch(Jugador jugador, String match);

    void mostrarMensajeBienvenida();

    void mostrarMensajeRevancha();

    void mostrarBannerDeCierre();


}
