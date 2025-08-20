package models;

import java.time.LocalDateTime;

public class HistorialConexion {
    private int idHistorial;
    private int idDispositivo;
    private String ipUtilizada;
    private String macUtilizada;
    private LocalDateTime fechaConexion;
    
    
    
    public HistorialConexion(int idDispositivo, String ipUtilizada, String macUtilizada) {
        this.idDispositivo = idDispositivo;
        this.ipUtilizada = ipUtilizada;
        this.macUtilizada = macUtilizada;
    }
    public int getIdHistorial() {
        return idHistorial;
    }
    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }
    public int getIdDispositivo() {
        return idDispositivo;
    }
    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }
    public String getIpUtilizada() {
        return ipUtilizada;
    }
    public void setIpUtilizada(String ipUtilizada) {
        this.ipUtilizada = ipUtilizada;
    }
    public String getMacUtilizada() {
        return macUtilizada;
    }
    public void setMacUtilizada(String macUtilizada) {
        this.macUtilizada = macUtilizada;
    }
    public LocalDateTime getFechaConexion() {
        return fechaConexion;
    }
    public void setFechaConexion(LocalDateTime fechaConexion) {
        this.fechaConexion = fechaConexion;
    }
    
    
}
