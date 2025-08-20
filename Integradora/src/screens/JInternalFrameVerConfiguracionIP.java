package screens;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.ConfiguracionIP;
import models.ConfiguracionIPDAO;

import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;

public class JInternalFrameVerConfiguracionIP extends JInternalFrame {
    private JLabel lblTitulo;
    private JTable tableConfiguracionIP;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private Connection conn;

    public JInternalFrameVerConfiguracionIP(Connection conn) {
        super("Ver Configuración IP", true, true, true, true);
        this.conn = conn;
        this.setSize(650, 400);
        initComponents();
    }

    private void initComponents() {
        lblTitulo = new JLabel("Listado de Configuraciones IP");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));

        String[] columnNames = {"Rango de Inicio", "Rango Final", "Descripción", "Red Destinada"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableConfiguracionIP = new JTable(tableModel);
        scrollPane = new JScrollPane(tableConfiguracionIP);

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
        ConfiguracionIPDAO dao = new ConfiguracionIPDAO(conn);
        ArrayList<ConfiguracionIP> lista = dao.obtenerConfiguracionIP();

        tableModel.setRowCount(0); // Limpiar tabla

        for (ConfiguracionIP c : lista) {
            Object[] rowData = {
                c.getRangoInicio(),
                c.getRangoFin(),
                c.getDescripcion(),
                c.getRedDestinada()
            };
            tableModel.addRow(rowData);
        }
    }
}