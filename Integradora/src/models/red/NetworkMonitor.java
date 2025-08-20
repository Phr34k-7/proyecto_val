import java.util.List;
import java.util.Scanner;
import java.util.Set;
import models.red.ARPScanner;
import models.red.ARPScanner.DispositivoDetectado;

public class NetworkMonitor {

    public static void main(String[] args) {
        System.out.println("Iniciando monitor de red... Presiona Ctrl+C para detener.");
        ARPScanner scanner = new ARPScanner();
        Scanner input = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n Escaneando la red...");
            
            // 1. Obtiene las IPs autorizadas de la BD
            Set<String> ipsAutorizadas = scanner.obtenerIpsDeBD();
            
            // 2. Escanea la red para obtener los dispositivos actuales
            List<DispositivoDetectado> dispositivosDetectados = scanner.escanear();
            
            boolean nuevoDispositivoEncontrado = false;

            // 3. Compara los dispositivos detectados con las IPs autorizadas
            for (DispositivoDetectado dispositivo : dispositivosDetectados) {
                if (!ipsAutorizadas.contains(dispositivo.getIp())) {
                    nuevoDispositivoEncontrado = true;
                    System.out.println("\n ¡ALERTA! Nuevo dispositivo no autorizado conectado:");
                    System.out.println("  " + dispositivo);

                    // 4. Pregunta al usuario si quiere dar permiso
                    System.out.print("¿Deseas dar permiso a este dispositivo? (s/n): ");
                    String respuesta = input.nextLine().trim().toLowerCase();
                    
                    if (respuesta.equals("s")) {
                        scanner.agregarDispositivoAutorizado(dispositivo.getIp(), dispositivo.getMac());
                    } else {
                        System.out.println("Dispositivo no autorizado, manteniendose en lista de no permitidos.");
                    }
                }
            }
            
            if (!nuevoDispositivoEncontrado) {
                System.out.println("No se encontraron nuevos dispositivos.");
            }
            
            // Espera antes de volver a escanear (por ejemplo, 30 segundos)
            try {
                Thread.sleep(30000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
