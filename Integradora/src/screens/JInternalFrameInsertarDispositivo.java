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

import models.Dispositivo;
import models.DispositivoDAO;



public class JInternalFrameInsertarDispositivo extends JInternalFrame{
    private JLabel lblNombreDispositivo;
    private JLabel lblTipoDispositivo;
    private JLabel lblUsuarioAsignado;
    private JTextField txtNombreDispositivo;
    private JTextField txtTipoDispositivo;
    private JTextField txtUsuarioAsignado;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private Connection conn;
    

    public JInternalFrameInsertarDispositivo(Connection conn){
        super("Insertar Dispositivo", 
              true,  // resizable
              true,  // closable
              true,  // maximizable
              true); // iconifiable (minimizable)
        this.conn = conn;
        this.setTitle("Insertar nuevo Dispositivo");
        this.setSize(400,400);
        initComponents();
    }

    private void initComponents(){
        // Creación de objetos
        lblNombreDispositivo = new JLabel("Nombre del Dispositivo:");
        lblTipoDispositivo = new JLabel("Tipo de Dispositivo:");
        lblUsuarioAsignado = new JLabel("Usuario Asignado:");
        txtNombreDispositivo = new JTextField();
        txtTipoDispositivo = new JTextField();
        txtUsuarioAsignado = new JTextField();
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        // Etiquetas
        lblNombreDispositivo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTipoDispositivo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblUsuarioAsignado.setFont(new Font("Tahoma", Font.BOLD, 16));
        //Botones 
        btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));


        btnAceptar.addActionListener(e -> insertarUsuario());
        btnCancelar.addActionListener(e -> this.dispose());

        GroupLayout layout = new GroupLayout(getContentPane());
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblNombreDispositivo)
                .addComponent(txtNombreDispositivo)
                .addComponent(lblTipoDispositivo)
                .addComponent(txtTipoDispositivo)
                .addComponent(lblUsuarioAsignado)
                .addComponent(txtUsuarioAsignado)
                .addGroup(
                    layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addComponent(btnCancelar)
                )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblNombreDispositivo)
                .addComponent(txtNombreDispositivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTipoDispositivo)
                .addComponent(txtTipoDispositivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblUsuarioAsignado)
                .addComponent(txtUsuarioAsignado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAceptar)
                        .addComponent(btnCancelar)
                )
        );
    }

    private void  insertarUsuario(){
        int rows;
        
        //  Recuperar datos de las cajas de texto
        String NombreDispositivo = txtNombreDispositivo.getText();
        String TipoDispositivo = txtTipoDispositivo.getText();
        String  UsuarioAsignado = txtUsuarioAsignado.getText();
        

        //  Revisar que los campos no esten vacios
        if ( NombreDispositivo.isEmpty() || TipoDispositivo.isEmpty() || UsuarioAsignado.isEmpty()){
            JOptionPane.showMessageDialog(this,
                "Por favor, complete todos los campos correctamente",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
           
            Dispositivo Dispositivo = new Dispositivo(NombreDispositivo,TipoDispositivo,UsuarioAsignado);

           
            DispositivoDAO DispositivoDAO =  new DispositivoDAO(this.conn);

           
            rows = DispositivoDAO.insertarDispositivoDAO(Dispositivo);

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Dispositivo insertado correctamente. ",
                    "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
                txtNombreDispositivo.setText("");
                txtTipoDispositivo.setText("");
                txtUsuarioAsignado.setText("");
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, 
                    "Error al insertar Dispositivo.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }

   }

}

}
