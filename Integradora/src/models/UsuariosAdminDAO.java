package models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.UsuariosAdmin;
import java.sql.Statement;
public class UsuariosAdminDAO {
    
    private Connection conn;

    // Constructor que recibe la conexión
    public UsuariosAdminDAO(Connection conn) {
        this.conn = conn;
    }

    //  Insertar usuario
    public int insertarUsuariosAdmin(UsuariosAdmin usuario) {
        String sql = "INSERT INTO Usuarios_Admin (nombre, ape_pt, ape_mt, correo, contrasena, rol, token_verificacion, verificado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApePt());
            stmt.setString(3, usuario.getApeMt());
            stmt.setString(4, usuario.getCorreo());
            stmt.setString(5, usuario.getContrasena());
            stmt.setString(6, usuario.getRol());
            stmt.setString(7, usuario.getTokenVerificacion());
            stmt.setBoolean(8, usuario.isVerificado());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
       public ArrayList<UsuariosAdmin> obtenerUsuariosAdmin(){
        ArrayList<UsuariosAdmin> listaUsuariosAdmin = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios_Admin ORDER BY id_admin";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            /* El ResultSet es el resultado del select,
               una matriz con el contenido de la tabla carreras */
            while (rs.next()) { // mientras haya renglones en el resultSet
                // Se recuperan los datos de cada renglón del rs
                String nombre = rs.getString("nombre");
                String apePt = rs.getString("ape_pt");
                String correo= rs.getString("correo");
                String rol= rs.getString("rol");
                // Se crea un POJO con los datos de cada renglon del rs
                UsuariosAdmin unUsa = new UsuariosAdmin(nombre,apePt,correo,rol);
                // Se agrega el nuevo objeto Carrera al arreglo
                listaUsuariosAdmin.add(unUsa);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        // Se regresa un arreglo con objetos Carrera
        return listaUsuariosAdmin;
    }

       // Verificar si un correo ya existe
    public boolean correoExiste(String correo) {
        String sql = "SELECT COUNT(*) FROM Usuarios_Admin WHERE correo = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar correo: " + e.getMessage());
        }

        return false;
    }

    // 🔐 Validar credenciales
    public UsuariosAdmin obtenerUsuarioPorCredenciales(String correo, String contrasena) {
        String sql = "SELECT * FROM Usuarios_Admin WHERE correo = ? AND contrasena = ? AND verificado = true";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.setString(2, contrasena);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UsuariosAdmin usuario = new UsuariosAdmin();
                    usuario.setIdAdmin(rs.getInt("id_admin"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApePt(rs.getString("ape_pt"));
                    usuario.setApeMt(rs.getString("ape_mt"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setContrasena(rs.getString("contrasena"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setTokenVerificacion(rs.getString("token_verificacion"));
                    usuario.setVerificado(rs.getBoolean("verificado"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuario: " + e.getMessage());
        }

        return null;
    }
   public UsuariosAdmin obtenerUsuarioPorCorreo(String correo) {
    String sql = "SELECT * FROM Usuarios_Admin WHERE correo = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, correo);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                UsuariosAdmin usuario = new UsuariosAdmin();
                usuario.setIdAdmin(rs.getInt("id_admin"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApePt(rs.getString("ape_pt"));
                usuario.setApeMt(rs.getString("ape_mt"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
                usuario.setTokenVerificacion(rs.getString("token_verificacion"));
                usuario.setVerificado(rs.getBoolean("verificado"));
                return usuario;
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener usuario por correo: " + e.getMessage());
    }
    return null;
}
// ✅ Actualizar solo el estado de verificación por correo
public boolean actualizarVerificado(String correo, boolean estado) {
    String sql = "UPDATE Usuarios_Admin SET verificado = ? WHERE correo = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setBoolean(1, estado);
        stmt.setString(2, correo);
        int rows = stmt.executeUpdate();
        return rows > 0;
    } catch (SQLException e) {
        System.out.println("Error al actualizar verificación: " + e.getMessage());
        return false;
    }
}
}
