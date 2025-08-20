package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
public class ConfiguracionIPDAO {
     private Connection conn;
    public ConfiguracionIPDAO(Connection conn){
        this.conn = conn;
    }
    public int insertarConfiguracionIPDAO(ConfiguracionIP configuracionIP){
    int rows = 0;
    String sql = "INSERT INTO Configuracion_IP (rango_inicio, rango_fin, descripcion, red_destinada) VALUES (?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, configuracionIP.getRangoInicio());
        stmt.setString(2, configuracionIP.getRangoFin());
        stmt.setString(3, configuracionIP.getDescripcion());
        stmt.setString(4, configuracionIP.getRedDestinada());
        rows = stmt.executeUpdate(); 
    } catch (SQLException e) {
        System.out.println("Error al insertar ConfiguracionIP: " + e.getMessage());
    }
    return rows;
}
public ArrayList<ConfiguracionIP> obtenerConfiguracionIP(){
        ArrayList<ConfiguracionIP> listaConfiguracionIP= new ArrayList<>();
        String sql = "SELECT * FROM Configuracion_IP ORDER BY id_config";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            /* El ResultSet es el resultado del select,
               una matriz con el contenido de la tabla carreras */
            while (rs.next()) { // mientras haya renglones en el resultSet
                // Se recuperan los datos de cada renglón del rs
                String ri = rs.getString("rango_inicio");
                String rf = rs.getString("rango_fin");
                String des= rs.getString("descripcion");
                String ra= rs.getString("red_destinada");
                // Se crea un POJO con los datos de cada renglon del rs
                ConfiguracionIP unaCon = new ConfiguracionIP (ri,rf,des,ra);
                // Se agrega el nuevo objeto Carrera al arreglo
                listaConfiguracionIP.add(unaCon);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
         return listaConfiguracionIP;

}
}

 