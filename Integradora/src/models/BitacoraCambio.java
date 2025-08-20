package models;

import java.time.LocalDateTime;

public class BitacoraCambio {
    private int idCambio;
    private int idAdmin;
    private String tablaAfectada;
    private String descripcion;
    private LocalDateTime fechaCambio;
    
    
    public BitacoraCambio(int idAdmin, String tablaAfectada, String descripcion) {
        this.idAdmin = idAdmin;
        this.tablaAfectada = tablaAfectada;
        this.descripcion = descripcion;
    }
    public int getIdCambio() {
        return idCambio;
    }
    public void setIdCambio(int idCambio) {
        this.idCambio = idCambio;
    }
    public int getIdAdmin() {
        return idAdmin;
    }
    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
    public String getTablaAfectada() {
        return tablaAfectada;
    }
    public void setTablaAfectada(String tablaAfectada) {
        this.tablaAfectada = tablaAfectada;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public LocalDateTime getFechaCambio() {
        return fechaCambio;
    }
    public void setFechaCambio(LocalDateTime fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    
}
