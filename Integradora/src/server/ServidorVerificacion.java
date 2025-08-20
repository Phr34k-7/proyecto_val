package server;
import fi.iki.elonen.NanoHTTPD;
import models.UsuariosAdmin;
import models.UsuariosAdminDAO;

import java.sql.Connection;
import java.util.Map;

public class ServidorVerificacion extends NanoHTTPD {

    private Connection conn;

    public ServidorVerificacion(int port, Connection conn) {
        super(port);
        this.conn = conn;
    }

   @Override
public Response serve(IHTTPSession session) {
    String uri = session.getUri();
    if (!uri.equals("/verificar")) {
        return newFixedLengthResponse("<html><body><h2>Ruta no válida</h2></body></html>");
    }

    Map<String, String> params = session.getParms();
    String correo = params.get("correo");
    String token = params.get("token");

    if (correo == null || token == null) {
        return newFixedLengthResponse("<html><body><h2> Parámetros faltantes.</h2></body></html>");
    }

    UsuariosAdminDAO dao = new UsuariosAdminDAO(conn);
    UsuariosAdmin usuario = dao.obtenerUsuarioPorCorreo(correo);

    if (usuario != null && token.equals(usuario.getTokenVerificacion())) {
        dao.actualizarVerificado(correo, true);
        return newFixedLengthResponse("<html><body><h2>¡Cuenta verificada!</h2></body></html>");
    } else {
        return newFixedLengthResponse("<html><body><h2> Token inválido o usuario no encontrado.</h2></body></html>");
    }
}

    
}
