package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.ArrayList;

public class DispositivoDAO {
    private Connection conn;
    public DispositivoDAO(Connection conn){
        this.conn = conn;
    }
    public int insertarDispositivoDAO(Dispositivo dispositivo) {
    int rows = 0;
    String sql = "INSERT INTO Dispositivos (nombre_dispositivo, tipo_dispositivo, usuario_asignado) VALUES (?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, dispositivo.getNombreDispositivo());
        stmt.setString(2, dispositivo.getTipoDispositivo());
        stmt.setString(3, dispositivo.getUsuarioAsignado());

        rows = stmt.executeUpdate(); 
    } catch (SQLException e) {
        System.out.println("Error al insertar dispositivo: " + e.getMessage());
    }
    return rows;
}
public ArrayList<Dispositivo> obtenerDispositivos(){
        ArrayList<Dispositivo> listaDipositivos= new ArrayList<>();
        String sql = "SELECT * FROM Dispositivos ORDER BY id_dispositivo";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            /* El ResultSet es el resultado del select,
               una matriz con el contenido de la tabla carreras */
            while (rs.next()) { // mientras haya renglones en el resultSet
                // Se recuperan los datos de cada renglón del rs
                String nomDis = rs.getString("nombre_dispositivo");
                String tdis = rs.getString("tipo_dispositivo");
                String uasig = rs.getString("usuario_asignado");
                // Se crea un POJO con los datos de cada renglon del rs
                Dispositivo unDis = new Dispositivo(nomDis,tdis,uasig);
                // Se agrega el nuevo objeto Carrera al arreglo
                listaDipositivos.add(unDis);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
         return listaDipositivos;

}

}
