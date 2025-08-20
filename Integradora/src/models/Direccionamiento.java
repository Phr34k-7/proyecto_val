package models;

import java.time.LocalDateTime;

public class Direccionamiento {
    private int idDireccion;
    private int idDispositivo;
    private String direccionIp;
    private String direccionMac;
    private LocalDateTime fechaAsignacion;
    private boolean activo;
    
    
    public Direccionamiento(int idDispositivo, String direccionIp, String direccionMac) {
        this.idDispositivo = idDispositivo;
        this.direccionIp = direccionIp;
        this.direccionMac = direccionMac;
    }
    public int getIdDireccion() {
        return idDireccion;
    }
    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
    public int getIdDispositivo() {
        return idDispositivo;
    }
    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }
    public String getDireccionIp() {
        return direccionIp;
    }
    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }
    public String getDireccionMac() {
        return direccionMac;
    }
    public void setDireccionMac(String direccionMac) {
        this.direccionMac = direccionMac;
    }
    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }
    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
