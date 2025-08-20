package models;

public class ConfiguracionIP {
    private int idConfig;
    private String rangoInicio;
    private String rangoFin;
    private String descripcion;
    private String redDestinada;
    
    
    
    public ConfiguracionIP(String rangoInicio, String rangoFin, String descripcion, String redDestinada) {
        this.rangoInicio = rangoInicio;
        this.rangoFin = rangoFin;
        this.descripcion = descripcion;
        this.redDestinada = redDestinada;
    }
    public int getIdConfig() {
        return idConfig;
    }
    public void setIdConfig(int idConfig) {
        this.idConfig = idConfig;
    }
    public String getRangoInicio() {
        return rangoInicio;
    }
    public void setRangoInicio(String rangoInicio) {
        this.rangoInicio = rangoInicio;
    }
    public String getRangoFin() {
        return rangoFin;
    }
    public void setRangoFin(String rangoFin) {
        this.rangoFin = rangoFin;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getRedDestinada() {
        return redDestinada;
    }
    public void setRedDestinada(String redDestinada) {
        this.redDestinada = redDestinada;
    }

}
