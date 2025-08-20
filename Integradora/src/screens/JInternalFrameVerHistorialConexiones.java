package screens;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.HistorialConexionDAO;
import models.HistorialConexion;
import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;

public class JInternalFrameVerHistorialConexiones extends JInternalFrame {
    private JLabel lblTitulo;
    private JTable tableHistorial;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private Connection conn;

    public JInternalFrameVerHistorialConexiones(Connection conn) {
        super("Historial de Conexiones", true, true, true, true);
        this.conn = conn;
        this.setSize(600, 400);
        initComponents();
    }

    private void initComponents() {
        lblTitulo = new JLabel("Historial de Conexiones");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));

        String[] columnNames = {"ID Dispositivo", "IP Utilizada", "MAC Utilizada"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableHistorial = new JTable(tableModel);
        scrollPane = new JScrollPane(tableHistorial);

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
        HistorialConexionDAO dao = new HistorialConexionDAO(conn);
        ArrayList<HistorialConexion> lista = dao.obtenerHistorialConexions();

        tableModel.setRowCount(0); // Limpiar tabla

        for (HistorialConexion unCon : lista) {
            Object[] rowData = {
                unCon.getIdDispositivo(),
                unCon.getIpUtilizada(),
                unCon.getMacUtilizada()
            };
            tableModel.addRow(rowData);
        }
    }
}
