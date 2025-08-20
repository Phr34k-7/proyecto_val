package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EventoDAO {
    private Connection conn;
    public EventoDAO(Connection conn){
        this.conn = conn;
    }
    public int insertarEventoDAO(Evento evento) {
    int rows = 0;
    String sql = "INSERT INTO Eventos (id_direccion, tipo_evento, descripcion) VALUES (?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, evento.getIdDireccion());
        stmt.setString(2, evento.getTipoEvento());
        stmt.setString(3, evento.getDescripcion());
        rows = stmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al insertar Evento: " + e.getMessage());
    }
    return rows;
}

public ArrayList<Evento> obtenerEvento(){
        ArrayList<Evento> listaEventos= new ArrayList<>();
        String sql = "SELECT * FROM Eventos ORDER BY id_evento";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            /* El ResultSet es el resultado del select,
               una matriz con el contenido de la tabla carreras */
            while (rs.next()) { // mientras haya renglones en el resultSet
                // Se recuperan los datos de cada renglón del rs
                int id_dire = rs.getInt("id_direccion");
                String tipoE = rs.getString("tipo_evento");
                String des= rs.getString("descripcion");
                // Se crea un POJO con los datos de cada renglon del rs
                Evento unCon = new Evento(id_dire,tipoE,des);
                // Se agrega el nuevo objeto Carrera al arreglo
                listaEventos.add(unCon);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
         return listaEventos;

}
public void registrarEvento(int idDireccion, String tipo, String descripcion) throws SQLException {
    String sql = "INSERT INTO eventos (id_direccion, tipo_evento, descripcion, fecha_evento) VALUES (?, ?, ?, NOW())";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, idDireccion);
    stmt.setString(2, tipo);
    stmt.setString(3, descripcion);
    stmt.executeUpdate();
}

public int obtenerUltimoId() throws SQLException {
    String sql = "SELECT MAX(id_evento) FROM eventos";
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    return rs.next() ? rs.getInt(1) : -1;
}
}
