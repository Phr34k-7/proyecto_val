package models;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class NotificacionDAO {
    // Notificacionesdao contiene todos los metodos parab interactuar con la tabla N
    // como Insernt, Select,uddate, dalet
    private Connection conn;

    public NotificacionDAO(Connection conn) {
        this.conn = conn;
    }

    public int insertarNotificaciones(Notificacion Notificacion) {
        int rows = 0;
        String sql = "INSERT INTO Notificaciones (id_evento, enviado_a, metodo_envio,estado_envio) VALUES (?,?,?,?);";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Notificacion.getIdEvento());
            stmt.setString(2, Notificacion.getEnviadoA());
            stmt.setString(3, Notificacion.getMetodoEnvio());
            stmt.setBoolean(4, Notificacion.isEstadoEnvio());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar Notificacion:" + e.getMessage());
        }
        return rows;

    }

    public ArrayList<Notificacion> obtenerNotificacion() {
        ArrayList<Notificacion> listaNotificaciones = new ArrayList<>();
        String sql = "SELECT * FROM Notificaciones ORDER BY id_notificacion";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            /*
             * El ResultSet es el resultado del select,
             * una matriz con el contenido de la tabla carreras
             */
            while (rs.next()) { // mientras haya renglones en el resultSet
                // Se recuperan los datos de cada renglón del rs
                int idEvento = rs.getInt("id_evento");
                String envidoA = rs.getString("enviado_a");
                String metodoE = rs.getString("metodo_envio");
                boolean estadoEnvio = rs.getBoolean("estado_envio");

                // Se crea un POJO con los datos de cada renglon del rs
                Notificacion noti = new Notificacion(idEvento, envidoA, metodoE, estadoEnvio);
                // Se agrega el nuevo objeto Carrera al arreglo
                listaNotificaciones.add(noti);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Se regresa un arreglo con objetos Carrera
        return listaNotificaciones;
    }

    public void enviarNotificacion(int idEvento, String destino, String metodo) throws SQLException {
        String sql = "INSERT INTO notificaciones (id_evento, enviado_a, metodo_envio, estado_envio, fecha_envio) VALUES (?, ?, ?, 'pendiente', NOW())";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idEvento);
        stmt.setString(2, destino);
        stmt.setString(3, metodo);
        stmt.executeUpdate();
    }

}
