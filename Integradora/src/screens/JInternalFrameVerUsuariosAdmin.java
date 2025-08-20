package screens;
import java.awt.Font; //Necesaria para cambiar el tipo de letra
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout; //Necesario para el diseño del layout
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import models.UsuariosAdmin;
import models.UsuariosAdminDAO;

public class JInternalFrameVerUsuariosAdmin extends JInternalFrame{
    private JLabel lblTitulo;
    private JTable tableUsuarios_Admin;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private Connection conn;
    private ArrayList<UsuariosAdmin> usuariosAdmin;

   public JInternalFrameVerUsuariosAdmin(Connection conn) {
         super("Ver Usuarios",
             true,
             true,
             true,
             true);
            this.conn = conn; // Inicializar la conexión a la base de datos
        this.setTitle("Lista de Usuario");
        this.setSize(600, 400);
        initComponents();
   }

    private void initComponents() {
        lblTitulo = new JLabel("Listado de Usuarios");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));

        // Aquí se debería cargar la lista de carreras desde la base de datos
        // y crear el JTable con los datos obtenidos.
        String[] columnNames = {"Nombre", "Apellido Paterno", "Correo","Rol"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableUsuarios_Admin = new JTable(tableModel); // Placeholder para el JTable
        scrollPane = new JScrollPane(tableUsuarios_Admin);

        rellenarTabla();
        tableUsuarios_Admin.setModel(tableModel);
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
    UsuariosAdminDAO UsaDAO = new UsuariosAdminDAO(conn);
    List<UsuariosAdmin> unUsa = UsaDAO.obtenerUsuariosAdmin();

    tableModel.setRowCount(0);

    for (UsuariosAdmin usuario : unUsa) {
        Object[] rowData = {
            usuario.getNombre(),
            usuario.getApePt(),
            usuario.getCorreo(),
            usuario.getRol()
        };
        tableModel.addRow(rowData);

    }
}        

    
}

