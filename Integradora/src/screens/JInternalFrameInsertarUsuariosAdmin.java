package screens;

import java.awt.Font;
import java.sql.Connection;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import models.TokenGenerator;
import models.UsuariosAdmin;
import models.UsuariosAdminDAO;
import screens.EnviarCorreo;

public class JInternalFrameInsertarUsuariosAdmin extends JInternalFrame {
    private JLabel lblnombre;
    private JLabel lblapePT;
    private JLabel lblapeMT;
    private JLabel lblcorreo;
    private JLabel lblcontrasena;
    private JLabel lblrol;
    private JTextField txtnombre;
    private JTextField txtapePT;
    private JTextField txtapeMT;
    private JTextField txtcorreo;
    private JTextField txtcontrasena;
    private JTextField txtrol;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private Connection conn;

    public JInternalFrameInsertarUsuariosAdmin(Connection conn) {
        super("Insertar Usuario",
                true, // resizable
                true, // closable
                true, // maximizable
                true); // iconifiable (minimizable)
        this.conn = conn;
        this.setTitle("Insertar nuevo Usuario");
        this.setSize(400, 400);
        initComponents();
    }

    private boolean esCorreoValido(String correo) {
        String patron = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return correo.matches(patron);
    }

    private void initComponents() {
        // Creación de objetos

        lblnombre = new JLabel("Nombre del Usuario:");
        lblapePT = new JLabel("Apellido Paterno:");
        lblapeMT = new JLabel("Apellido Materno:");
        lblcorreo = new JLabel("Correo:");
        lblcontrasena = new JLabel("Contraseña:");
        lblrol = new JLabel("Rol:");
        txtnombre = new JTextField();
        txtapePT = new JTextField();
        txtapeMT = new JTextField();
        txtcorreo = new JTextField();
        txtcontrasena = new JTextField();
        txtrol = new JTextField();
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        // Etiquetas

        lblnombre.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblapePT.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblapeMT.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblcorreo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblcontrasena.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblrol.setFont(new Font("Tahoma", Font.BOLD, 16));
        // Botones
        btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));

        btnAceptar.addActionListener(e -> insertarUsuarioConVerificacion(conn));
        btnCancelar.addActionListener(e -> this.dispose());

        GroupLayout layout = new GroupLayout(getContentPane());
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblnombre)
                        .addComponent(txtnombre)
                        .addComponent(lblapePT)
                        .addComponent(txtapePT)
                        .addComponent(lblapeMT)
                        .addComponent(txtapeMT)
                        .addComponent(lblcorreo)
                        .addComponent(txtcorreo)
                        .addComponent(lblcontrasena)
                        .addComponent(txtcontrasena)
                        .addComponent(lblrol)
                        .addComponent(txtrol)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(btnAceptar)
                                        .addComponent(btnCancelar)));

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(lblnombre)
                        .addComponent(txtnombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblapePT)
                        .addComponent(txtapePT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblapeMT)
                        .addComponent(txtapeMT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblcorreo)
                        .addComponent(txtcorreo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblcontrasena)
                        .addComponent(txtcontrasena, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblrol)
                        .addComponent(txtrol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAceptar)
                                        .addComponent(btnCancelar)));
    }

  private void insertarUsuarioConVerificacion(Connection conn) {
    int rows;

    String Nombre = txtnombre.getText();
    String ApellidoPaterno = txtapePT.getText();
    String ApellidoMaterno = txtapeMT.getText();
    String Correo = txtcorreo.getText();
    String Contrasena = txtcontrasena.getText();
    String Rol = txtrol.getText();

    if (Nombre.isEmpty() || ApellidoPaterno.isEmpty() || ApellidoMaterno.isEmpty() ||
        Correo.isEmpty() || Contrasena.isEmpty() || Rol.isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Por favor, complete todos los campos correctamente",
            "Error",
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!esCorreoValido(Correo)) {
        JOptionPane.showMessageDialog(this,
            "El formato del correo no es válido",
            "Correo inválido",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    UsuariosAdminDAO UsuariosDAO = new UsuariosAdminDAO(this.conn);
    if (UsuariosDAO.correoExiste(Correo)) {
        JOptionPane.showMessageDialog(this,
            "El correo ya está registrado",
            "Correo duplicado",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    String tokenVerificacion = TokenGenerator.generarToken();
    boolean verificado = false;

    UsuariosAdmin Usuario = new UsuariosAdmin(Nombre, ApellidoPaterno, ApellidoMaterno, Correo, Contrasena, Rol);
    Usuario.setTokenVerificacion(tokenVerificacion);
    Usuario.setVerificado(verificado);

    rows = UsuariosDAO.insertarUsuariosAdmin(Usuario);

   rows = UsuariosDAO.insertarUsuariosAdmin(Usuario);

if (rows > 0) {
    boolean enviado = EnviarCorreo.enviarCorreo(
        Usuario.getCorreo(),
        "Verifica tu cuenta",
        "Hola " + Usuario.getNombre() + ",\n\n"
        + "Gracias por registrarte.\n"
        +"Para activar tu cuenta, haz clic en el siguiente enlace:\n\n"
        + "http://192.168.1.84:8080/verificar?correo=" + Usuario.getCorreo() + "&token=" + Usuario.getTokenVerificacion() + "\n\n"
        + "Si no solicitaste esta cuenta, puedes ignorar este mensaje."
    );

    if (enviado) {
        JOptionPane.showMessageDialog(this,
            "Usuario insertado correctamente.\nSe ha enviado un correo de verificación a " + Usuario.getCorreo(),
            "Éxito",
            JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this,
            "Usuario insertado correctamente, pero no se pudo enviar el correo de verificación.",
            "Advertencia",
            JOptionPane.WARNING_MESSAGE);
    }

    // Limpiar campos
    txtnombre.setText("");
    txtapePT.setText("");
    txtapeMT.setText("");
    txtcorreo.setText("");
    txtcontrasena.setText("");
    txtrol.setText("");
    this.dispose();

} else {
    JOptionPane.showMessageDialog(this,
        "Error al insertar Usuario.",
        "Error",
        JOptionPane.ERROR_MESSAGE);
}
}
}