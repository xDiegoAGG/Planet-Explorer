public class Validacion { //Clase que contiene las distinas funciones para realizar la validación y verficación de los datos.

    public static boolean validacion(String cadena, String[] caracteresPermitidos, int longitudMaxima) { // Función para hacer la validación de las dstintas entradas del usuario
        int palValidas = 0;
        for (int contador = 0; contador < cadena.length(); contador++) {
            if (estaEnArreglo(cadena.charAt(contador), caracteresPermitidos)) {
                palValidas++;
            }
        }
        if (longitudMaxima == -1) {
            return cadena.length() == palValidas && cadena.length() > 0;
        } else {
            return cadena.length() == palValidas && cadena.length() > 0 && cadena.length() <= longitudMaxima;
        }
    }
    public static boolean estaEnArreglo(char caracter, String[] arreglo) { // Verifica si un caracter se encuentra en un arrelgo
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i].equalsIgnoreCase(Character.toString(caracter))) {
                return true;
            }
        }
        return false;
    }
    public static boolean validarNombre(String nombre, boolean inicioSesion, Usuarios[] usuarios){ // Para verificar que el nombre del usuario sea un nombre válido
        String palabrasValidas = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKMNÑOPQRSTUVWXYZ";
        int palabrasValidadas = 0;
        int palabrasNoEspacios = 0;
        if(inicioSesion){
            return usuarioRepetido(nombre, usuarios);
        }
        else{
            for(int contador = 0; contador < nombre.length(); contador++){
                if(palabrasValidas.contains(nombre.charAt(contador) + "")){
                    palabrasValidadas += 1;
                    if(nombre.charAt(contador) != ' '){
                        palabrasNoEspacios += 1;
                    }
                }
            }
            return palabrasValidadas == nombre.length() && palabrasNoEspacios >= 4;
        }
    }
    public static boolean validarCorreo(String cadena, String[] caracteresPermitidos, Usuarios[] usuarios){ //Función para verificar que el correo proporcionado por la persona sea un correo válido.
        int charValido = 0;
        int arrobas = 0;
        int puntos = 0;

        for (int i = 0; i < cadena.length(); i++){
            if(estaEnArreglo(cadena.charAt(i), caracteresPermitidos)){
                charValido++;
            }

            if(cadena.charAt(i) == '@'){
                arrobas++;
            }

            if (cadena.charAt(i) == '.'){
                puntos++;
            }
        }
        if(arrobas > 1 || arrobas == 0 || puntos > 1 || puntos == 0 || correoRepetido(cadena.toLowerCase(), usuarios)) {
            return false;
        }
        else {
            return charValido == cadena.length();
        }
    }
    public static boolean usuarioRepetido(String cadena, Usuarios[] listaUsuarios) { //Verifica si el Usuario ingresado ya se encuentra registrado
        for (int i = 0; i < listaUsuarios.length; i++) {
            if (listaUsuarios[i].getNombre().equalsIgnoreCase(cadena)) {
                return true;
            }
        }
        return false;
    }
    public static boolean correoRepetido(String cadena, Usuarios[] listaUsuarios) { //Verifica si el correo ingresado ya se encuentra registrado
        for (int i = 0; i < listaUsuarios.length; i++) {
            if (listaUsuarios[i].getCorreo().equalsIgnoreCase(cadena)) {
                return true;
            }
        }
        return false;
    }
}