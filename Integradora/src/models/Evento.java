package models;

import java.time.LocalDateTime;

public class Evento {
    private int idEvento;
    private int idDireccion;
    private String tipoEvento;
    private String descripcion;
    private LocalDateTime fechaEvento;

    
    
    public Evento(int idDireccion, String tipoEvento, String descripcion) {
        this.idDireccion = idDireccion;
        this.tipoEvento = tipoEvento;
        this.descripcion = descripcion;
    }
    public int getIdEvento() {
        return idEvento;
    }
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
    public int getIdDireccion() {
        return idDireccion;
    }
    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
    public String getTipoEvento() {
        return tipoEvento;
    }
    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public LocalDateTime getFechaEvento() {
        return fechaEvento;
    }
    public void setFechaEvento(LocalDateTime fechaEvento) {
        this.fechaEvento = fechaEvento;
    }
    

    
}
