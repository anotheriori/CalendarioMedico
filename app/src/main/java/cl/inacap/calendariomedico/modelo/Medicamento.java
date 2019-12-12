package cl.inacap.calendariomedico.modelo;

public class Medicamento {

    private int idFoto;
    private String hora;
    private String cantidad;
    private String nombre;
    private boolean estado; //true si está pendiente - false si está comprado
    private static final boolean PENDIENTE=true;

    public Medicamento(int idFoto, String hora, String cantidad, String nombre, boolean estado) {
        this.idFoto = idFoto;
        this.hora = hora;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Medicamento(int idFoto, String hora, String cantidad, String nombre) {
        this.idFoto = idFoto;
        this.hora = hora;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.estado=PENDIENTE;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        //Devuelve el nombre del producto y si está comprado o pendiente
        String activo;
        if(estado) activo="Activa";
        else activo="Tomado";
        return nombre + ": " + activo;
    }
}
