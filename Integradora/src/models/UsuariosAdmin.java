package models;

public class UsuariosAdmin {
    private int idAdmin;
    private String nombre;
    private String apePt;
    private String apeMt;
    private String correo;
    private String contrasena;
    private String rol;
    private String tokenVerificacion;
    private boolean verificado;
    private String Estado;
    // Constructor vacío
    public UsuariosAdmin() {
    }
    

    public UsuariosAdmin(String nombre, String apePt,String correo, String rol) {
        this.nombre = nombre;
        this.apePt = apePt;
        this.correo = correo;
        this.rol = rol;
    }


    // Constructor completo
    public UsuariosAdmin( String nombre, String apePt, String apeMt, String correo,
                         String contrasena, String rol) {
        
        this.nombre = nombre;
        this.apePt = apePt;
        this.apeMt = apeMt;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
      
    }

    // Getters y Setters
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePt() {
        return apePt;
    }

    public void setApePt(String apePt) {
        this.apePt = apePt;
    }

    public String getApeMt() {
        return apeMt;
    }

    public void setApeMt(String apeMt) {
        this.apeMt = apeMt;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTokenVerificacion() {
        return tokenVerificacion;
    }

    public void setTokenVerificacion(String tokenVerificacion) {
        this.tokenVerificacion = tokenVerificacion;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    
    
}
