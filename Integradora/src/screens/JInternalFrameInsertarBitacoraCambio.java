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

import models.BitacoraCambio;
import models.BitacoraCambioDAO;

public class JInternalFrameInsertarBitacoraCambio extends JInternalFrame {
    private JLabel lblIdAdmin;
    private JLabel lblTablaAfectada;
    private JLabel lblDescripcion;
    private JTextField txtIdAdmin;
    private JTextField txtTablaAfectada;
    private JTextField txtDescripcion;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private Connection conn;
    

    public JInternalFrameInsertarBitacoraCambio(Connection conn){
        super("Insertar Bitácora de Cambio", 
              true,  // resizable
              true,  // closable
              true,  // maximizable
              true); // iconifiable (minimizable)

        this.conn = conn;
        this.setTitle("Insertar nueva Bitácora de Cambio");
        this.setSize(400, 400);
        initComponents();
    }

    private void initComponents(){
        lblIdAdmin = new JLabel("ID Administrador:");
        lblTablaAfectada = new JLabel("Tabla Afectada:");
        lblDescripcion = new JLabel("Descripción:");
        txtIdAdmin = new JTextField();
        txtTablaAfectada = new JTextField();
        txtDescripcion = new JTextField();
        
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        // Estilos de fuente
        Font font = new Font("Tahoma", Font.BOLD, 16);
        lblIdAdmin.setFont(font);
        lblTablaAfectada.setFont(font);
        lblDescripcion.setFont(font);
       
        btnAceptar.setFont(font);
        btnCancelar.setFont(font);

        btnAceptar.addActionListener(e -> insertarBitacoraCambio());
        btnCancelar.addActionListener(e -> this.dispose());

        GroupLayout layout = new GroupLayout(getContentPane());
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblIdAdmin)
                .addComponent(txtIdAdmin)
                .addComponent(lblTablaAfectada)
                .addComponent(txtTablaAfectada)
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
                .addComponent(lblIdAdmin)
                .addComponent(txtIdAdmin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblTablaAfectada)
                .addComponent(txtTablaAfectada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblDescripcion)
                .addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar)
                )
        );
    }

    private void insertarBitacoraCambio(){
        int rows;

        
            int idAdmin = Integer.parseInt(txtIdAdmin.getText());
            String tablaAfectada = txtTablaAfectada.getText();
            String descripcion = txtDescripcion.getText();

            if (idAdmin <= 0 || tablaAfectada.isEmpty() || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Por favor, complete todos los campos correctamente",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        else{
            BitacoraCambio cambio = new BitacoraCambio(idAdmin,tablaAfectada,descripcion);


            BitacoraCambioDAO Cambiodao = new BitacoraCambioDAO(this.conn);
            rows = Cambiodao.insertarBitacoraCambioDAO(cambio);

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Bitácora de cambio insertada correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
                txtIdAdmin.setText("");
                txtTablaAfectada.setText("");
                txtDescripcion.setText("");
                
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al insertar la bitácora de cambio.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }

        } 
        }
    }

