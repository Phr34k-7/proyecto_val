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

import models.Evento;
import models.EventoDAO;


public class JInternalFrameInsertarEvento extends JInternalFrame {
    private JLabel lblId;
    private JLabel lblTipoEvento;
    private JLabel lblDescripcion;
    private JTextField txtId;
    private JTextField txtTipoEvento;
    private JTextField txtDescripcion;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private Connection conn;
     
    public JInternalFrameInsertarEvento(Connection conn){
        super("Insertar Evento", 
              true,  // resizable
              true,  // closable
              true,  // maximizable
              true); // iconifiable (minimizable)
        this.conn = conn;
        this.setTitle("Insertar nuevo evento");
        this.setSize(400,400);
        initComponents();
    }

    private void initComponents(){
        // Creación de objetos
        lblId = new JLabel("Id Direccion:");
        lblTipoEvento = new JLabel("Tipo de Evento:");
        lblDescripcion = new JLabel("Descripcion:");
        txtId = new JTextField();
        txtTipoEvento= new JTextField();
        txtDescripcion = new JTextField();
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        // Etiquetas
        lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTipoEvento.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 16));
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
                .addComponent(lblTipoEvento)
                .addComponent(txtTipoEvento)
                .addComponent(lblDescripcion)
                .addComponent(txtDescripcion)
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
                .addComponent(lblTipoEvento)
                .addComponent(txtTipoEvento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblDescripcion)
                .addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
        String TipoEvento = txtTipoEvento.getText();
        String  Descrpcion = txtDescripcion.getText();
        

        //  Revisar que los campos no esten vacios
        if ( id <= 0  || TipoEvento.isEmpty() || Descrpcion.isEmpty()){
            JOptionPane.showMessageDialog(this,
                "Por favor, complete todos los campos correctamente",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
           
            Evento Evento = new Evento(id,TipoEvento,Descrpcion);
            
            EventoDAO EventoDAO =  new EventoDAO(this.conn);
            rows = EventoDAO.insertarEventoDAO(Evento);

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Evento insertada correctamente. ",
                    "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
                txtId.setText("");
                txtTipoEvento.setText("");
                txtDescripcion.setText("");
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, 
                    "Error al insertar Evento.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }

   }

}
}
