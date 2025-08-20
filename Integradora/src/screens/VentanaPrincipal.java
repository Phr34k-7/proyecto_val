package screens;

import java.sql.Connection;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import screens.Inicioseccion.JFrameInicioSección;
import screens.Inicioseccion.FondoDesktop;
import models.UsuariosAdmin;
import models.UsuariosAdminDAO;

public class VentanaPrincipal extends JFrame {
    private JDesktopPane desktop;
    private JPanel menuLateral;
    private JPanel panelSubUsuarios;
    private JPanel panelSubDispositivo;
    private JPanel panelSubDireccionamiento;
    private JPanel panelSubEvento;
    private JPanel panelSubNotificaciones;
    private JPanel panelSubHistorial;
    private JPanel panelSubBitacora;
    private JPanel panelSubIP;
    private JButton btnUsuarios;
    private JButton btnDispositivo;
    private JButton btnDireccionamiento;
    private JButton btnEvento;
    private JButton btnNotificaciones;
    private JButton btnHistorialConexion;
    private JButton btnBitacoraCambio;
    private JButton btnConfiguracionIP;
    private JButton btnInsertarUsuarios;
    private JButton btnVerUsuarios;
    private JButton btnActualizarUsuarios;
    private JButton btnEliminarUsuarios;
    private JButton btnInsertardispositivo;
    private JButton btnVerDispositivo;
    private JButton btnInsertarDireccionamiento;
    private JButton btnVerDireccionamiento;
    private JButton btnInsertarEvento;
    private JButton btnVerEvento;
    private JButton btnInsertarNotificacion;
    private JButton btnVerNotificacion;
    private JButton btnInsertarHistorialConexion;
    private JButton btnVerHistorialConexion;
    private JButton btnInsertarBitacoraCambio;
    private JButton btnVerBitacoraCambio;
    private JButton btnInsertarConfiguracionIP;
    private JButton btnVerConfiguracionIP;
    private UsuariosAdmin usuario;
    private UsuariosAdminDAO dao;
    private Connection conn;
    private JButton crearBoton;

    public VentanaPrincipal(UsuariosAdmin usuario, Connection conn) {
        this.usuario = usuario;
        this.conn = conn;

        String titulo = "Bienvenido " + usuario.getNombre() + " (" + usuario.getRol() + ")";
        setTitle(titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }

    private JButton crearBoton(String texto, ActionListener accion) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // radio de esquinas
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(100, 149, 237)); // color del borde
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
            }
        };

        // Estilo visual
        boton.setPreferredSize(new Dimension(200, 40));
        boton.setMaximumSize(new Dimension(200, 40));
        boton.setBackground(new Color(150, 150, 150)); // Gris claro
        boton.setForeground(Color.BLACK);
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setContentAreaFilled(false); // evita que el fondo rectangular se dibuje encima

        // Hover visual
        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton.setBackground(new Color(205, 205, 250)); // azul claro
            }

            public void mouseExited(MouseEvent evt) {
                boton.setBackground(new Color(150, 150, 150)); // gris claro
            }
        });

        if (accion != null) {
            boton.addActionListener(accion);
        }

        return boton;
    }

    private void initComponents() {
        // Área dinámica
        desktop = new FondoDesktop();
        // Menú lateral
        menuLateral = new JPanel();
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));
        menuLateral.setBackground(new Color(25, 25, 112));
        menuLateral.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        menuLateral.setOpaque(true);
        // Botones del menú lateral
        // menu usuarios
        menuLateral.add(Box.createVerticalStrut(100));
        btnUsuarios = crearBoton("Usuarios", null);
        menuLateral.add(btnUsuarios);
        panelSubUsuarios = new JPanel();
        panelSubUsuarios.setLayout(new BoxLayout(panelSubUsuarios, BoxLayout.Y_AXIS));
        panelSubUsuarios.setBackground(new Color(220, 220, 220)); // tono más claro
        panelSubUsuarios.setVisible(false); // oculto al inicio
        btnVerUsuarios = crearBoton(" Ver", e -> abrirFormularioVerUsuarios());
        btnInsertarUsuarios = crearBoton(" Insertar", e -> abrirFormularioInsertarUsuario());
        if (!usuario.getRol().equals("admin")) {
            btnInsertarUsuarios.setVisible(false); // técnico no lo ve
        }
        panelSubUsuarios.add(btnInsertarUsuarios);
        panelSubUsuarios.add(btnVerUsuarios);
        
        
        btnUsuarios.setText("👤 Usuarios");
        btnUsuarios.setToolTipText("Gestión de usuarios");

        menuLateral.add(panelSubUsuarios);
        menuLateral.add(Box.createVerticalStrut(10)); // Espacio entre botones
        // Acción para desplegar
        btnUsuarios.addActionListener(e -> {
            panelSubUsuarios.setVisible(!panelSubUsuarios.isVisible());
            menuLateral.revalidate();
            menuLateral.repaint();
        });
        // Dispositivo
        menuLateral.add(Box.createVerticalStrut(10)); // Espacio entre botones
        btnDispositivo = crearBoton("Dispositivo", null);
        menuLateral.add(btnDispositivo);
        JPanel panelSubDispositivo = new JPanel();
        panelSubDispositivo.setLayout(new BoxLayout(panelSubDispositivo, BoxLayout.Y_AXIS));
        panelSubDispositivo.setBackground(new Color(0, 90, 160));
        panelSubDispositivo.setVisible(false);
        JButton btnVerDispositivo = crearBoton(" Ver", e -> abrirFormularioVerDispositivo());
        panelSubDispositivo.add(btnVerDispositivo);
        JButton btnInsertarDispositivo = crearBoton(" Insertar", e -> abrirFormularioInsertarDispositivo());
        panelSubDispositivo.add(btnInsertarDispositivo);
        btnDispositivo.setText("🖥️ Dispositivo");
        btnDispositivo.setToolTipText("Registro y estado de dispositivos");

        menuLateral.add(panelSubDispositivo);
        menuLateral.add(Box.createVerticalStrut(10)); // Espacio entre botones
        btnDispositivo.addActionListener(e -> {
            panelSubDispositivo.setVisible(!panelSubDispositivo.isVisible());
        });

        // Direccionamiento
        menuLateral.add(Box.createVerticalStrut(10));
        btnDireccionamiento = crearBoton("Direccionamiento", null);
        menuLateral.add(btnDireccionamiento);
        JPanel panelSubDireccionamiento = new JPanel();
        panelSubDireccionamiento.setLayout(new BoxLayout(panelSubDireccionamiento, BoxLayout.Y_AXIS));
        panelSubDireccionamiento.setBackground(new Color(0, 90, 160));
        panelSubDireccionamiento.setVisible(false);
        JButton btnVerDireccionamiento = crearBoton(" Ver", e -> abrirFormularioVerDireccionamiento());
        panelSubDireccionamiento.add(btnVerDireccionamiento);
        JButton btnInsertarDireccionamiento = crearBoton(" Insertar", e -> abrirFormularioInsertarDireccionamiento());
        panelSubDireccionamiento.add(btnInsertarDireccionamiento);
        btnDireccionamiento.setText("📡 Direccionamiento");
        btnDireccionamiento.setToolTipText("Asignación de IP y MAC a dispositivos");
        menuLateral.add(panelSubDireccionamiento);
        menuLateral.add(Box.createVerticalStrut(10)); // Espacio entre botones
        btnDireccionamiento.addActionListener(e -> {
            panelSubDireccionamiento.setVisible(!panelSubDireccionamiento.isVisible());
        });

        // Evento
        menuLateral.add(Box.createVerticalStrut(10)); // Espacio entre botones
        btnEvento = crearBoton(" Evento", null);
        menuLateral.add(btnEvento);
        JPanel panelSubEvento = new JPanel();
        panelSubEvento.setLayout(new BoxLayout(panelSubEvento, BoxLayout.Y_AXIS));
        panelSubEvento.setBackground(new Color(0, 90, 160));
        panelSubEvento.setVisible(false);
        JButton btnVerEvento = crearBoton(" Ver", e -> abrirFormularioVerEvento());
        panelSubEvento.add(btnVerEvento);
        JButton btnInsertarEvento = crearBoton(" Insertar", e -> abrirFormularioInsertarEvento());
        panelSubEvento.add(btnInsertarEvento);
        btnEvento.setText("📅 Evento");
        btnEvento.setToolTipText(" Registro de sucesos técnicos");

        menuLateral.add(panelSubEvento);
        menuLateral.add(Box.createVerticalStrut(10)); // Espacio entre botones
        btnEvento.addActionListener(e -> {
            panelSubEvento.setVisible(!panelSubEvento.isVisible());
        });

        // Notificaciones
        menuLateral.add(Box.createVerticalStrut(10)); // Espacio entre botones
        btnNotificaciones = crearBoton(" Notificaciones", null);
        menuLateral.add(btnNotificaciones);
        JPanel panelSubNotificaciones = new JPanel();
        panelSubNotificaciones.setLayout(new BoxLayout(panelSubNotificaciones, BoxLayout.Y_AXIS));
        panelSubNotificaciones.setBackground(new Color(0, 90, 160));
        panelSubNotificaciones.setVisible(false);
        JButton btnVerNotificaciones = crearBoton(" Ver", e -> abrirFormularioVerNotificaciones());
        panelSubNotificaciones.add(btnVerNotificaciones);
        JButton btnInsertarNotificaciones = crearBoton(" Insertar", e -> abrirFormularioInsertarNotificacion());
        panelSubNotificaciones.add(btnInsertarNotificaciones);
        btnNotificaciones.setText("🔔 Notificaciones");
        btnNotificaciones.setToolTipText(" Envío de alertas por eventos");

        menuLateral.add(panelSubNotificaciones);
        menuLateral.add(Box.createVerticalStrut(10)); // Espacio entre botones
        btnNotificaciones.addActionListener(e -> {
            panelSubNotificaciones.setVisible(!panelSubNotificaciones.isVisible());
        });

        // Historial de Conexión
        menuLateral.add(Box.createVerticalStrut(10)); // Espacio entre botones
        btnHistorialConexion = crearBoton("Historial", null);
        menuLateral.add(btnHistorialConexion);
        JPanel panelSubHistorial = new JPanel();
        panelSubHistorial.setLayout(new BoxLayout(panelSubHistorial, BoxLayout.Y_AXIS));
        panelSubHistorial.setBackground(new Color(0, 90, 160));
        panelSubHistorial.setVisible(false);
        JButton btnverHistorialCon = crearBoton(" Ver", e -> abrirFormularioVerHistorialCon());
        panelSubHistorial.add(btnverHistorialCon);
        JButton btnVerHistorial = crearBoton(" Insertar", e -> abrirFormularInsertarHistorialConexion());
        panelSubHistorial.add(btnVerHistorial);
        btnHistorialConexion.setText("📈 Historial");
        btnHistorialConexion.setToolTipText("Registro de conexiones por IP/MAC");

        menuLateral.add(panelSubHistorial);
        menuLateral.add(Box.createVerticalStrut(10)); // Espacio entre botones
        btnHistorialConexion.addActionListener(e -> {
            panelSubHistorial.setVisible(!panelSubHistorial.isVisible());
        });

        // 📘 Bitácora de Cambio
        menuLateral.add(Box.createVerticalStrut(10)); // Espacio entre botones
        btnBitacoraCambio = crearBoton(" Bitácora", null);
        menuLateral.add(btnBitacoraCambio);
        JPanel panelSubBitacora = new JPanel();
        panelSubBitacora.setLayout(new BoxLayout(panelSubBitacora, BoxLayout.Y_AXIS));
        panelSubBitacora.setBackground(new Color(0, 90, 160));
        panelSubBitacora.setVisible(false);
        JButton btnVerBitacora = crearBoton(" Ver", e -> abrirFormularioVerBitacora());
        panelSubBitacora.add(btnVerBitacora);

        JButton btnInsertarBitacora = crearBoton(" Insertar", e -> abrirFormularioInsertarBitacora());
        panelSubBitacora.add(btnInsertarBitacora);
        btnBitacoraCambio.setText("📝 Bitácora");
        btnBitacoraCambio.setToolTipText("Auditoría de cambios hechos por el admin");
        if (!usuario.getRol().equals("admin")) {
            btnBitacoraCambio.setVisible(false); // técnico no accede
        }

        menuLateral.add(panelSubBitacora);
        menuLateral.add(Box.createVerticalStrut(10)); // Espacio entre botones
        btnBitacoraCambio.addActionListener(e -> {
            panelSubBitacora.setVisible(!panelSubBitacora.isVisible());
        });

        // Configuración IP
        menuLateral.add(Box.createVerticalStrut(10)); // Espacio entre botones
        btnConfiguracionIP = crearBoton("Configuración IP", null);
        menuLateral.add(btnConfiguracionIP);
        JPanel panelSubIP = new JPanel();
        panelSubIP.setLayout(new BoxLayout(panelSubIP, BoxLayout.Y_AXIS));
        panelSubIP.setBackground(new Color(0, 90, 160));
        panelSubIP.setVisible(false);
        JButton btnVerIP = crearBoton(" Ver", e -> abrirFormularioVerConfiguracionIP());
        panelSubIP.add(btnVerIP);
        JButton btnInsertarIP = crearBoton(" Insertar", e -> abrirFormularioInsertarConfiguracionIP());
        panelSubIP.add(btnInsertarIP);
        btnConfiguracionIP.setText("🌐 Configuración IP");
        btnConfiguracionIP.setToolTipText("Definición de rangos IP y redesP");

        menuLateral.add(panelSubIP);
        menuLateral.add(Box.createVerticalStrut(10)); // Espacio entre botones
        btnConfiguracionIP.addActionListener(e -> {
            panelSubIP.setVisible(!panelSubIP.isVisible());

        });
        // Envolver el menú lateral en un JScrollPane
        JScrollPane scrollMenu = new JScrollPane(menuLateral);
        scrollMenu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollMenu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollMenu.setBorder(null);

        // Panel dividido con scroll en el menú lateral
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollMenu, desktop);
        splitPane.setDividerLocation(700);
        splitPane.setEnabled(false);

        // Agregar el splitPane al JFrame
        add(splitPane, BorderLayout.CENTER);

        JButton btnCerrarSesion = new JButton("🔓 Cerrar sesión");
        btnCerrarSesion.setToolTipText("Finalizar sesión actual");
        btnCerrarSesion.setBackground(new Color(60, 60, 60));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarSesion.setBackground(new Color(100, 149, 237)); // azul claro
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarSesion.setBackground(new Color(60, 60, 60)); // gris oscuro
            }
        });

        btnCerrarSesion.addActionListener(e -> {
            dispose(); // cerrar ventana actual
            new JFrameInicioSección().setVisible(true); // abrir login
        });

        menuLateral.add(Box.createVerticalStrut(100));
        menuLateral.add(btnCerrarSesion);

        add(splitPane);
        pack();
    }

    private void estilizarMenu(JMenu menu) {
        menu.setForeground(Color.WHITE);
        menu.setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    private void abrirFormularioInsertarUsuario() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameInsertarUsuariosAdmin insertarUsuario = new JInternalFrameInsertarUsuariosAdmin(this.conn);
        // Agregar el InternalFrame al escritorio
        this.desktop.add(insertarUsuario);
        // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = insertarUsuario.getSize();
        insertarUsuario.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        insertarUsuario.setVisible(true);
    }

    private void abrirFormularioVerUsuarios() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameVerUsuariosAdmin VerUsuario = new JInternalFrameVerUsuariosAdmin(this.conn);
        // // // Agregar l InternalFrame al escritorio
        this.desktop.add(VerUsuario);
        // // // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = VerUsuario.getSize();
        VerUsuario.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        VerUsuario.setVisible(true);
    }
     

    private void abrirFormularioInsertarDispositivo() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameInsertarDispositivo insertarDispositivo = new JInternalFrameInsertarDispositivo(this.conn);
        // Agregar el InternalFrame al escritorio
        this.desktop.add(insertarDispositivo);
        // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = insertarDispositivo.getSize();
        insertarDispositivo.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        insertarDispositivo.setVisible(true);
    }

    private void abrirFormularioVerDispositivo() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameVerDispositivo VerDis = new JInternalFrameVerDispositivo(this.conn);
        // // // Agregar l InternalFrame al escritorio
        this.desktop.add(VerDis);
        // // // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = VerDis.getSize();
        VerDis.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        VerDis.setVisible(true);
    }

    private void abrirFormularioInsertarDireccionamiento() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameInsertarDireccionamiento insertarDireccionamiento = new JInternalFrameInsertarDireccionamiento(
                this.conn);
        // Agregar el InternalFrame al escritorio
        this.desktop.add(insertarDireccionamiento);
        // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = insertarDireccionamiento.getSize();
        insertarDireccionamiento.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        insertarDireccionamiento.setVisible(true);
    }

    private void abrirFormularioVerDireccionamiento() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameVerDireccionamiento VerDire = new JInternalFrameVerDireccionamiento(this.conn);
        // // // Agregar l InternalFrame al escritorio
        this.desktop.add(VerDire);
        // // // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = VerDire.getSize();
        VerDire.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        VerDire.setVisible(true);
    }

    private void abrirFormularioInsertarEvento() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameInsertarEvento insertarEvento = new JInternalFrameInsertarEvento(this.conn);
        // Agregar el InternalFrame al escritorio
        this.desktop.add(insertarEvento);
        // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = insertarEvento.getSize();
        insertarEvento.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        insertarEvento.setVisible(true);
    }

    private void abrirFormularioVerEvento() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameVerEvento VerEve = new JInternalFrameVerEvento(this.conn);
        // // // Agregar l InternalFrame al escritorio
        this.desktop.add(VerEve);
        // // // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = VerEve.getSize();
        VerEve.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        VerEve.setVisible(true);
    }

    private void abrirFormularioInsertarNotificacion() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameInsertarNotificaciones insertarNotificaciones = new JInternalFrameInsertarNotificaciones(
                this.conn);
        // Agregar el InternalFrame al escritorio
        this.desktop.add(insertarNotificaciones);
        // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = insertarNotificaciones.getSize();
        insertarNotificaciones.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        insertarNotificaciones.setVisible(true);
    }

    private void abrirFormularioVerNotificaciones() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameVerNotificaciones VerNoti = new JInternalFrameVerNotificaciones(this.conn);
        // // // Agregar l InternalFrame al escritorio
        this.desktop.add(VerNoti);
        // // // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = VerNoti.getSize();
        VerNoti.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        VerNoti.setVisible(true);
    }

    private void abrirFormularioInsertarBitacora() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameInsertarBitacoraCambio insertarBitacora = new JInternalFrameInsertarBitacoraCambio(this.conn);
        // Agregar el InternalFrame al escritorio
        this.desktop.add(insertarBitacora);
        // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = insertarBitacora.getSize();
        insertarBitacora.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        insertarBitacora.setVisible(true);
    }

    private void abrirFormularioVerBitacora() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameVerBitacoracambio VerBi = new JInternalFrameVerBitacoracambio(this.conn);
        // // // Agregar l InternalFrame al escritorio
        this.desktop.add(VerBi);
        // // // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = VerBi.getSize();
        VerBi.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        VerBi.setVisible(true);
    }

    private void abrirFormularInsertarHistorialConexion() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameInsertarHistorialConexion insertarHistorial = new JInternalFrameInsertarHistorialConexion(
                this.conn);
        // Agregar el InternalFrame al escritorio
        this.desktop.add(insertarHistorial);
        // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = insertarHistorial.getSize();
        insertarHistorial.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        insertarHistorial.setVisible(true);
    }

    private void abrirFormularioVerHistorialCon() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameVerHistorialConexiones VerCon = new JInternalFrameVerHistorialConexiones(this.conn);
        // // // Agregar l InternalFrame al escritorio
        this.desktop.add(VerCon);
        // // // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = VerCon.getSize();
        VerCon.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        VerCon.setVisible(true);
    }

    private void abrirFormularioInsertarConfiguracionIP() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameInsertarConfiguracionIP insertarConip = new JInternalFrameInsertarConfiguracionIP(this.conn);
        // Agregar el InternalFrame al escritorio
        this.desktop.add(insertarConip);
        // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = insertarConip.getSize();
        insertarConip.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        insertarConip.setVisible(true);
    }

    private void abrirFormularioVerConfiguracionIP() {
        // Crear un objeto de tipo JInternalFrame
        JInternalFrameVerConfiguracionIP VerIP = new JInternalFrameVerConfiguracionIP(this.conn);
        // // // Agregar l InternalFrame al escritorio
        this.desktop.add(VerIP);
        // // // Le digo que se muestre el InternalFrame
        Dimension desktopSize = desktop.getSize();
        Dimension frameSize = VerIP.getSize();
        VerIP.setLocation((desktopSize.width - frameSize.width) / 2,
                (desktopSize.height - frameSize.height) / 2);
        VerIP.setVisible(true);
    }

}
