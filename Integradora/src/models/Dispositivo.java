package models;

import java.time.LocalDateTime;

public class Dispositivo {
    private int idDispositivo;
    private String nombreDispositivo;
    private String tipoDispositivo;
    private String usuarioAsignado;
    private String estado;
    private LocalDateTime fechaRegistro;
    
    
    
    public Dispositivo( String nombreDispositivo, String tipoDispositivo, String usuarioAsignado) {
        this.nombreDispositivo = nombreDispositivo;
        this.tipoDispositivo = tipoDispositivo;
        this.usuarioAsignado = usuarioAsignado;
    }
    public int getIdDispositivo() {
        return idDispositivo;
    }
    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }
    public String getNombreDispositivo() {
        return nombreDispositivo;
    }
    public void setNombreDispositivo(String nombreDispositivo) {
        this.nombreDispositivo = nombreDispositivo;
    }
    public String getTipoDispositivo() {
        return tipoDispositivo;
    }
    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }
    public String getUsuarioAsignado() {
        return usuarioAsignado;
    }
    public void setUsuarioAsignado(String usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
}
