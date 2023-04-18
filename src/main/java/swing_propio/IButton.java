package swing_propio;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

/**
 * Este esta clase boton es para instanciar botones que tengan la propiedad
 * del antialiasing
 * @author Rosa Rodriguez y Jose Trista
 */
public class IButton extends JButton {
    /**
     * Metodo constructor del boton por defecto
     */
    public IButton() {
        super();
    }
    /**
     * Metodo para pintar el boton con la propiedad del antialiasing
     * @param g componente a pintar
     */
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
    }
    
}
