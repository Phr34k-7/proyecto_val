package screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.BitacoraCambio;
import models.BitacoraCambioDAO;

import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;

public class JInternalFrameVerBitacoracambio extends JInternalFrame {
    private JLabel lblTitulo;
    private JTable tableBitacora;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private Connection conn;

    public JInternalFrameVerBitacoracambio(Connection conn) {
        super("Ver Bitácora de Cambios", true, true, true, true);
        this.conn = conn;
        this.setSize(650, 400);
        initComponents();
    }

    private void initComponents() {
        lblTitulo = new JLabel("Bitácora de Cambios");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));

        String[] columnNames = {"ID Admin", "Tabla Afectada", "Descripción"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableBitacora = new JTable(tableModel);
        scrollPane = new JScrollPane(tableBitacora);

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
        BitacoraCambioDAO dao = new BitacoraCambioDAO(conn);
        ArrayList<BitacoraCambio> lista = dao.obtenerBitacoraCambio();

        tableModel.setRowCount(0); // Limpiar tabla

        for (BitacoraCambio b : lista) {
            Object[] rowData = {
                b.getIdAdmin(),
                b.getTablaAfectada(),
                b.getDescripcion()
            };
            tableModel.addRow(rowData);
        }
    }
}
