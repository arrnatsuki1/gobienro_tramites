/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing_propio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author Rosa Rodriguez
 */
public class GobiernoButton extends JButton {
    
    public Color colorDefault = new Color(255, 255, 255);
    public Color defaultText = new Color(0, 0, 0);
    
    public Color colorEntered = new Color(157, 36, 73);
    public Color enteredText = new Color(255, 255, 255);
    
    
    public GobiernoButton() {
        super();
        
        agregarHover();
        
    }
    
    private void agregarHover() {
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                cambiarColor(true);
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                cambiarColor(false);
            }
            
        });
        
    }
    
    private void cambiarColor(boolean entro) {
        if(entro) {
            if(!this.isEnabled()) {
                return;
            }
            this.setBackground(colorEntered);
            this.setForeground(enteredText);
        } else {
            this.setBackground(colorDefault);
            this.setForeground(defaultText);
        }
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
    }
    
}
