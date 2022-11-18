package interfaz;

import negocio.Jugador;
import negocio.Match;
import negocio.MostrarInformacion;
import negocio.MostrarInformacionImplementacion;
import java.util.Scanner;

/**
 * Creado por Alexis Guzman https://github.com/alexisguz.
 */

public class Main {
    public static void main(String[] args) {
        // Se instancia MostrarInformacion
        MostrarInformacion mostrarImplementacionPuntajeSystemOut = new MostrarInformacionImplementacion();

        // Se muestra el mensaje de bienvenida
        mostrarImplementacionPuntajeSystemOut.mostrarMensajeBienvenida();

        // Se instancia el Jugador 1
        Scanner scannerJugador1 = new Scanner(System.in);
        System.out.print("Porfavor introduzca el nombre del Jugador 1: ");
        String jugador1Nombre = scannerJugador1.nextLine();
        System.out.print("Introduzca la probabilidad de " + jugador1Nombre + " (escriba del 1 al 100): ");
        int probJugador1 = Integer.parseInt(scannerJugador1.nextLine());
        Jugador jugador1 = new Jugador(jugador1Nombre,probJugador1);

        // Se instancia el Jugador 2
        Scanner scannerJugador2 = new Scanner(System.in);
        System.out.print("Porfavor introduzca el nombre del Jugador 2: ");
        String jugador2Nombre = scannerJugador1.nextLine();
        System.out.print("Introduzca la probabilidad de " + jugador2Nombre + " (escriba del 1 al 100): ");
        int probJugador2 = Integer.parseInt(scannerJugador1.nextLine());
        Jugador jugador2 = new Jugador(jugador2Nombre,probJugador2);

        // Se instancia el Match
        System.out.print("Introduzca el nombre del torneo: ");
        String nombreTorneo = scannerJugador1.nextLine();
        Match match = new Match(jugador1, jugador2,nombreTorneo);
        match.play(mostrarImplementacionPuntajeSystemOut);

        //Se pregunta si se quiere jugar Revancha
        Scanner scannerRevancha = new Scanner(System.in);
        System.out.print("Desea jugar la revancha (s/n): ");
        String revancha = scannerRevancha.nextLine();
        //Se juega Revancha
        if (revancha.equals("s")){
            mostrarImplementacionPuntajeSystemOut.mostrarMensajeRevancha();
            match = new Match(jugador1, jugador2,nombreTorneo);
            match.play(mostrarImplementacionPuntajeSystemOut);
        }
        else {
            // No se juega Revancha y se muestra el banner de cierre
            mostrarImplementacionPuntajeSystemOut.mostrarBannerDeCierre();
        }









    }
}