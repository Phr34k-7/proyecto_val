import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import models.Conexion;
import models.red.MonitorARP;
import screens.Inicioseccion.JFrameInicioSección;
import server.ServidorVerificacion;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println("Error al aplicar FlatLaf: " + e.getMessage());
        }

        try {
            Connection conn = Conexion.conectar();
            System.out.println("Conexión exitosa a la BD");
            // 🔄 Iniciar escaneo ARP automático
            MonitorARP monitor = new MonitorARP(conn);
            monitor.iniciar();

            // 🔌 Iniciar servidor de verificación
            try {
                ServidorVerificacion servidor = new ServidorVerificacion(8080, conn);
                servidor.start();
                System.out.println("Servidor de verificación iniciado en http://localhost:8080");
            } catch (IOException e) {
                System.out.println("Error al iniciar servidor de verificación: " + e.getMessage());
            }

            // 🧑‍💻 Mostrar login
            JFrameInicioSección login = new JFrameInicioSección(conn);
            login.setVisible(true);
        } catch (SQLException e) {
            System.out.println("Error en conexión a la BD: " + e.getMessage());
        }
    }
}