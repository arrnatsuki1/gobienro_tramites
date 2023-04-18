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
 *  Clase BotonGobierno, esto nomas esta clase es para poder hacer botones
 *  iguales con atributos identicos
 * @author Rosa Rodriguez
 */
public class GobiernoButton extends JButton {
    /**
     * Los colores que tendra el boton en caso de que se dispare la accion
     * exited
     */
    public Color colorDefault = new Color(255, 255, 255);
    public Color defaultText = new Color(0, 0, 0);
    /**
     * Los colores que tendra el boton en caso de que se dispare la 
     * accion hover/entered
     */
    public Color colorEntered = new Color(157, 36, 73);
    public Color enteredText = new Color(255, 255, 255);
    
    /**
     * Metodo constructor por defecto del boton con caracteristicas del
     * gobierno(si lo copiamos de la pagina del gobierno)
     */
    public GobiernoButton() {
        super();
        
        agregarHover();
        
    }
    /**
     * Metodo para agregar oyentes a los botones
     */
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
    /**
     * Metodo para cambiar el color del fondo del boton en caso del hover
     * @param entro booleano, en caso de entrar true, en caso de salir false
     */
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
    /**
     * Metodo para dibujar el boton con antialiasing
     * @param g componente a pintar
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
    }
    
}
