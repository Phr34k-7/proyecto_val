package screens;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.Evento;
import models.EventoDAO;

import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;


public class JInternalFrameVerEvento extends JInternalFrame {
    private JLabel lblTitulo;
    private JTable tableEventos;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private Connection conn;

    public JInternalFrameVerEvento(Connection conn) {
        super("Ver Eventos", true, true, true, true);
        this.conn = conn;
        this.setSize(600, 400);
        initComponents();
    }

    private void initComponents() {
        lblTitulo = new JLabel("Listado de Eventos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));

        String[] columnNames = {"ID Dirección", "Tipo de Evento", "Descripción"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableEventos = new JTable(tableModel);
        scrollPane = new JScrollPane(tableEventos);

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
        EventoDAO dao = new EventoDAO(conn);
        ArrayList<Evento> lista = dao.obtenerEvento();

        tableModel.setRowCount(0); // Limpiar tabla

        for (Evento e : lista) {
            Object[] rowData = {
                e.getIdDireccion(),
                e.getTipoEvento(),
                e.getDescripcion()
            };
            tableModel.addRow(rowData);
        }
    }
}