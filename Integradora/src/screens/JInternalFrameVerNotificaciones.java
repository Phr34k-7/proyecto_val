package screens;
import java.awt.Font;
import java.sql.Connection;
import java.sql.NClob;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Notificacion;
import models.NotificacionDAO;


public class JInternalFrameVerNotificaciones extends JInternalFrame {
 private JLabel lblTitulo;
    private JTable tableNotificaciones;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private Connection conn;
    private ArrayList<Notificacion> notificacion;

   public JInternalFrameVerNotificaciones(Connection conn) {
         super("Ver Notificaciones",
             true,
             true,
             true,
             true);
            this.conn = conn; // Inicializar la conexión a la base de datos
        this.setTitle("Lista de Notificaciones");
        this.setSize(600, 400);
        initComponents();
   }

    private void initComponents() {
        lblTitulo = new JLabel("Listado de ");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));

        // Aquí se debería cargar la lista de carreras desde la base de datos
        // y crear el JTable con los datos obtenidos.
        String[] columnNames = {"Id Evento", "Enviado a", "Metodo de Envio","Estado de Envio","fecha de Envio"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableNotificaciones = new JTable(tableModel); // Placeholder para el JTable
        scrollPane = new JScrollPane(tableNotificaciones);

        rellenarTabla();
        tableNotificaciones.setModel(tableModel);
        tableModel.fireTableDataChanged();

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

  public void rellenarTabla() {
    NotificacionDAO NotiDAO = new NotificacionDAO(conn);
    List<Notificacion> List = NotiDAO.obtenerNotificacion();

    tableModel.setRowCount(0);

   for (Notificacion noti : List) {
    Object[] rowData = {
        noti.getIdEvento(),
    noti.getEnviadoA(),
    noti.getMetodoEnvio(),
    noti.isEstadoEnvio() ? "Enviado" : "Pendiente",
    };
    tableModel.addRow(rowData);
}
}        

}
