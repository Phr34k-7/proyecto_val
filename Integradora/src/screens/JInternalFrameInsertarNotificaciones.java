package screens;

import java.awt.Font;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import models.Notificacion;
import models.NotificacionDAO;

public class JInternalFrameInsertarNotificaciones extends JInternalFrame{
    private JLabel lblId;
    private JLabel lblEnviadoA;
    private JLabel lblMetodoDEnvio;
    private JLabel lblEstadoEnvio;
    private JLabel lblfechaEnvio;
    private JTextField txtId;
    private JTextField txtEnviadoA;
    private JTextField txtMetodoDEnvio;
    private JTextField txtEstadoEnvio;
    private JTextField txtfechaEnvio;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private Connection conn;
    private JDesktopPane desktop;
    private String rol;
    
    

    public JInternalFrameInsertarNotificaciones( JDesktopPane desktop, String rol) {
        this.desktop = desktop;
        this.rol = rol;
    }

    public JInternalFrameInsertarNotificaciones(Connection conn){
        super("Insertar Notificacion", 
              true,  // resizable
              true,  // closable
              true,  // maximizable
              true); // iconifiable (minimizable)
        this.conn = conn;
        this.setTitle("Insertar nueva Notificacion");
        this.setSize(400,400);
        initComponents();
    }

    private void initComponents(){
        // Creación de objetos
        
        lblId = new JLabel("Id de Evento:");
        lblEnviadoA = new JLabel("Enviado a..:");
        lblMetodoDEnvio = new JLabel("Metodo de Envio:");
        lblEstadoEnvio = new JLabel("Estado de Envio:");
        txtId = new JTextField();
        txtEnviadoA = new JTextField();
        txtMetodoDEnvio= new JTextField();
        txtEstadoEnvio = new JTextField();
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        
        // Etiquetas
        
        lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblEnviadoA.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblMetodoDEnvio.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblEstadoEnvio.setFont(new Font("Tahoma", Font.BOLD, 16));
        //Botones 
        btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));


        btnAceptar.addActionListener(e -> insertarNotificaciones());
        btnCancelar.addActionListener(e -> this.dispose());

        GroupLayout layout = new GroupLayout(getContentPane());
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                
                .addComponent(lblId)
                .addComponent(txtId)
                .addComponent(lblEnviadoA)
                .addComponent(txtEnviadoA)
                .addComponent(lblMetodoDEnvio)
                .addComponent(txtMetodoDEnvio)
                .addComponent(lblEstadoEnvio)
                .addComponent(txtEstadoEnvio)
                .addGroup( 
                    layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addComponent(btnCancelar)
                )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                
                .addComponent(lblId)
                .addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblEnviadoA)
                .addComponent(txtEnviadoA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblMetodoDEnvio)
                .addComponent(txtMetodoDEnvio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblEstadoEnvio)
                .addComponent(txtEstadoEnvio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAceptar)
                        .addComponent(btnCancelar)
                )
        );
    }

   private void insertarNotificaciones() {
    
        int idEvento = Integer.parseInt(txtId.getText());
        String enviadoA = txtEnviadoA.getText();
        String metodoEnvio = txtMetodoDEnvio.getText();
        boolean estadoEnvio = Boolean.parseBoolean(txtEstadoEnvio.getText().trim());
        

        if (idEvento <= 0 || enviadoA.isEmpty() || metodoEnvio.isEmpty() || txtEstadoEnvio.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, complete todos los campos correctamente",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Notificacion noti = new Notificacion(idEvento, enviadoA, metodoEnvio, estadoEnvio);
        NotificacionDAO notiDao = new NotificacionDAO(this.conn);
        int rows = notiDao.insertarNotificaciones(noti);

        if (rows > 0) {
            JOptionPane.showMessageDialog(this,
                    "Notificación insertada correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            txtId.setText("");
            txtEnviadoA.setText("");
            txtMetodoDEnvio.setText("");
            txtEstadoEnvio.setText("");
           
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error al insertar la Notificación.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }}