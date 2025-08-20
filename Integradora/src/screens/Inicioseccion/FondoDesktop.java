package screens.Inicioseccion;
import javax.swing.*;
import java.awt.*;

public class FondoDesktop extends JDesktopPane {
    private Image fondo;

    public FondoDesktop() {
        // Asegúrate de que la imagen esté en /src/resources/jaygroup.png
        fondo = new ImageIcon(getClass().getResource("/resources/jaygroup.png")).getImage();
    }

   @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (fondo != null) {
        Graphics2D g2d = (Graphics2D) g.create();
        int x = (getWidth() - fondo.getWidth(null)) / 2;
        int y = (getHeight() - fondo.getHeight(null)) / 2;
        g2d.drawImage(fondo, x, y, this);
        g2d.dispose();
    }
}
}
