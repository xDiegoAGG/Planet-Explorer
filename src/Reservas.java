public class Reservas {  //Clase que almacena la información de las Reservas

    //Atributos de la clase Reservas
    private DestinoEspacial destino;  //Guarda un destino
    private String fecha;

    public Reservas(DestinoEspacial destino, String fecha){ //Constructor para las reservas
        this.destino = destino;
        this.fecha = fecha;
    }

    //getters y setters
    public DestinoEspacial getDestino() {
        return destino;
    }
    public String getFecha(){
        return fecha;
    }
}