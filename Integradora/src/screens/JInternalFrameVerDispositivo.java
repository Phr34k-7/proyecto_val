package screens;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.Dispositivo;
import models.DispositivoDAO;

import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;

public class JInternalFrameVerDispositivo extends JInternalFrame {
    private JLabel lblTitulo;
    private JTable tableDispositivos;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private Connection conn;

    public JInternalFrameVerDispositivo(Connection conn) {
        super("Ver Dispositivos", true, true, true, true);
        this.conn = conn;
        this.setSize(600, 400);
        initComponents();
    }

    private void initComponents() {
        lblTitulo = new JLabel("Listado de Dispositivos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));

        String[] columnNames = {"Nombre", "Tipo", "Usuario Asignado"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableDispositivos = new JTable(tableModel);
        scrollPane = new JScrollPane(tableDispositivos);

        rellenarTabla();

        GroupLayout layout = new GroupLayout(getContentPane());
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblTitulo)
                .addComponent(scrollPane)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addComponent(scrollPane)
        );

        this.pack();
    }

    private void rellenarTabla() {
        DispositivoDAO dao = new DispositivoDAO(conn);
        ArrayList<Dispositivo> lista = dao.obtenerDispositivos();

        tableModel.setRowCount(0); // Limpiar tabla

        for (Dispositivo d : lista) {
            Object[] rowData = {
                d.getNombreDispositivo(),
                d.getTipoDispositivo(),
                d.getUsuarioAsignado()
            };
            tableModel.addRow(rowData);
        }
    }
}

