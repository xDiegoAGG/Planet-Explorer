import java.util.Scanner;
public class Menu { //En esta clase se encuentran las funciones para todos los menus usados durante el programa
    public static String menuDecision(String mensaje, String[] opcionesPermitidas, int opcionMaxima, int longitudMaxima, boolean esInicio){ //Menú utilizado para todas los casos donde el usuario deba escoger una opción.
        boolean bandera = true;
        String entrada = "";
        Scanner scan = new Scanner(System.in);
        opcionesPermitidas = Utilidades.agregarElementoString(opcionesPermitidas, Integer.toString(opcionMaxima)); //Agrega al arreglo de opciones permitidas la opción maxima que será la opcion de volver al menu anterior o salir.
        boolean error = false;
        while(bandera){
            Utilidades.limpiarPantalla();
            System.out.print(mensaje);
            if(esInicio){//Bloque condicional que imprime salir del programa o volver al menu anterior segun el valor de el booleano esInicio
                System.out.print(opcionMaxima + ". Salir del programa.\n\n");
            }
            else {
                System.out.print(opcionMaxima + ". Volver al menú anterior.\n\n" );
            }
            if(error){ //Bloque condicional que imprime un mensaje de error dependiendo del booleano error.
                System.out.print("Has cometido un error, por favor selecciona la opción de nuevo:  ");
            }
            else {
                System.out.print("Selecciona la opción de tu gusto: ");
            }
            entrada = scan.nextLine();//Pide al usuario una entrada.
            if(Validacion.validacion(entrada, opcionesPermitidas, longitudMaxima)){ //Verifica si la opción del usuario está entre las opciones permitidas y cumple la longitud máxima.
                bandera = false;
            }
            else {
                error = true;
            }
        }
        return entrada;
    }
    public static String menuEntrada(String mensaje, String[] opcionesPermitidas, boolean esCorreo, boolean inicioSesion, Usuarios[] usuarios){ //Menú utilizado en caso de que el usuario deba ingresar una cadea de texto (Una entrada)
        Scanner scan = new Scanner(System.in);
        boolean bandera = true;
        boolean error = false;
        String tipoError = "";
        String entrada = "";
        while(bandera){//Ciclo que se encarga de pedirle al usuario entradas hasta que alguna sea válida
            Utilidades.limpiarPantalla();
            System.out.println(mensaje);
            if(esCorreo){//Bloque condicional que despliega un mensaje indicandole al usuario su tipo de error, o que debe ingresar un correo.
                if(!error) {
                    System.out.print("Ingresa el correo a registrar: ");
                }
                else if (tipoError.equals("invalido")){
                    System.out.print("El correo ingresado es inválido, ingreselo de nuevo: ");
                }
                else {
                    System.out.print("El correo ingresado ya está registrado, ingreselo de nuevo: ");
                }
            }
            else {
                if(!error) {//Bloque condicional que despliega un mensaje indicandole al usuario su tipo de error, o que debe ingresar un nombre.
                    if(inicioSesion) {
                        System.out.print("Ingresa el nombre de tu cuenta: ");
                    }
                    else {
                        System.out.print("Ingresa el nombre para tu nueva cuenta: ");
                    }
                }
                else if (tipoError.equals("invalido")){ //Imprime un mensaje según el tipo de error que el usuario haya cometido.
                    System.out.print("El nombre ingresado es inválido, por favor ingresalo de nuevo: ");
                }
                else if (tipoError.equals("repetido")){
                    System.out.print("El nombre ingresado ya está registrado, por favor ingresa otro: ");
                }
                else {
                    System.out.print("El nombre ingresado no se encuentra en la base de datos, por favor ingresa otro: ");
                }
            }
            entrada = scan.nextLine(); //Pide al usuario su entrada.

            if(esCorreo){ //Bloque condicional if, else. que se encarga de validar el correo o el nombre.
                if(Validacion.validarCorreo(entrada, opcionesPermitidas, usuarios)){  //Verifica la validez del correo ingresado
                    if(Validacion.correoRepetido(entrada, usuarios)){
                        tipoError = "repetido";
                        error = true;
                    }
                    else {
                        bandera = false;
                    }
                }
                else {
                    error = true;
                    tipoError = "invalido";
                }
            }
            else {
                if(inicioSesion){
                    if(Validacion.validarNombre(entrada, true, usuarios)){
                        bandera = false;
                    }
                    else{
                        error = true;
                        tipoError = "usuarioInexistente";
                    }
                }
                else {
                    if(Validacion.validarNombre(entrada, false, usuarios)){
                        if(Validacion.usuarioRepetido(entrada, usuarios)){
                            error = true;
                            tipoError = "repetido";
                        }
                        else {
                            bandera = false;
                        }
                    }
                    else{
                        error = true;
                        tipoError = "invalido";
                    }
                }
            }
        }
        return entrada;
    }
}