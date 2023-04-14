/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
 * @author Alan Rodriguez
 */
public class IPanel extends JPanel{
    public JButton botonReferencia;
    public Color color;
    public JPanel panel;
    
    public IPanel() {
        super();
    }
    
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

    public JButton getBotonReferencia() {
        return botonReferencia;
    }

    public void setBotonReferencia(JButton botonReferencia) {
        this.botonReferencia = botonReferencia;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    
    
    
}
