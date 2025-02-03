import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Utilidades { //Esta clase nos ayuda con distinas funciones que son muy útiles.

    public static String[] obtenerDatos(char separador, String cadenaSeparar, int numeroDatos){ //Ayuda a obtener los datos de la base de datos
        String[] listaDatos = new String[numeroDatos];
        int indiceDisponible = 0;
        String stringAux = "";
        for(int contador = 0; contador < cadenaSeparar.length(); contador++){ // Verifica si el caracter actual es igual al separador ( "|" )
            if(cadenaSeparar.charAt(contador) == separador){
                listaDatos[indiceDisponible] = stringAux;
                indiceDisponible++;
                stringAux = "";
            }
            else{
                stringAux = stringAux + cadenaSeparar.charAt(contador);
            }
        }
        return listaDatos;
    }
    public static int contarSeparador(char separador, String cadena){ // Cuenta el número de veces que se encuentra el separador en una línea
        int numeroSeparador = 0;
        for(int contador = 0; contador < cadena.length(); contador++){
            if(cadena.charAt(contador) == separador){
                numeroSeparador++;
            }
        }
        return numeroSeparador;
    }
    public static int contarLineasArchivo(File archivo){ // Ayuda a contar el número de líneas de un archivo determinado
        int contador = 0;
        try {
            Scanner lectura = new Scanner(archivo);
            while(lectura.hasNext()){
                contador++;
                lectura.nextLine();
            }
        }
        catch(IOException excepcion){
        }
        return contador;
    }
    public static String[] subArreglo(int indiceInicial, int indiceFinal, String[] arregloOriginal){ //Función para crear un subArreglo a partir de un arreglodado
        if(indiceFinal < indiceInicial){ //Verifica si los indices están orden correcto (Swap)
            int Aux = indiceFinal;
            indiceFinal = indiceInicial;
            indiceInicial = Aux;
        }

        String[] nuevoArreglo = new String[indiceFinal - indiceInicial];
        int indiceNuevo = 0;

        for(int i = 0; i < arregloOriginal.length; i++){
            if(i >= indiceInicial && i < indiceFinal){
                nuevoArreglo[indiceNuevo] = arregloOriginal[i];
                indiceNuevo++;
            }
        }
        return nuevoArreglo;
    }
    public static int encontrarNombreDestinos(String palabra, DestinoEspacial[] destinos) { //Busca el nombre de un destino entre los Destinos asignados del sistema
        for (int contador = 0; contador < destinos.length; contador++) {
            if (destinos[contador] != null && destinos[contador].getNombre() != null && destinos[contador].getNombre().equalsIgnoreCase(palabra)) {
                return contador;
            }
        }
        return -1;
    }
    public static String obtenerDestinoStrings(DestinoEspacial[] destinos){ //Función para obtener los datos de todos los destinos.
        String Aux = "";
        for(int i = 0; i < destinos.length; i++){
            Aux += (i + 1) + ". " + destinos[i].getNombre() + "\n\t" + "Descripción: " + destinos[i].getDescripcion() + "\n\tCosto del viaje: $" + destinos[i].getCosto() + "\n\tEquipo necesario para el viaje: " + destinos[i].getEquipoNecesario() + "\n\tRecomendaciones: " + destinos[i].getRecomendaciones() + "\n";
        }
        return Aux;
    }
    public static String[] agregarElementoString(String[] cadena, String Elemento){ //Sirve para añadir un elemento a un arreglo
        String[] cadenaAux = new String[cadena.length + 1];

        for(int i = 0; i < cadena.length; i++){
            cadenaAux[i] = cadena[i];
        }
        cadenaAux[cadena.length] = Elemento;
        return cadenaAux;
    }
    public static Reservas[] agregarElementoReserva(Reservas[] reservas, Reservas Elemento){ //Para agregar una nueva reserva al arreglo de Reservas[]
        Reservas[] reservasNueva = new Reservas[reservas.length + 1];

        for(int i = 0; i < reservas.length; i++){
            reservasNueva[i] = reservas[i];
        }
        reservasNueva[reservas.length] = Elemento;
        return reservasNueva;
    }
    public static DestinoEspacial[] obtenerDestinoPreferencias(DestinoEspacial[] destinos, String tipoLugar) { //Obtiene un arreglo con los tipos de preferencias
        int contador = 0;

        DestinoEspacial[] destinosAux = new DestinoEspacial[destinos.length];
        for (int i = 0; i < destinos.length; i++) {
            if (destinos[i].getTipoLugar().equalsIgnoreCase(tipoLugar)) {
                destinosAux[contador] = destinos[i];
                contador++;
            }
        }

        DestinoEspacial[] destinoAux2 = new DestinoEspacial[contador];
        for (int i = 0; i < contador; i++) {
            destinoAux2[i] = destinosAux[i];
        }
        return destinoAux2;
    }
    public static void limpiarPantalla(){ //Limpia la pantalla del usuario
        for(int i = 0; i < 25; i++){
            System.out.println();
        }
    }
    public static String[] obtenerArrayNumeros(int numero){ //Devuelve un arreglo con una determinada cantidad de números
        String[] stringAux =  new String[numero];
        for(int contador = 0; contador < numero; contador++){
            stringAux[contador] = Integer.toString(contador + 1);
        }
        return stringAux;
    }
    public static String[] obtenerArrayAbcedario(){ //Función para convertir el abecedario en un arreglo y asignar cada letra a una posición
        String abecedario = "abcdefghijklmñopqrstuvwxyz";
        String[] abc = new String[abecedario.length()];
        for(int contador = 0; contador < abecedario.length(); contador++){
            abc[contador] = Character.toString(abecedario.charAt(contador));
        }
        return abc;
    }
    public static String[] obtenerArrayAbCorreo(){ //Obtiene igualmente las letras del abecedario pero se incluye el '@' y el '.'
        String abecedario = "abcdefghijklmñopqrstuvwxyz@.";
        String[] abc = new String[abecedario.length()];
        for(int contador = 0; contador < abecedario.length(); contador++){
            abc[contador] = Character.toString(abecedario.charAt(contador));
        }
        return abc;
    }
    public static String enlistarElementosArray(String[] arreglo){  //Ayuda a transformar los elementos de un arreglo en una lista
        String stringAux = "";
        for(int contador = 0; contador < arreglo.length; contador++){
            stringAux += (contador + 1) + ". " + arreglo[contador] + "\n";
        }
        return stringAux;
    }
    public static Reservas[] retirarElementoReserva(Reservas[] arreglo, int indiceElemento){ // Retira un elemento de las Reservas del usuario
        Reservas[] nuevaReservas = new Reservas[arreglo.length - 1];
        int indiceArreglo = 0;
        for(int contador = 0; contador < arreglo.length; contador++){
            if(contador != indiceElemento){
                nuevaReservas[indiceArreglo] = arreglo[contador];
                indiceArreglo++;
            }
        }
        return nuevaReservas;
    }
    public static String[] retirarElementoString(String[] arreglo, int indiceElemento){ // Retira un determinado elemento de un arreglo
        String[] nuevoString = new String[arreglo.length - 1];
        int indiceArreglo = 0;
        for(int contador = 0; contador < arreglo.length; contador++){
            if(contador != indiceElemento){
                nuevoString[indiceArreglo] = arreglo[contador];
                indiceArreglo++;
            }
        }
        return nuevoString;
    }
    public static void temporizador(int tiempo){ //Hace iteraciones repetitivas con el fin de simular una congelación de la pantalla.
        for(int contador = 0; contador < tiempo*999; contador++){
            System.out.print("");
        }
    }
    public static boolean hayDestinoReservas(String nombreDestino, Reservas[] reservas){  //Verifica si un destino se encuetra entre las reservas del usuario
        for (int i = 0; i < reservas.length; i++){
            if (reservas[i].getDestino().getNombre().equalsIgnoreCase(nombreDestino)){
                return true;
            }
        }
        return false;
    }
    public static String obtenerStringArchivo(String nombreArchivo){ //Devuelve el contenido de un archivo en una cadena de texto
        String aux = "";
        try {
            File archivo = new File(nombreArchivo);
            Scanner scan = new Scanner(archivo);
            while (scan.hasNext()) {
                aux = aux + scan.nextLine() + "\n";
            }
            scan.close();
        }
        catch (IOException exception){

        }
        return aux;
    }
    public static String obtenerStringReservas(Reservas[] reservas){ //Devuelve las reservas en una cadena
        String aux = "";
        for(int contador = 0; contador < reservas.length; contador++){
            aux += (contador + 1) + ". " + reservas[contador].getDestino().getNombre() + " - " + reservas[contador].getFecha() + "\n";
        }
        return aux;
    }
}