package models;

import java.time.LocalDateTime;

public class Notificacion {
    private int idNotificacion;
    private int idEvento;
    private String enviadoA;
    private String metodoEnvio;
    private boolean estadoEnvio;
    private LocalDateTime fechaEnvio;

    
    public Notificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }
    
    
    public Notificacion(int idEvento, String enviadoA, String metodoEnvio, boolean estadoEnvio) {
        this.idEvento = idEvento;
        this.enviadoA = enviadoA;
        this.metodoEnvio = metodoEnvio;
        this.estadoEnvio = estadoEnvio;
    
    }
    
    public int getIdNotificacion() {
        return idNotificacion;
    }
    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }
    public int getIdEvento() {
        return idEvento;
    }
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
    public String getEnviadoA() {
        return enviadoA;
    }
    public void setEnviadoA(String enviadoA) {
        this.enviadoA = enviadoA;
    }
    public String getMetodoEnvio() {
        return metodoEnvio;
    }
    public void setMetodoEnvio(String metodoEnvio) {
        this.metodoEnvio = metodoEnvio;
    }
    public boolean isEstadoEnvio() {
        return estadoEnvio;
    }
    public void setEstadoEnvio(boolean estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }
    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }
    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
    


    
}
