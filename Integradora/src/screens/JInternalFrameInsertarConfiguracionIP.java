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

import models.ConfiguracionIP;
import models.ConfiguracionIPDAO;


public class JInternalFrameInsertarConfiguracionIP extends JInternalFrame {
    private JLabel lblRangoDInicio;
    private JLabel lblRangoFinal;
    private JLabel lblDescripcion;
    private JLabel lblRedDestinada;
    private JTextField txtRangoDInicio;
    private JTextField txtRangoFinal;
    private JTextField txtDescripcion;
    private JTextField txtRedDestinada;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private Connection conn;
    

    public JInternalFrameInsertarConfiguracionIP(Connection conn){
        super("Insertar Configuracion", 
              true,  // resizable
              true,  // closable
              true,  // maximizable
              true); // iconifiable (minimizable)
        this.conn = conn;
        this.setTitle("Insertar nueva Configuración");
        this.setSize(400,400);
        initComponents();
    }

    private void initComponents(){
        // Creación de objetos
        
        lblRangoDInicio= new JLabel("Rango de Inicio:");
        lblRangoFinal = new JLabel("Rango Final:");
        lblDescripcion = new JLabel("Descripción:");
        lblRedDestinada = new JLabel("Red Destinada:");
        txtRangoDInicio = new JTextField();
        txtRangoFinal = new JTextField();
        txtDescripcion= new JTextField();
        txtRedDestinada = new JTextField();
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        // Etiquetas
        
        lblRangoDInicio.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblRangoFinal.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblRedDestinada.setFont(new Font("Tahoma", Font.BOLD, 16));
        //Botones 
        btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));


        btnAceptar.addActionListener(e -> insertarConfiguracionIP());
        btnCancelar.addActionListener(e -> this.dispose());

        GroupLayout layout = new GroupLayout(getContentPane());
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                
                .addComponent(lblRangoDInicio)
                .addComponent(txtRangoDInicio)
                .addComponent(lblRangoFinal)
                .addComponent(txtRangoFinal)
                .addComponent(lblDescripcion)
                .addComponent(txtDescripcion)
                .addComponent(lblRedDestinada)
                .addComponent(txtRedDestinada)
                .addGroup(
                    layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addComponent(btnCancelar)
                )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                
                .addComponent(lblRangoDInicio)
                .addComponent(txtRangoDInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblRangoFinal)
                .addComponent(txtRangoFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblDescripcion)
                .addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblRedDestinada)
                .addComponent(txtRedDestinada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAceptar)
                        .addComponent(btnCancelar)
                )
        );
    }

    private void  insertarConfiguracionIP(){
        int rows;
        
        //  Recuperar datos de las cajas de texto
        String RangoDInicio = txtRangoDInicio.getText();
        String RangoFinal = txtRangoFinal.getText();
        String Descripción = txtDescripcion.getText();
        String RedDestinada = txtRedDestinada.getText();
        

        //  Revisar que los campos no esten vacios
        if (RangoDInicio.isEmpty() || RangoFinal.isEmpty() || Descripción.isEmpty() || RedDestinada.isEmpty() ){
            JOptionPane.showMessageDialog(this,
                "Por favor, complete todos los campos correctamente",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
           
            ConfiguracionIP IPconfiguracion = new ConfiguracionIP (RangoDInicio,RangoFinal,Descripción,RedDestinada);

           
            ConfiguracionIPDAO ConfiguracionIP =  new  ConfiguracionIPDAO(this.conn);

            
            rows = ConfiguracionIP.insertarConfiguracionIPDAO( IPconfiguracion);

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, 
                    "IP Configurada insertada correctamente. ",
                    "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
                txtRangoDInicio.setText("");
                txtRangoFinal.setText("");
                txtDescripcion.setText("");
                txtRedDestinada.setText("");
            }else{
                JOptionPane.showMessageDialog(this, 
                    "Error al insertar la IP Configurada.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }

   }

}
}
