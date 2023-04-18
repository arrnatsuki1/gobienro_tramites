package swing_propio;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Clase para crear botones con antialiasing
 * @author Rosa Rodriguez
 */
public class IPanel extends JPanel{
    /**
     * Boton al cual se quiere llegar con el dibujo
     */
    public JButton botonReferencia;
    /**
     * Color de la figura entre el panel y el boton
     */
    public Color color;
    /**
     * Panel del cual sale el dibujo
     */
    public JPanel panel;
    /**
     * Metodo constructor por defecto
     */
    public IPanel() {
        super();
    }
    /**
     * Este metodo pinta debajo de un panel un un recuadro para dar un efecto
     * que sale de un boton en espesifico
     * @param g 
     */
    @Override
    protected void paintComponent(Graphics g) {        
        super.paintComponent(g);
        Graphics2D c = (Graphics2D)g;
        c.setStroke(new BasicStroke(5));
        c.setColor(color);
        c.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int panel_width = panel.getWidth(), panel_height = panel.getHeight();
        int panelx = panel.getX() + 13, panely = panel.getY();
        
        int x1 = panelx, y1 = panely + panel_height;
        int x2 = botonReferencia.getX(), y2 = botonReferencia.getY();
        int x3 = botonReferencia.getX() + botonReferencia.getWidth(), y3 = botonReferencia.getY();
        int x4 = panelx + panel_width - 14, y4 = panely + panel_height;
        
        int[] xs = {x1, x2, x3, x4};
        int[] ys = {y1, y2, y3, y4};
        
        Polygon p = new Polygon(xs, ys, 4);
        
        c.drawPolygon(p);
        c.fill(p);
        
    }
    /**
     * Metodo para obtener el boton de referencia
     * @return JButton de referencia
     */
    public JButton getBotonReferencia() {
        return botonReferencia;
    }
    /**
     * Metodo para setear el boton de referencia
     * @param botonReferencia JButton
     */
    public void setBotonReferencia(JButton botonReferencia) {
        this.botonReferencia = botonReferencia;
    }
    /**
     * Metodo para obtener el color del recuadro que se dibuja entre el
     * panel y el botton
     * @return Color
     */
    public Color getColor() {
        return color;
    }
    /**
     * Metodo para setear el Color de la figura que se dibujara entre el panel
     * y el boton
     * @param color Color
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Metodo para obtener el panel de referencia
     * @return JPanel
     */
    public JPanel getPanel() {
        return panel;
    }
    /**
     * Metodo para setear el panl de referencia
     * @param panel JPanel
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    
    
    
}
