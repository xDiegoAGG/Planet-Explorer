public class guardarDatos { //Clase para guardar los datos de la base de datos

    public static String lineasUsuario(Usuarios[] listaUsers){ //Crea la linea que se va a guardar en el archivo base de datos de usuarios.
        String lineaUser = ""; //Define la linea como vacia
        for(int contador = 0; contador < listaUsers.length; contador++){//Por cada usuario va obteniendo su respectiva linea, y la concatena a lineaUser
            Usuarios userActual = listaUsers[contador];
            lineaUser += userActual.getNombre() + "|" + userActual.getCorreo() + "|" + userActual.getPreferencias() +"|";
            lineaUser += lineaReservas(userActual.getReservas());
            if(contador != listaUsers.length - 1){
                lineaUser += "\n";
            }
        }
        return lineaUser; //Retorna un string con todos los datos de los usuarios con sus respectivos separadores y saltos de linea
    }
    public static String lineaReservas(Reservas[] reservas){ //Crea las líneas de las reservas
        String aux = "";
        Reservas reservaActu;
        if (reservas != null) {
            for (int contador = 0; contador < reservas.length; contador++) {
                reservaActu = reservas[contador];
                if (reservaActu != null && reservaActu.getDestino() != null) {
                    aux += reservaActu.getDestino().getNombre() + "|" + reservaActu.getFecha() + "|";
                }
            }
        }
        return aux;
    }
    public static String lineaDestinos(DestinoEspacial[] destinos){ //Crea las líneas de los Destinos, sigue una lógica similar al método lineaUsers
        String aux = "";
        DestinoEspacial destinoActu;
        if(!(destinos == null)){
            for(int contador = 0; contador < destinos.length; contador++){
                destinoActu = destinos[contador];
                aux += destinoActu.getNombre() + "|" + destinoActu.getDescripcion() + "|" + destinoActu.getCosto() + "|" + destinoActu.getTipoLugar() + "|" + destinoActu.getEquipoNecesario() + "|" + destinoActu.getRecomendaciones() + "|";
                if(!(destinoActu.getDisponibilidad() == null)) {
                    aux += lineaDisponibilidad(destinoActu.getDisponibilidad());
                }
                if(contador != destinos.length - 1){
                    aux += "\n";
                }
            }
        }
        return aux;
    }
    public static String lineaDisponibilidad(String[] disponibililidad){ //Guarda los datos de las líneas de las fechas disponibles
        String aux = "";
        for(int contador = 0; contador < disponibililidad.length; contador++){
            aux += disponibililidad[contador] + "|";
        }
        return aux;
    }
}