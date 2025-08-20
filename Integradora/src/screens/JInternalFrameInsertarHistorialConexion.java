package screens;

import java.awt.Font;
import java.sql.Connection;
import javax.swing.*;

import models.HistorialConexion;
import models.HistorialConexionDAO;

public class JInternalFrameInsertarHistorialConexion extends JInternalFrame {
    private JLabel lblIdDispositivo;
    private JLabel lblIpUtilizada;
    private JLabel lblMacUtilizada;

    private JTextField txtIdDispositivo;
    private JTextField txtIpUtilizada;
    private JTextField txtMacUtilizada;
    private JDesktopPane desktop;
    private String rol;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private Connection conn;

    

    public JInternalFrameInsertarHistorialConexion(JDesktopPane desktop, String rol) {
        this.desktop = desktop;
        this.rol = rol;
    }

    public JInternalFrameInsertarHistorialConexion(Connection conn){
        super("Insertar Historial de Conexión", 
        true, 
        true, 
        true, 
        true);
        this.conn = conn;
        this.setSize(400, 300);
        initComponents();
    }

    private void initComponents(){
        lblIdDispositivo = new JLabel("ID Dispositivo:");
        lblIpUtilizada = new JLabel("IP Utilizada:");
        lblMacUtilizada = new JLabel("MAC Utilizada:");

        txtIdDispositivo = new JTextField();
        txtIpUtilizada = new JTextField();
        txtMacUtilizada = new JTextField();

        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        Font font = new Font("Tahoma", Font.BOLD, 16);
        lblIdDispositivo.setFont(font);
        lblIpUtilizada.setFont(font);
        lblMacUtilizada.setFont(font);
        btnAceptar.setFont(font);
        btnCancelar.setFont(font);

        btnAceptar.addActionListener(e -> insertarHistorial());
        btnCancelar.addActionListener(e -> this.dispose());

        GroupLayout layout = new GroupLayout(getContentPane());
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblIdDispositivo)
                .addComponent(txtIdDispositivo)
                .addComponent(lblIpUtilizada)
                .addComponent(txtIpUtilizada)
                .addComponent(lblMacUtilizada)
                .addComponent(txtMacUtilizada)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblIdDispositivo)
                .addComponent(txtIdDispositivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblIpUtilizada)
                .addComponent(txtIpUtilizada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblMacUtilizada)
                .addComponent(txtMacUtilizada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
        );
    }

    private void insertarHistorial(){
        
            int idDispositivo = Integer.parseInt(txtIdDispositivo.getText().trim());
            String ip = txtIpUtilizada.getText().trim();
            String mac = txtMacUtilizada.getText().trim();

            if (idDispositivo <= 0 || ip.isEmpty() || mac.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Todos los campos son obligatorios",
                    "Error de entrada",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            HistorialConexion Historial = new HistorialConexion(idDispositivo,ip,mac);
          

            HistorialConexionDAO Historialdao = new HistorialConexionDAO(this.conn);
            int rows = Historialdao.insertarHistorialConexionDAO(Historial);

            if (rows > 0) {
                JOptionPane.showMessageDialog(this,
                    "Historial de conexión guardado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
                txtIdDispositivo.setText("");
                txtIpUtilizada.setText("");
                txtMacUtilizada.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                    "No se pudo guardar el historial.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }

        
    }
}
