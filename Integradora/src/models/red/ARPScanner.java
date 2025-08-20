package models.red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ARPScanner {

    public static class DispositivoDetectado {
        // ... (código que ya tenías)
        private final String ip;
        private final String mac;

        public DispositivoDetectado(String ip, String mac) {
            this.ip = ip;
            this.mac = mac;
        }

        public String getIp() { return ip; }
        public String getMac() { return mac; }

        @Override
        public String toString() {
            return "IP: " + ip + " | MAC: " + mac;
        }
    }

    // --- Metodos de la base de datos (sin cambios) ---
    
    /**
     * Conecta a la base de datos y obtiene todas las IPs autorizadas.
     */
    public Set<String> obtenerIpsDeBD() {
        Set<String> ips = new HashSet<>();
        String url = "TU_URL_DE_BASE_DE_DATOS"; // Reemplaza con tu URL
        String usuario = "TU_USUARIO"; // Reemplaza con tu usuario
        String contraseña = "TU_CONTRASEÑA"; // Reemplaza con tu contraseña
        String sql = "SELECT `Direccion IP` FROM direccionamiento";
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ips.add(rs.getString("Direccion IP"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las IPs de la base de datos: " + e.getMessage());
        }
        return ips;
    }
    
    public void agregarDispositivoAutorizado(String ip, String mac) {
        String url = "TU_URL_DE_BASE_DE_DATOS"; // Reemplaza con tu URL
        String usuario = "TU_USUARIO"; // Reemplaza con tu usuario
        String contraseña = "TU_CONTRASEÑA"; // Reemplaza con tu contraseña
        String sql = "INSERT INTO direccionamiento (`Direccion IP`, `Direccion MAC`) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ip);
            stmt.setString(2, mac);
            stmt.executeUpdate();
            System.out.println("Dispositivo " + ip + " autorizado y agregado a la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al agregar el dispositivo autorizado: " + e.getMessage());
        }
    }

    /**
     * Escanea la red y devuelve una lista de dispositivos.
     * Ya no valida ni reporta errores, solo recopila los datos.
     */
    public List<DispositivoDetectado> escanear() {
        List<DispositivoDetectado> lista = new ArrayList<>();
        try {
            Process proceso = Runtime.getRuntime().exec("arp -a");
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;

            while ((linea = reader.readLine()) != null) {
                if (linea.contains("dynamic") || linea.contains("dinámico")) {
                    String[] partes = linea.trim().split("\\s+");
                    if (partes.length >= 2 && esIPValida(partes[0]) && esMACValida(partes[1])) {
                        String ip = partes[0];
                        String mac = partes[1].replace("-", ":").toLowerCase();
                        lista.add(new DispositivoDetectado(ip, mac));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al ejecutar el escaneo ARP: " + e.getMessage());
        }
        return lista;
    }
    
    // --- Métodos de validación (sin cambios) ---

    private boolean esIPValida(String ip) {
        return ip.matches("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");
    }

    private boolean esMACValida(String mac) {
        return mac.matches("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");
    }
}
