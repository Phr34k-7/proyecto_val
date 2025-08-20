package screens;

import java.sql.Connection;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import models.Direccionamiento;
import models.DireccionamientoDAO;


public class JInternalFrameInsertarDireccionamiento extends JInternalFrame {
    private JLabel lblId;
    private JLabel lblDireccionIP;
    private JLabel lblDireccionMAC;
    private JTextField txtId;
    private JTextField txtDireccionIP;
    private JTextField txtDireccionMAC;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private Connection conn;

    public JInternalFrameInsertarDireccionamiento(Connection conn){
        super("Insertar Dirección", 
              true,  // resizable
              true,  // closable
              true,  // maximizable
              true); // iconifiable (minimizable)
        this.conn = conn;
        this.setTitle("Insertar nueva Dirección");
        this.setSize(400,400);
        initComponents();
    }

    private void initComponents(){
        // Creación de objetos
        lblId = new JLabel("Id de Dispositivo:");
        lblDireccionIP = new JLabel("Direccion IP:");
        lblDireccionMAC = new JLabel("Direccion MAC:");
        txtId = new JTextField();
        txtDireccionIP = new JTextField();
        txtDireccionMAC = new JTextField();
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        // Etiquetas
        lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDireccionIP.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDireccionMAC.setFont(new Font("Tahoma", Font.BOLD, 16));
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
                .addComponent(lblId)
                .addComponent(txtId)
                .addComponent(lblDireccionIP)
                .addComponent(txtDireccionIP)
                .addComponent(lblDireccionMAC)
                .addComponent(txtDireccionMAC)
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
                .addComponent(lblDireccionIP)
                .addComponent(txtDireccionIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblDireccionMAC)
                .addComponent(txtDireccionMAC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
        int id = Integer.parseInt(txtId.getText());
        String DireccionIP = txtDireccionIP.getText();
        String  DireccionMAC = txtDireccionMAC.getText();
        

        //  Revisar que los campos no esten vacios
        if ( id <= 0  || DireccionIP.isEmpty() || DireccionMAC.isEmpty()){
            JOptionPane.showMessageDialog(this,
                "Por favor, complete todos los campos correctamente",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
        
            Direccionamiento Direccionamiento = new Direccionamiento(id,DireccionIP,DireccionMAC);

            
            DireccionamientoDAO DireccionamientoDAO =  new DireccionamientoDAO(this.conn);

        
            rows = DireccionamientoDAO.insertarDireccionamientoDAO(Direccionamiento);

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Dirección insertada correctamente. ",
                    "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
                txtId.setText("");
                txtDireccionIP.setText("");
                txtDireccionMAC.setText("");
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, 
                    "Error al insertar Dirección.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }

   }

}
}
