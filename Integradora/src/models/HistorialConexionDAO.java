package models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HistorialConexionDAO {
//Notificacionesdao contiene todos los metodos parab interactuar con la tabla carrera como Insernt, Select,uddate, dalet
    private Connection conn;
    public HistorialConexionDAO(Connection conn){
        this.conn = conn;
    }
    public int insertarHistorialConexionDAO(HistorialConexion historial){
    int rows = 0;
    String sql = "INSERT INTO Historial_Conexiones (id_dispositivo, ip_utilizada, mac_utilizada) VALUES (?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, historial.getIdDispositivo());
        stmt.setString(2, historial.getIpUtilizada());
        stmt.setString(3, historial.getMacUtilizada());

        rows = stmt.executeUpdate(); 
    } catch (SQLException e) {
        System.out.println("Error al insertar Historial de Conexión: " + e.getMessage());
    }
    return rows;
}
public ArrayList<HistorialConexion> obtenerHistorialConexions(){
        ArrayList<HistorialConexion> listaHistorialCo= new ArrayList<>();
        String sql = "SELECT * FROM Historial_Conexiones ORDER BY id_historial";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            /* El ResultSet es el resultado del select,
               una matriz con el contenido de la tabla carreras */
            while (rs.next()) { // mientras haya renglones en el resultSet
                // Se recuperan los datos de cada renglón del rs
                int id_dispositivo = rs.getInt("id_dispositivo");
                String ip = rs.getString("ip_utilizada");
                String mac= rs.getString("mac_utilizada");
                // Se crea un POJO con los datos de cada renglon del rs
                HistorialConexion unCon = new HistorialConexion(id_dispositivo,ip,mac);
                // Se agrega el nuevo objeto Carrera al arreglo
                listaHistorialCo.add(unCon);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        // Se regresa un arreglo con objetos Carrera
        return listaHistorialCo;
    }
}
