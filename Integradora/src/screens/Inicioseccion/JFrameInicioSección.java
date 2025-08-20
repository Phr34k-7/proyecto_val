package screens.Inicioseccion;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import screens.VentanaPrincipal;
import models.Conexion;
import models.UsuariosAdmin;
import models.UsuariosAdminDAO;

public class JFrameInicioSección extends JFrame {
    private Diseño panelLogin;

    public JFrameInicioSección() {
        setTitle("Inicio de Sesión");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setLayout(null);

        JLabel titulo = new JLabel("Bienvenido a JAY ", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setBounds(0, 10, 400, 30);
        add(titulo);

        panelLogin = new Diseño();
        panelLogin.setBounds(40, 60, 320, 240); 
        add(panelLogin);

        // Acción del botón
        panelLogin.getLoginButton().addActionListener(e -> validarLogin());

        setVisible(true);
    }

    public JFrameInicioSección(Connection conn) {
        this(); // Puedes guardar la conexión si lo deseas
    }

    private void validarLogin() {
       if (!panelLogin.getChkTerminos().isSelected()) {
        JOptionPane.showMessageDialog(this, "Debes aceptar los términos y condiciones para continuar.");
        return;
    }

    String correo = panelLogin.getUserField().getText().trim();
    String contraseña = new String(panelLogin.getPassField().getPassword()).trim();

    if (correo.isEmpty() || contraseña.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
        return;
    }


        try {
            Connection conn = Conexion.conectar();
            UsuariosAdminDAO dao = new UsuariosAdminDAO(conn);
            UsuariosAdmin usuario = dao.obtenerUsuarioPorCredenciales(correo, contraseña);

            if (usuario != null) {
                if ("bloqueado".equalsIgnoreCase(usuario.getEstado())) {
                    JOptionPane.showMessageDialog(this, "Tu cuenta está bloqueada.");
                } else {
                    JOptionPane.showMessageDialog(this, "Bienvenido " + usuario.getNombre() + " (" + usuario.getRol() + ")");
                    VentanaPrincipal vp = new VentanaPrincipal(usuario, conn);
                    vp.setExtendedState(Frame.MAXIMIZED_BOTH);
                    vp.setVisible(true);
                    dispose();
                }
                
            } else {
                UsuariosAdmin sinVerificar = dao.obtenerUsuarioPorCorreo(correo);
                if (sinVerificar != null && !sinVerificar.isVerificado()) {
                    JOptionPane.showMessageDialog(this, "Tu correo aún no ha sido verificado. Revisa tu bandeja de entrada.");
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JFrameInicioSección());
    }
}









