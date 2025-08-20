package screens.Inicioseccion;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.net.URI;

public class Diseño extends JPanel {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginBtn;
    private JCheckBox chkTerminos;

    public Diseño() {
        setLayout(null);
        setBackground(new Color(245, 245, 245)); // fondo

        JLabel lblcorreo = new JLabel("Correo");
        lblcorreo.setBounds(40, 10, 220, 20);
        lblcorreo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblcorreo.setForeground(Color.decode("#160D55")); // azul oscuro
        add(lblcorreo);

        // Campo de usuario con bordes redondeados
        userField = new JTextField();
        userField.setBounds(40, 30, 220, 35);
        userField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        userField.setBackground(Color.gray);
        userField.setForeground(Color.WHITE);
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(userField);

        // Etiqueta PASSWORD
        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setBounds(40, 75, 220, 20);
        lblContrasena.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblContrasena.setForeground(Color.decode("#160D55"));
        add(lblContrasena);

        // Campo de contraseña con bordes redondeados
        passField = new JPasswordField();
        passField.setBounds(40, 95, 220, 35);
        ;
        passField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        passField.setBackground(Color.gray);
        passField.setForeground(Color.WHITE);
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(passField);

        chkTerminos = new JCheckBox("Acepto los términos y condiciones");
        chkTerminos.setBounds(40, 140, 220, 20);
        chkTerminos.setForeground(Color.darkGray);
        chkTerminos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        chkTerminos.setOpaque(false);
        add(chkTerminos);

        JLabel lblVerTerminos = new JLabel("<html><u>Ver términos</u></html>");
        lblVerTerminos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblVerTerminos.setForeground(Color.BLACK); // ← negro para que se vea bien
        lblVerTerminos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblVerTerminos.setBounds(120, 155, 150, 20);
        add(lblVerTerminos);

        lblVerTerminos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    URI enlace = new URI(
                            "https://drive.google.com/file/d/11kVHS9Ck5iTFGUXot1GGzKKaB1F-0kpF/view?usp=drive_link");
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        if (desktop.isSupported(Desktop.Action.BROWSE)) {
                            desktop.browse(enlace);
                        } else {
                            JOptionPane.showMessageDialog(null, "Tu sistema no permite abrir enlaces.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Desktop no soportado en este entorno.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "No se pudo abrir el enlace.");
                    ex.printStackTrace(); // para depurar si falla
                }
            }
        });
        // Botón azul oscuro con bordes redondeados

        loginBtn = new JButton("Ingresar");
        loginBtn.setBounds(40, 180, 220, 40);
        loginBtn.setBackground(Color.decode("#160D55")); // Azul oscuro
        loginBtn.setForeground(Color.gray);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(BorderFactory.createLineBorder(Color.decode("#1e1558ff"), 1));
        loginBtn.setContentAreaFilled(true);
        loginBtn.setOpaque(true);
        loginBtn.setRolloverEnabled(false);
        add(loginBtn);
    }

    public JTextField getUserField() {
        return userField;
    }

    public JPasswordField getPassField() {
        return passField;
    }

    public JButton getLoginButton() {
        return loginBtn;
    }

    public JCheckBox getChkTerminos() {
        return chkTerminos;
    }

    public void setChkTerminos(JCheckBox chkTerminos) {
        this.chkTerminos = chkTerminos;
    }
}