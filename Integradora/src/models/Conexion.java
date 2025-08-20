package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "TU_URL_DE_BASE_DE_DATOS"; // Por ejemplo: "jdbc:mysql://localhost:3306/Sgd_ip_mac"
    private static final String USER = "TU_USUARIO";
    private static final String PASSWORD = "TU_CONTRASEÑA";

    public static Connection conectar() throws SQLException {
        return  DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
