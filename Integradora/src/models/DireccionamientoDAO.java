package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

public class DireccionamientoDAO {
    private Connection conn;

    public DireccionamientoDAO(Connection conn) {
        this.conn = conn;
    }

    public int insertarDireccionamientoDAO(Direccionamiento direccionamiento) {
        int rows = 0;
        String sql = "INSERT INTO Direccionamiento (id_dispositivo, direccion_ip, direccion_mac) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, direccionamiento.getIdDispositivo());
            stmt.setString(2, direccionamiento.getDireccionIp());
            stmt.setString(3, direccionamiento.getDireccionMac());

            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar Direccionamiento: " + e.getMessage());
        }
        return rows;
    }

    public ArrayList<Direccionamiento> obtenerDireccionamientos() {
        ArrayList<Direccionamiento> listaDireccionamientos = new ArrayList<>();
        String sql = "SELECT * FROM Direccionamiento ORDER BY id_direccion";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            /*
             * El ResultSet es el resultado del select,
             * una matriz con el contenido de la tabla carreras
             */
            while (rs.next()) { // mientras haya renglones en el resultSet
                // Se recuperan los datos de cada renglón del rs
                int id_dis = rs.getInt("id_dispositivo");
                String dip = rs.getString("direccion_ip");
                String dmac = rs.getString("direccion_mac");
                // Se crea un POJO con los datos de cada renglon del rs
                Direccionamiento unaDire = new Direccionamiento(id_dis, dip, dmac);
                // Se agrega el nuevo objeto Carrera al arreglo
                listaDireccionamientos.add(unaDire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDireccionamientos;
    }

    public boolean estaRegistrada(String ip, String mac) throws SQLException {
        String sql = "SELECT COUNT(*) FROM direccionamiento WHERE ip = ? AND mac = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, ip);
        stmt.setString(2, mac);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt(1) > 0;
    }

    public boolean estaActiva(String ip, String mac) throws SQLException {
        String sql = "SELECT activa FROM direccionamiento WHERE ip = ? AND mac = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, ip);
        stmt.setString(2, mac);
        ResultSet rs = stmt.executeQuery();
        return rs.next() && rs.getBoolean("activa");
    }

    public int obtenerIdDireccion(String ip, String mac) throws SQLException {
        String sql = "SELECT id_direccion FROM direccionamiento WHERE ip = ? AND mac = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, ip);
        stmt.setString(2, mac);
        ResultSet rs = stmt.executeQuery();
        return rs.next() ? rs.getInt("id_direccion") : -1;
    }
}
