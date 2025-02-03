public class DestinoEspacial { //Clase para almacenar los datos de los Destinos

    //Atributos de los Destinos
    private String nombre;
    private String descripcion;
    private double costo;
    private String tipoLugar;
    private String[] disponibilidad;
    private String equipoNecesario;
    private String recomendaciones;

    public DestinoEspacial(String nombre, String descripcion, double costo, String tipoLugar, String equipoNecesario, String recomendaciones, String[] disponibilidad){ //Constructor para el Destino Espacial que guarda la información
        this.nombre = nombre;
        this.equipoNecesario = equipoNecesario;
        this.recomendaciones = recomendaciones;
        this.descripcion = descripcion;
        this.costo = costo;
        this.tipoLugar = tipoLugar;
        this.disponibilidad = disponibilidad;
    }

    //getters y setters de los Destinos
    public String getNombre(){
        return nombre;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public double getCosto(){
        return costo;
    }
    public void setDisponibilidad(String[] disponibilidad){
        this.disponibilidad = disponibilidad;
    }
    public String[] getDisponibilidad(){
        return disponibilidad;
    }
    public String getTipoLugar(){
        return this.tipoLugar;
    }
    public String getEquipoNecesario(){
        return this.equipoNecesario;
    }
    public String getRecomendaciones(){
        return this.recomendaciones;
    }
}