package models.red;

import java.util.List;
import java.util.concurrent.*;

import models.DireccionamientoDAO;
import models.DispositivoDAO;
import models.EventoDAO;
import models.NotificacionDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class MonitorARP {
    private final DireccionamientoDAO direccionamientoDAO;
    private final DispositivoDAO dispositivoDAO;
    private final EventoDAO eventosDAO;
    private final NotificacionDAO notiDAO;
    private final Connection conn;
    private final ScheduledExecutorService scheduler;

    public MonitorARP(Connection conn) {
    this.conn = conn;
    this.scheduler = Executors.newScheduledThreadPool(1);
    this.direccionamientoDAO = new DireccionamientoDAO(conn);
    this.dispositivoDAO = new DispositivoDAO(conn);
    this.eventosDAO = new EventoDAO(conn);
    this.notiDAO = new NotificacionDAO(conn);
}

    public void iniciar() {
        scheduler.scheduleAtFixedRate(() -> {
            ARPScanner scanner = new ARPScanner();
            List<ARPScanner.DispositivoDetectado> dispositivos = scanner.escanear();

            for (ARPScanner.DispositivoDetectado d : dispositivos) {
                verificarDispositivo(d);
            }
        }, 0, 5, TimeUnit.MINUTES); // cada 5 minutos
    }

   private void verificarDispositivo(ARPScanner.DispositivoDetectado d) {
    try {
        boolean registrada = direccionamientoDAO.estaRegistrada(d.getIp(), d.getMac());
        boolean activa = direccionamientoDAO.estaActiva(d.getIp(), d.getMac());

        if (!registrada || !activa) {
            int idDireccion = direccionamientoDAO.obtenerIdDireccion(d.getIp(), d.getMac());
            eventosDAO.registrarEvento(idDireccion, "Acceso no autorizado", "MAC no registrada o inactiva");
            int idEvento = eventosDAO.obtenerUltimoId();
            notiDAO.enviarNotificacion(idEvento, "TU_CORREO_DE_DESTINATARIO", "sistema");
            System.out.println("Anomalía detectada: " + d);
        }
    } catch (SQLException e) {
        System.err.println("Error SQL al verificar dispositivo: " + e.getMessage());
    }
   }
}
