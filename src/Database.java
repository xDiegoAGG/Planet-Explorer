import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Database { //Clase para Administrar las Bases de Datos

    //Atributos de la clase
    private File archivoUsers;
    private File archivoDestinos;
    private String nombreUsers;
    private String nombreDestinos;
    private int numeroUsuarios;
    private int numeroDestinos;
    static Usuarios[] listaUsuarios;
    DestinoEspacial[] listaDestinos;

    public Database(String nombreUsers, String nombreDestinos) { //Constructor para crear la base de datos de los Usuarios y de los Destinos
        //Asigna los atributos
        this.nombreUsers = nombreUsers;
        this.nombreDestinos = nombreDestinos;
        this.archivoDestinos = new File(nombreDestinos);
        try{
            if(!archivoDestinos.createNewFile()){ //Si el archivo de destinos ya existe se cuentan las líneas y se definen el número de destinos
                setNumeroDestinos(Utilidades.contarLineasArchivo(archivoDestinos)); //Cuenta cuantos destinos hay y los asigna a su respectivo atributo.
                this.listaDestinos = hacerListaDestinos(numeroDestinos, archivoDestinos); //Llama al método hacerListaDestinos que se encarga de recapitular todos los destinos y sus datos.
            }
        }
        catch (IOException exception) { //Excepción.
        }
        this.archivoUsers = new File(nombreUsers);
        try{ //Realiza el mismo procedimiento anterior pero con los usuarios.
            if(!archivoUsers.createNewFile()){ //Si el archivo de Usuarios ya existe los cuenta y se define la lista
                setNumeroUsuarios(Utilidades.contarLineasArchivo(archivoUsers));
                this.listaUsuarios = hacerListaUsuarios(numeroUsuarios, archivoUsers);
            }
        }
        catch (IOException exception) {
        }
    }
    public DestinoEspacial[] hacerListaDestinos(int numeroDatos, File archivo) { //Crea la lista de destinos Espaciales

        DestinoEspacial[] arregloDestinos = new DestinoEspacial[numeroDatos]; //Crea un arreglo de destinos de tamaño igual al numero de destinos existentes.
        try {
            Scanner lectura = new Scanner(archivo); //Define el scanner que hará la lectura
            for (int contador = 0; contador < numeroDatos; contador++) {//Por cada iteracion define todas un nuevo objeto DestinoEspacial con los datos de ese destino.
                String linea = lectura.nextLine();
                String[] datosDestino = Utilidades.obtenerDatos('|', linea, Utilidades.contarSeparador('|', linea));
                if(datosDestino.length > 4) { //Bloque condicional que sirve para obtener las fechas disponibles de cada destino, finalmente crea el objeto destino.
                    String[] disponible = Utilidades.subArreglo(6, datosDestino.length, datosDestino);
                    arregloDestinos[contador] = new DestinoEspacial(datosDestino[0], datosDestino[1], Double.parseDouble(datosDestino[2]), datosDestino[3], datosDestino[4], datosDestino[5], disponible);
                }
            }

        }
        catch (IOException excepcion) {

        }
        return arregloDestinos; //Retorna la lista con todos los destinos disponibles.
    }
    public Usuarios[] hacerListaUsuarios(int numeroUsers, File archivo){ //Crea la lista de los Usuarios, la lógica es identica a la presentada en el método anterior.
        Usuarios[] arregloUsuarios = new Usuarios[numeroUsers];
        try {
            Scanner lectura = new Scanner(archivo);
            for (int contador = 0; contador < numeroUsers; contador++) {
                String linea = lectura.nextLine();
                String[] datosUsuario = Utilidades.obtenerDatos('|', linea, Utilidades.contarSeparador('|', linea));
                if(datosUsuario.length > 3) {
                    String[] subStringReservas = Utilidades.subArreglo(3, datosUsuario.length, datosUsuario);
                    arregloUsuarios[contador] = new Usuarios(datosUsuario[0], datosUsuario[1], datosUsuario[2], construirReservas(subStringReservas, listaDestinos));
                }
                else{
                    arregloUsuarios[contador] = new Usuarios(datosUsuario[0], datosUsuario[1], datosUsuario[2]);
                }
            }

        }
        catch(IOException excepcion){
        }
        return arregloUsuarios;
    }
    public static Usuarios[] anadirUsuario(Usuarios usuarioGuardar, Usuarios[] listaUsuarios){ //Añade un nuevo usuario a la lista de Usuarios ya registrados
        Usuarios[] nuevaLista = new Usuarios[listaUsuarios.length + 1]; //Crea un nuevo arreglo de tamaño mayor que el listado de usuarios, exactamente en 1.
        for(int contador = 0; contador < listaUsuarios.length; contador++){ //Añade los usuarios que ya existian.
            nuevaLista[contador] = listaUsuarios[contador];
        }
        nuevaLista[listaUsuarios.length] = usuarioGuardar; //En la última posicion añade el usuario deseado.
        return nuevaLista; //Retorna la nueva lista.
    }
    public Reservas[] construirReservas(String[] subStringReserva, DestinoEspacial[] destinos){ //Metodo que sirve para construir todas las reservas de un usuario
        int indiceDestino;
        int indiceArreglo = 0;
        Reservas[] reservas = new Reservas[(int) subStringReserva.length / 2]; //Crea un arreglo de reservas con tamaño igual a la longitud del arreglo que contiene los destinos y las fechas dividido entre 2.
        for(int contador = 0; contador < subStringReserva.length; contador += 2){ //Itera en los indices pares del arreglo con todos los datos de las reservas.
            indiceDestino = Utilidades.encontrarNombreDestinos(subStringReserva[contador], destinos); //Obtiene el indice que se encuentra el destino en el arreglo de destinos
            if(indiceDestino != -1){
                reservas[indiceArreglo] = new Reservas(destinos[indiceDestino], subStringReserva[contador + 1]); //crea la reserva y la almacena en el arreglo de reservas.
                indiceArreglo++;
            }
        }
        return reservas; //Retorna todas las reservas que se pudieron recopilar en el substring.
    }
    public static void guardarDatos(String lineaDatos, String nombreArchivo){ //Guarda los datos en la base de datos
        try {
            FileWriter escribir = new FileWriter(nombreArchivo); //Instancia la clase FileWriter
            escribir.write(lineaDatos); //Escribe en el archivo la linea que contiene todos los usuarios o destinos.
            escribir.close(); //Cierra el FileWriter.
        }
        catch (IOException exception){
        }
    }
    public static int indiceUsuario(String nombreUsuario, Usuarios[] listaUsuarios){ //Obtiene el indice en el que se encuentra un usuario en la lista de Usuarios
        for(int contador = 0; contador < listaUsuarios.length; contador++){
            if(listaUsuarios[contador].getNombre().equalsIgnoreCase(nombreUsuario)){
                return contador;
            }
        }
        return -1;
    }

    //setters y getters de la clase Database
    public void setNumeroUsuarios(int numeroUsuarios){
        this.numeroUsuarios = numeroUsuarios;
    }
    public void setNumeroDestinos(int numeroDestinos) {
        this.numeroDestinos = numeroDestinos;
    }
    public  Usuarios[] getListaUsuarios(){
        return listaUsuarios;
    }
    public void setListaUsuarios(Usuarios[] listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    public DestinoEspacial[] getListaDestinos() {
        return listaDestinos;
    }
    public String getNombreUsers() {
        return nombreUsers;
    }
    public String getNombreDestinos() {
        return nombreDestinos;
    }
}