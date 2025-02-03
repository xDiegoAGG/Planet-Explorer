import java.io.File;
public class Imprimir { //Clase para imprimir distintos mensajes y lo que se necesite

    public static void mostrarReservas(Reservas[] reservas, String encabezado, DestinoEspacial[] destinos) {  //Imprime las reservas

        int contadorAux = 0;
        String nombreDestino;
        String costo;
        String equipo;

        System.out.print(encabezado);
        for (int contador = 0; contador < destinos.length; contador++) { //Bloque que itera en los destinos disponibles
            if(Utilidades.hayDestinoReservas(destinos[contador].getNombre(), reservas)){//Verifica si el usuario reservó en el destino actual.
                //Define las variables necesarias para desplegar el destino.
                contadorAux += 1;
                nombreDestino = destinos[contador].getNombre();
                costo = destinos[contador].getCosto() + "";
                equipo = destinos[contador].getEquipoNecesario();
                System.out.print( "\n" + (contadorAux) +". " + nombreDestino + "\n\tCosto: " + costo + "\n\tEquipo necesario: " + equipo + "\n\tFechas:");
                for(int contador2 = 0; contador2 < reservas.length; contador2++){//Itera en las reservas del usuario para obtener todas las reservas que coincidan con el destino actual.
                    if(reservas[contador2].getDestino().getNombre().equalsIgnoreCase(nombreDestino)){
                        System.out.print("\n\t\t" + reservas[contador2].getFecha());
                    }
                }
            }
        }
    }
    public static String mensajeArchivo(String nombreArchivo) { //Imprime el texto de un determinado archivo
        return Utilidades.obtenerStringArchivo(nombreArchivo);
    }
    public static void imprimirError(String mensajeAdicional){ //Imprime el texto de Error
        Utilidades.limpiarPantalla();
        System.out.print(" _____                          \n|  ___|                         \n| |__   _ __  _ __   ___   _ __ \n|  __| | '__|| '__| / _ \\ | '__|\n| |___ | |   | |   | (_) || |   \n\\____/ |_|   |_|    \\___/ |_|   \n\n");
        System.out.print(mensajeAdicional);
        Utilidades.temporizador(25000);
    }
    public static void imprimirExito(String mensajeAdicional){ //Imprime el mensaje de Exito
        Utilidades.limpiarPantalla();
        System.out.print(" _____        _  _          \n|  ___|      (_)| |         \n| |__  __  __ _ | |_   ___  \n|  __| \\ \\/ /| || __| / _ \\ \n| |___  >  < | || |_ | (_) |\n\\____/ /_/\\_\\|_| \\__| \\___/ " + "\n\n");
        System.out.print(mensajeAdicional);
        Utilidades.temporizador(20000);
        Utilidades.limpiarPantalla();
    }
    public static String imprimirPreferencias(){ //Imprime los tipos de preferencias
        String preferencias = "Por favor Elige tu tipo de viaje preferido:\n\n1. Aventura\n2. Relajacion\n3. Exploracion\n4. Vistas\n5. Educacion\n";
        return preferencias;
    }
    public static String imprimirDestino(){ //Imprime el mensaje de destinos
        return "______             _    _                    \n|  _  \\           | |  (_)                   \n| | | |  ___  ___ | |_  _  _ __    ___   ___ \n| | | | / _ \\/ __|| __|| || '_ \\  / _ \\ / __|\n| |/ / |  __/\\__ \\| |_ | || | | || (_) |\\__ \\\n|___/   \\___||___/ \\__||_||_| |_| \\___/ |___/";
    }
    public static String imprimirMenuUsuario(){ //Imprime el mensaje de Menú de Usuario
        return "___  ___                                                      _        \n|  \\/  |                                                     (_)       \n| .  . |  ___  _ __   _   _   _   _  ___  _   _   __ _  _ __  _   ___  \n| |\\/| | / _ \\| '_ \\ | | | | | | | |/ __|| | | | / _` || '__|| | / _ \\ \n| |  | ||  __/| | | || |_| | | |_| |\\__ \\| |_| || (_| || |   | || (_) |\n\\_|  |_/ \\___||_| |_| \\__,_|  \\__,_||___/ \\__,_| \\__,_||_|   |_| \\___/ ";
    }
}