public class Usuarios { // Clase que alamcena la información del usuario

    //Atributo de cada Usuario
    private String nombre;
    private String correo;
    private String preferencias;
    private Reservas[] reservas; //Arreglo de tipo Reservas para almacenar las reservas del usuario

    public Usuarios(String nombre, String correo, String preferencias) { //Constructor del usuario solo con correo nombre y preferencias
        this.nombre = nombre;
        this.correo = correo;
        this.preferencias = preferencias;
    }
    public Usuarios(String nombre, String correo, String preferencias, Reservas[] reservas) { //Constructor del usuario incluyendo las reservas
        this.nombre = nombre;
        this.correo = correo;
        this.preferencias = preferencias;
        this.reservas = reservas;
    }

    //getters y setters del Usuario
    public String getCorreo() {
        return correo;
    }
    public String getNombre() {
        return nombre;
    }
    public String getPreferencias() {
        return preferencias;
    }
    public Reservas[] getReservas(){
        return this.reservas;
    }
    public void setReservas(Reservas[] reservas) {
        this.reservas = reservas;
    }
}