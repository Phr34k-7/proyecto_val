package models.red;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Connection;
import models.DireccionamientoDAO;
import models.DispositivoDAO;
import models.EventoDAO;
import models.NotificacionDAO;

public class VerificarDispositivo {
    private Connection conn;
    private void verificarDispositivo(ARPScanner.DispositivoDetectado d) {
    try {
        DireccionamientoDAO direccionamientoDAO = new DireccionamientoDAO(conn);
        DispositivoDAO dispositivoDAO = new DispositivoDAO(conn);
        EventoDAO eventosDAO = new EventoDAO(conn);
        NotificacionDAO notiDAO = new NotificacionDAO(conn);

        boolean registrada = direccionamientoDAO.estaRegistrada(d.getIp(), d.getMac());
        boolean activa = direccionamientoDAO.estaActiva(d.getIp(), d.getMac());

        if (!registrada || !activa) {
            int idDireccion = direccionamientoDAO.obtenerIdDireccion(d.getIp(), d.getMac());
            eventosDAO.registrarEvento(idDireccion, "Acceso no autorizado", "MAC no registrada o inactiva");
            int idEvento = eventosDAO.obtenerUltimoId();
            notiDAO.enviarNotificacion(idEvento, "TU_CORREO_DE_DESTINATARIO", "sistema");
            System.out.println(" Anomalía detectada: " + d);
        }
    } catch (SQLException e) {
        System.err.println("Error SQL al verificar dispositivo: " + e.getMessage());
    }
}
}
