package models;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BitacoraCambioDAO {
    private Connection conn;
    public BitacoraCambioDAO(Connection conn){
        this.conn = conn;
    }
    public int insertarBitacoraCambioDAO(BitacoraCambio bCambio) {
    int rows = 0;
    String sql = "INSERT INTO Bitacora_Cambios (id_admin, tabla_afectada, descripcion) VALUES (?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, bCambio.getIdAdmin());
        stmt.setString(2, bCambio.getTablaAfectada());
        stmt.setString(3, bCambio.getDescripcion());

        rows = stmt.executeUpdate(); 
    } catch (SQLException e) {
        System.out.println("Error al insertar Bitácora: " + e.getMessage());
    }
    return rows;
}
public ArrayList<BitacoraCambio> obtenerBitacoraCambio(){
        ArrayList<BitacoraCambio> listaBitacoraCambios= new ArrayList<>();
        String sql = "SELECT * FROM Bitacora_Cambios ORDER BY id_cambio";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            /* El ResultSet es el resultado del select,
               una matriz con el contenido de la tabla carreras */
            while (rs.next()) { // mientras haya renglones en el resultSet
                // Se recuperan los datos de cada renglón del rs
                int id_ad = rs.getInt("id_admin");
                String taf = rs.getString("tabla_afectada");
                String des= rs.getString("descripcion");
                // Se crea un POJO con los datos de cada renglon del rs
                BitacoraCambio unCom = new BitacoraCambio (id_ad,taf,des);
                // Se agrega el nuevo objeto Carrera al arreglo
                listaBitacoraCambios.add(unCom);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
         return listaBitacoraCambios;

} 
}
