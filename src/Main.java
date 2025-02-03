class Main {
    public static void main(String[] args) {
        Database baseDatos = new Database("databaseUser.txt", "databaseDestinos.txt"); //Obtiene los usuarios y los destinos de la base de datos e instancia un nuevo objeto.
        Usuarios sesion = null; // Define la sesion nula.
        boolean bandera = true; //Variable de control que decide si el programa finaliza o no.
        boolean estaEscogiendoLugar = false; //Define si el usuario esta escogiendo destino.
        String destino;
        boolean verDestinoPreferencias = true;
        boolean banderaSesion = true; // Bandera que determina si el usuario sale del menu después de iniciar sesion.
        DestinoEspacial[] destinosPreferencias; //Destinos segun las preferencias del usuario
        String entrada;
        String decisionSesion = "";
        String[] preferencias = {"Aventura", "Relajacion", "Exploracion", "Vistas", "Educacion"}; //Define todas las preferencias disponibles.

        while (bandera) {
            if (sesion == null) { //Determina si el usuario inició sesión o se registró.
                String[] opcAuxiliar = {"1", "2"};
                entrada = Menu.menuDecision(Imprimir.mensajeArchivo("mensajeBienvenida.txt") + "\n1. Iniciar sesión\n2. Registrarse\n", opcAuxiliar, 3, 1, true); //Menú inicial.
                if (Integer.parseInt(entrada) == 1){ //Inicio de sesión
                    if(!(baseDatos.getListaUsuarios().length == 0 || baseDatos.getListaUsuarios() == null)) {//Determina si hay usuarios en la base de datos o no.
                        String nombre;
                        nombre = Menu.menuEntrada(Imprimir.mensajeArchivo("nombre.txt"), Utilidades.obtenerArrayAbcedario(), false, true, baseDatos.getListaUsuarios());
                        sesion = baseDatos.getListaUsuarios()[Database.indiceUsuario(nombre, baseDatos.getListaUsuarios())];
                    }
                    else { //Imprime un error si no hay usuarios en la base de datos.
                        Imprimir.imprimirError("No existen usuarios en la base de datos, por favor registrarse...");
                        Utilidades.temporizador(10000);
                    }

                } else if (Integer.parseInt(entrada) == 2) { //Registro
                    String encabezadoRegistro = Imprimir.mensajeArchivo("registro.txt"); //Encabezado del registro.
                    String nombre = Menu.menuEntrada(encabezadoRegistro, Utilidades.obtenerArrayAbcedario(), false, false, baseDatos.getListaUsuarios()); //Pregunta al usuario su nombre
                    String correo = Menu.menuEntrada(encabezadoRegistro, Utilidades.obtenerArrayAbCorreo(), true, false, baseDatos.getListaUsuarios()); //Pregunta al usuario su correo
                    String[] opcAuxx = {"1","2","3","4","5"}; //Opciones disponibles para elegir.
                    String decisionPref = Menu.menuDecision(Imprimir.imprimirPreferencias(), opcAuxx, 6,1, false); //Le pregunta al usuario que preferencias de viaje desea.
                    if(!decisionPref.equalsIgnoreCase("6")) { //Condicional que sirve para asignarle al usuario la sesion, guardar el nuevo usuario y meterlo en la lista de usuarios de la base de datos.
                        baseDatos.setListaUsuarios(Database.anadirUsuario(new Usuarios(nombre, correo, preferencias[Integer.parseInt(decisionPref) - 1]), baseDatos.getListaUsuarios()));
                        Database.guardarDatos(guardarDatos.lineasUsuario(baseDatos.getListaUsuarios()), baseDatos.getNombreUsers());
                        sesion = baseDatos.getListaUsuarios()[baseDatos.getListaUsuarios().length - 1];
                    }
                } else { //Estructura de control que se ejecuta si el usuario decide salir del programa
                    Utilidades.limpiarPantalla();
                    System.out.println(Imprimir.mensajeArchivo("despedida.txt"));
                    System.out.print("Nos encontraremos en otra aventura espacial...");
                    bandera = false;
                }
            }
            else { //En este caso el usuario tiene una sesion activa y entra en un ciclo mientras no vuelva al menu de inicio.
                while(banderaSesion){
                    String[] OpcAuxiliar2 = {"1","2","3","4"};
                    if(!estaEscogiendoLugar) { //Estructura de control que se activa si el usuario no está escogiendo lugar y le despliega las opciones disponibles.
                        decisionSesion = Menu.menuDecision(   Imprimir.imprimirMenuUsuario() +"\n\nBienvenido " + sesion.getNombre() + ", ¿Qué deseas hacer hoy?\n\n1. Ver tus Reservas\n2. Reservar viaje\n3. Cancelar un viaje\n", OpcAuxiliar2, 4, 1, false);
                    }
                    if(Integer.parseInt(decisionSesion) == 1){ //Este bloque se ejecuta si el usuario quiere ver sus reservas.
                        if(sesion.getReservas() == null || sesion.getReservas().length == 0){ //El bloque se ejecuta si el usuario no tiene viajes agendados
                            Imprimir.imprimirError("No tienes viajes agendados, lo sentimos...");
                            Utilidades.temporizador(20000); //Temporizador que congela la pantalla por unos instantes
                        }
                        else { //Se ejecuta si el usuario tiene viajes agendados
                            Utilidades.limpiarPantalla();
                            Imprimir.mostrarReservas(sesion.getReservas(), Imprimir.mensajeArchivo("reservas.txt") + "\nEstas son tus reservas " + sesion.getNombre() + ":\n", baseDatos.getListaDestinos());
                            Utilidades.temporizador(300000); //Temporizador que congela la pantalla por unos instantes
                        }
                    }
                    else if(Integer.parseInt(decisionSesion) == 2){ //Bloque que se ejecuta si el usuario quiere asignar una nueva reserva
                        estaEscogiendoLugar = true; //Se activa la variable de control que determina si el usuario está eligiendo lugar o no.
                        if(verDestinoPreferencias) { //Bloque que se ejecuta si el usuario quiere ver los destinos que cumplan con sus prefernecias
                            destinosPreferencias = Utilidades.obtenerDestinoPreferencias(baseDatos.getListaDestinos(), sesion.getPreferencias()); //Asigna que los destinos a desplegar sean los que ucmplen con las preferencias del usuario.
                            if(destinosPreferencias.length == 0){ //Bloque que se ejecuta si no hay destinos que cumplan con las preferencias del usuario.
                                verDestinoPreferencias = false;
                                destinosPreferencias = baseDatos.getListaDestinos(); //Asigna que se muestren todos los destinos
                                Imprimir.imprimirError("No hay destinos que cumplan tus preferencias, te mostraremos todos los destinos...");
                                Utilidades.temporizador(10000); //Temporizador que congela la pantalla por unos instantes
                            }
                        }
                        else{
                            destinosPreferencias = baseDatos.getListaDestinos(); //Muestra todos los destinos disponibles.
                        }
                        if(verDestinoPreferencias) { //Muestra los destinos según si el usuario quiere ver sus preferencias o no.
                            destino = Menu.menuDecision(Imprimir.imprimirDestino() + "\n\nBienvenido aquí están los que cumplen con tus preferencias: \n\n" + Utilidades.obtenerDestinoStrings(destinosPreferencias) + (destinosPreferencias.length + 1) + ". Visualizar todas los destinos" + "\n", Utilidades.obtenerArrayNumeros(destinosPreferencias.length + 1), destinosPreferencias.length + 2, Integer.toString(destinosPreferencias.length).length(), false);
                        }

                        else{
                            destino = Menu.menuDecision(Imprimir.imprimirDestino() + "\n\nBienvenido aquí están todos los destinos: \n\n" + Utilidades.obtenerDestinoStrings(destinosPreferencias) + (destinosPreferencias.length + 1) + ". Visualizar los destinos acorde a tus preferencias" + "\n", Utilidades.obtenerArrayNumeros(destinosPreferencias.length + 1), destinosPreferencias.length + 2, Integer.toString(destinosPreferencias.length).length(), false);
                        }
                        if(!destino.equalsIgnoreCase((destinosPreferencias.length + 2) + "") && !destino.equalsIgnoreCase((destinosPreferencias.length + 1) + "")){ //Evalua si el usuario escogió una opcion distinta a salirse de el menu o cambiar de destinos
                            String[] disponibilidad = destinosPreferencias[Integer.parseInt(destino) - 1].getDisponibilidad(); //Obtiene la disponibilidad del destino escogido.
                            if(disponibilidad == null || disponibilidad.length == 0){ //Evalua si el destino tiene fechas disponibles o no.
                                Imprimir.imprimirError("No hay disponibilidad de fechas para este destino, escoja otro por favor...");
                                Utilidades.temporizador(10000);
                            }
                            else{ //Muestra las fechas disponibles y le pregunta al usuario cual desea escoger
                                String fecha = Menu.menuDecision( "Estas son las fechas Disponibles:" + "\n\n"+ Utilidades.enlistarElementosArray(disponibilidad), Utilidades.obtenerArrayNumeros(disponibilidad.length), disponibilidad.length + 1, Integer.toString(disponibilidad.length).length(), false);
                                if(Integer.parseInt(fecha) != disponibilidad.length + 1){ //Evalua si el usuario quiere devolverse o no.
                                    if(sesion.getReservas() == null || sesion.getReservas().length == 0){ //Evalua si el usuario no tiene reservas anteriormente, si no tiene reservas crea un objeto nuevo, sino usa el que ya está asignado.
                                        sesion.setReservas( Utilidades.agregarElementoReserva(new Reservas[0], new Reservas(destinosPreferencias[Integer.parseInt(destino) - 1], disponibilidad[Integer.parseInt(fecha) - 1])));
                                    }
                                    else {
                                        sesion.setReservas(Utilidades.agregarElementoReserva(sesion.getReservas(), new Reservas(destinosPreferencias[Integer.parseInt(destino) - 1], disponibilidad[Integer.parseInt(fecha) - 1])));
                                    }
                                    //Le quita la fecha escogida a la disponibilidad del destino, además agrega guarda la base de datos de usuarios y destinos e imprime un mensaje de exito.
                                    destinosPreferencias[Integer.parseInt(destino) - 1].setDisponibilidad(Utilidades.retirarElementoString(destinosPreferencias[Integer.parseInt(destino) - 1].getDisponibilidad(), Integer.parseInt(fecha) - 1));
                                    Database.guardarDatos(guardarDatos.lineaDestinos(baseDatos.getListaDestinos()), baseDatos.getNombreDestinos());
                                    Database.guardarDatos(guardarDatos.lineasUsuario(baseDatos.getListaUsuarios()), baseDatos.getNombreUsers());
                                    Imprimir.imprimirExito("Reserva realizada correctamente, volviendo al menú de usuario...");

                                }
                            }

                        }
                        else if(destino.equalsIgnoreCase((destinosPreferencias.length + 1) + "")){//Esta opcion sucede cuando el usuario quiere ver destinos de sus preferencias o todos los destinos.
                            verDestinoPreferencias = !verDestinoPreferencias;
                        }
                        else{
                            estaEscogiendoLugar = false; //Vuelve al menu de inicio de sesion reseteando las variables de control
                            verDestinoPreferencias = true;
                        }
                    }
                    else if(Integer.parseInt(decisionSesion) == 3){ //Sirve para eliminar la reserva
                        if(sesion.getReservas() == null || sesion.getReservas().length == 0){//Evalua si el usuario tiene reservas hechas con anterioridad.
                            Imprimir.imprimirError("Usted no ha hecho ninguna reserva, volviendo al menú anterior..."); //Imprime un mensaje de error.
                        }
                        else{
                            //Pregunta al usuario cual de sus reservas desea eliminar
                            String resEliminar = Menu.menuDecision(Imprimir.mensajeArchivo("reservas.txt")  + "\nHe aquí los viajes que has agendado: \n\n"+ Utilidades.obtenerStringReservas(sesion.getReservas()), Utilidades.obtenerArrayNumeros(sesion.getReservas().length), sesion.getReservas().length + 1, Integer.toString(sesion.getReservas().length + 1).length(), false);
                            if(!resEliminar.equalsIgnoreCase(Integer.toString(sesion.getReservas().length + 1))){ //Evalua si el usuario no seleccionó la ultima opcion que es la de volver al menu anterior.
                                //Este bloque condicional realiza todas las tareas necesarias para borrar la reserva.
                                //crea una variable destino reserva para guardar la decision del usuario, despues le agrega de nuevo la disponibilidad al destino ya que el usuario cancelo la reserva.
                                //Le quita al usuario la reserva correspondiente, guarda los datos pertinentes e imprime exito.
                                DestinoEspacial destinoReserva = sesion.getReservas()[Integer.parseInt(resEliminar) - 1].getDestino();
                                destinoReserva.setDisponibilidad(Utilidades.agregarElementoString(destinoReserva.getDisponibilidad(), sesion.getReservas()[Integer.parseInt(resEliminar) - 1].getFecha()));
                                sesion.setReservas(Utilidades.retirarElementoReserva(sesion.getReservas(), Integer.parseInt(resEliminar) - 1));
                                Database.guardarDatos(guardarDatos.lineasUsuario(baseDatos.getListaUsuarios()), baseDatos.getNombreUsers());
                                Database.guardarDatos(guardarDatos.lineaDestinos(baseDatos.getListaDestinos()), baseDatos.getNombreDestinos());
                                Imprimir.imprimirExito("La reserva fue cancelada exitosamente");
                            }
                        }
                    }
                    else{//La condicion sirve para cuando el usuario quiere devolverse al menu de inicio, entonces lleva todas las variables a su estado inicial.
                        estaEscogiendoLugar = false;
                        verDestinoPreferencias = false;
                        banderaSesion = false;
                        sesion = null;
                    }
                }
            }
        }
    }
}

// Nosotros, Juan Pablo de Jesús Avendaño Bustamante y Diego Andrés Gonzalez Graciano, el día 18/11/2023 a las 8:00 p.m., declaramos que el código expuesto anteriormente está realizado al 100% por nuestras personas y que no copiamos ni mandamos a realizar ninguna parte de este proyecto.