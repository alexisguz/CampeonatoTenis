package negocio;

public class MostrarInformacionImplementacion implements MostrarInformacion{
    @Override
    public void mostrarJugadorSaca(String nombre) {
        System.out.println(nombre + " tiene el saque!");
    }
    @Override
    public void mostrarPuntoGame(Jugador jugador) {
        System.out.println(jugador.getNombre() + " hizo 1 punto!");
    }

    @Override
    public void mostrarPuntajeGame(String gamePuntajeJugador1, String gamePuntajeJugador2) {
        System.out.println("El puntaje actual del Game es: ( " + gamePuntajeJugador1 + " - " + gamePuntajeJugador2 + " )");
    }

    @Override
    public void anunciarReglaDeuce() {
        System.out.println("La regla Deuce fue aplicada!");
    }

    @Override
    public void mostrarGanadorGame(Jugador jugador) {
        System.out.println("\n\nEl ganador del game es: " + jugador.getNombre());
    }

    @Override
    public void mostrarPuntajeSet(Integer puntajeSetJugador1, Integer puntajeSetJugador2) {
        System.out.println("\n\nEl puntaje actual del Set es: ( " + puntajeSetJugador1 + " - " + puntajeSetJugador2 + " )\n\n");
    }

    @Override
    public void mostrarTieBreakPuntaje(Integer puntajeSetJugador1, Integer puntajeSetJugador2) {
        System.out.println("El puntaje actual de Tie Break es: ( " + puntajeSetJugador1 + " - " + puntajeSetJugador2 + " )\n\n");
    }

    @Override
    public void mostrarGanadorSet(Jugador jugador) {
        System.out.println("El ganador del Set es: " + jugador.getNombre() + "\n\n");
    }

    @Override
    public void mostrarPuntajeMatch(Integer puntajeMatchJugador1, Integer puntajeMatchJugador2) {
        System.out.println("\n\nEl puntaje del Match es: ( " + puntajeMatchJugador1 + " - " + puntajeMatchJugador2 + " )\n\n");
    }

    @Override
    public void mostrarGanadorMatch(Jugador jugador, String nombreTorneo) {
        System.out.println("///////////////////// F E L I C I D A D E S /////////////////////////");
        System.out.println("");
        System.out.println("EL GANADOR DEL MATCH ES " + (jugador.getNombre()).toUpperCase() + " EN EL TORNEO DE "+ (nombreTorneo).toUpperCase() );
        System.out.println("");
        System.out.println("///////////////////// F E L I C I D A D E S /////////////////////////");

    }

    @Override
    public void mostrarMensajeBienvenida() {
        System.out.println("-----------------------------------------------");
        System.out.println("¡BIENVENIDO AL SIMULADOR DE PARTIDOS DE TENIS!");
        System.out.println("-----------------------------------------------");
    }

    @Override
    public void mostrarMensajeRevancha() {
        System.out.println("-----------------------------------------------");
        System.out.println("¡QUE COMIENCE LA REVANCHA!");
        System.out.println("-----------------------------------------------");
    }

    @Override
    public void mostrarBannerDeCierre() {
        System.out.println("------------------------------------------------------");
        System.out.println("¡GRACIAS POR USAR EL SIMULADOR DE PARTIDOS DE TENIS!");
        System.out.println("------------------------------------------------------");
    }



}
