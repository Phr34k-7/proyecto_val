package screens;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.Direccionamiento;
import models.DireccionamientoDAO;

import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;

public class JInternalFrameVerDireccionamiento extends JInternalFrame {
    private JLabel lblTitulo;
    private JTable tableDireccionamientos;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private Connection conn;

    public JInternalFrameVerDireccionamiento(Connection conn) {
        super("Ver Direccionamientos", true, true, true, true);
        this.conn = conn;
        this.setSize(600, 400);
        initComponents();
    }

    private void initComponents() {
        lblTitulo = new JLabel("Listado de Direccionamientos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));

        String[] columnNames = {"ID Dispositivo", "Dirección IP", "Dirección MAC"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableDireccionamientos = new JTable(tableModel);
        scrollPane = new JScrollPane(tableDireccionamientos);

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
        DireccionamientoDAO dao = new DireccionamientoDAO(conn);
        ArrayList<Direccionamiento> lista = dao.obtenerDireccionamientos();

        tableModel.setRowCount(0); // Limpiar tabla

        for (Direccionamiento d : lista) {
            Object[] rowData = {
                d.getIdDispositivo(),
                d.getDireccionIp(),
                d.getDireccionMac()
            };
            tableModel.addRow(rowData);
        }
    }
}
